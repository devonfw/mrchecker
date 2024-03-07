package com.capgemini.framework.allure.listeners;

import io.qameta.allure.listener.TestLifecycleListener;
import io.qameta.allure.model.Link;
import io.qameta.allure.model.Status;
import io.qameta.allure.model.TestResult;
import io.qameta.allure.util.ResultsUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;
import java.text.MessageFormat;
import java.util.LinkedList;
import java.util.List;

/**
 * This listener is adding bug to allure report in case:
 * - test has TmsLink annotation
 * - Test fails
 * - Bug is defined in BUGS_FILE_PATH with the same:
 * - TmsLink
 * - Fail message
 */
public class AllureKnownBug implements TestLifecycleListener {
    static final String JIRA_LINK = "LinkToJira/";
    static final String BUGS_FILE_PATH = "src/test/resources/bugs/bugs.json";

    @Override
    public void beforeTestWrite(TestResult testResult) {

        // Only works for tests with defined JIRA-ID
        if (getXrayTestKey(testResult).isEmpty()) {
            return;
        }
        // Only works for tests which failed
        if (testResult.getStatus() == Status.FAILED || testResult.getStatus() == Status.SKIPPED) {
            new AllureKnownBug().handleKnownIssue(testResult);
        }
    }

    public void handleKnownIssue(TestResult testResult) {
        var message = testResult.getStatusDetails().getMessage();
        var test = testResult.getFullName();
        var jiraId = "";
        var status = testResult.getStatus();

        //Read bug.json file
        try (var reader = new FileReader(BUGS_FILE_PATH)) {
            for (var bug : (JSONArray) new JSONParser().parse(reader)) {
                var bugJSON = (JSONObject) bug;
                var testsJSONList = new LinkedList<String>();

                for (var bugJSONTest : (JSONArray) bugJSON.get("tests")) {
                    testsJSONList.add((String) bugJSONTest);
                }
                addStatusDetails(testResult, test, testsJSONList, jiraId, message, bugJSON, status);
            }
        } catch (IOException | ParseException e) {
            ExceptionUtils.printRootCauseStackTrace(e);
        }
    }

    private void addStatusDetails(TestResult testResult, String test, LinkedList<String> testsJSONList, String jiraId, String message, JSONObject bugJSON, Status status) {
        //Check if current test is in bug.json file
        if (isTestInFile(testResult, test, testsJSONList, jiraId, message, (String) bugJSON.get("message"))
                && (status == Status.FAILED || status == Status.SKIPPED)) {
            var statusDetails = testResult.getStatusDetails();
            var issueStatusJSON = MessageFormat.format("[{0}]", bugJSON.get("issueStatus")).toUpperCase();
            var errorMessage = MessageFormat.format("{0} {1}", issueStatusJSON, message);
            statusDetails.setMessage(errorMessage);
            testResult.setStatusDetails(statusDetails);

            //Add known bug to allure report
            var linksList = testResult.getLinks();
            var issueJSON = (String) bugJSON.get("issue");
            linksList.add(new Link().setName(issueJSON)
                    .setType(ResultsUtils.ISSUE_LINK_TYPE)
                    .setUrl(JIRA_LINK + issueJSON));

            testResult.setLinks(linksList);
        }
    }

    private boolean isTestInFile(TestResult testResult, String test, List<String> testsJSONList, String jiraId, String message, String messageJSON) {
        return (isStringContainsPartFromList(test, testsJSONList) || testsJSONList.contains(jiraId) || testsJSONList.contains("*") || testsJSONList.contains(
                getXrayTestKey(testResult))) && (message.contains(messageJSON) || message.replace("\n", "")
                .replace("\r", "")
                .matches(".*" + messageJSON.replace("\n", "") + ".*"));
    }

    // Fix for parametrized smoke tests which change method name by add some at the end
    private boolean isStringContainsPartFromList(String text, List<String> parts) {
        if (text != null && !parts.isEmpty()) {
            for (var part : parts) {
                if (text.contains(part.replace("-", ""))) {
                    return true;
                }
            }
        }
        return false;
    }

    private String getXrayTestKey(TestResult result) {
        return result.getLinks()
                .stream()
                .filter(link -> ResultsUtils.TMS_LINK_TYPE.equals(link.getType()))
                .map(Link::getName)
                .findFirst()
                .orElse("");
    }
}