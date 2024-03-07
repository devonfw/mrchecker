package com.capgemini.framework.allure.listeners;

import com.capgemini.framework.logger.Logger;
import io.qameta.allure.listener.TestLifecycleListener;
import io.qameta.allure.model.Attachment;
import io.qameta.allure.model.Link;
import io.qameta.allure.model.TestResult;
import io.qameta.allure.util.ResultsUtils;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.RegExUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.json.simple.JSONObject;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author mdzienia
 * This listener is creating json file in PATH_XRAY_JSON, with the results of test execution in format:
 * https://docs.getxray.app/display/XRAYCLOUD/Using+Xray+JSON+format+to+import+execution+results#UsingXrayJSONformattoimportexecutionresults-JSONformat
 * It can be used for importing tests to Xray
 */
public class AllureToXray implements TestLifecycleListener {
    private static final String PATH_ALLURE_RESULTS = "target/allure-results/";
    private static final String PATH_XRAY_JSON = "target/XRAY";
    private static final String COMMENT_DEFAULT_TEXT_APPNAME = "";

    private static final String XRAY_FILE = PATH_XRAY_JSON + "/" + "xrayTestExecution.json";

    private static final String JSON_EMPTY = " {\"tests\":[\n\n\n]}\n";
    private static final String JSON_END = "\n]}\n";
    private static final String JSON_COMMA = "\n,\n";
    
    
    private static final Object $LOCK_XRAY_FILE = new Object[0];
    
    
    @Override
    public void afterTestWrite(TestResult result) {
        if (getXrayTestKey(result).isEmpty() || "jiraIssue".equals(getXrayTestKey(result))) {
            return;
        }

        var oneTestResult = createXrayJsonForCurrentTest(result);
        createDirectory();
        addJsonToAllTestsFile(oneTestResult);
        createSeparateJsonFile(oneTestResult);
    }

    @Override
    public void beforeTestWrite(TestResult result) {
        result.setName(createSummary(result));
    }

    private String createSummary(TestResult result) {
        var testName = result.getName();
        testName = removeJiraIdFromParametrizedTestName(testName);
        testName = reformatDataDrivenMethodNamesToMatchDisplayedInAllure(testName);
        return testName;
    }

    private String removeJiraIdFromParametrizedTestName(String testName) {
        var jiraId = "YourProjectShortCutInJira-\\d{5}";
        var testsParametersDelimiter = ", ";
        var parameterHeader = "JIRA_ISSUE = ";
        var regex = String.format("(%s)?(%s|null)(%s)?", parameterHeader, jiraId, testsParametersDelimiter);

        return RegExUtils.removePattern(testName, regex);
    }

    private String reformatDataDrivenMethodNamesToMatchDisplayedInAllure(String testName) {
        return testName.replace("()", "")
                .replace(",", " | ")
                .trim();
    }

    private JSONObject createXrayJsonForCurrentTest(TestResult result) {
        // Bind all together in one JSON
        var oneTestResult = new JSONObject();

        oneTestResult.put("testKey", getXrayTestKey(result));
        oneTestResult.put("status", getXrayStatus(result));
        oneTestResult.put("start", getXrayTimestamp(result.getStart()));
        oneTestResult.put("finish", getXrayTimestamp(result.getStop()));
        oneTestResult.put("comment", getXrayComment(result));

        var evidenceList = getEvidenceList(result);
        if (!evidenceList.isEmpty()) {
            oneTestResult.put("evidences", evidenceList);
        }

        var defectsList = getBugList(result);
        if (!defectsList.isEmpty()) {
            oneTestResult.put("defects", defectsList);
        }

        return oneTestResult;
    }

    private List<String> getBugList(TestResult result) {
        return result.getLinks()
                .stream()
                .filter(link -> link.getType()
                        .equals(ResultsUtils.ISSUE_LINK_TYPE))
                .map(Link::getName)
                .toList();
    }

    private String getXrayComment(TestResult result) {
        if (result.getStatusDetails() != null && result.getStatusDetails().getMessage() != null) {
            return COMMENT_DEFAULT_TEXT_APPNAME + "\nBug:\n" + result.getStatusDetails().getMessage();
        }
        return COMMENT_DEFAULT_TEXT_APPNAME;
    }

    private List<JSONObject> getEvidenceList(TestResult result) {
        var stepList = result.getSteps();
        var attachmentList = new ArrayList<Attachment>();

        for (var step : stepList) {
            attachmentList.addAll(step.getAttachments());
        }
        var evidenceList = new ArrayList<JSONObject>();
        Logger.logInfo("attachments: " + attachmentList.size());

        if (!attachmentList.isEmpty()) {
            for (var attachment : attachmentList) {
                var filename = PATH_ALLURE_RESULTS + attachment.getSource();
                Logger.logInfo("filename=" + filename);
                var fileAttachment = encodeFileToBase64Binary(filename);

                if (fileAttachment != null) {
                    JSONObject evidence = new JSONObject();
                    var value = attachment.getName()
                            .replace(" ", "_")
                            .replace("/", "_") + ".html";
                    evidence.put("filename", value);
                    evidence.put("contentType", "text/html");
                    evidence.put("data", fileAttachment);

                    evidenceList.add(evidence);
                }
            }
        }
        return evidenceList;
    }

    private String getXrayTestKey(TestResult result) {
        return result.getLinks().stream()
                .filter(link -> link.getType().equals(ResultsUtils.TMS_LINK_TYPE))
                .map(Link::getName)
                .findFirst().orElse("");
    }

    private String getXrayStatus(TestResult result) {
        return switch (result.getStatus().value()) {
            case "passed" -> "PASS";
            case "failed", "skipped" -> "FAIL";
            case "broken" -> "ABORTED";
            default -> "";
        };
    }

    private String getXrayTimestamp(Long date) {
        var dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'+00:00'");
        dateFormat.setTimeZone(TimeZone.getTimeZone("CET"));
        return dateFormat.format(date);
    }

    private void createSeparateJsonFile(JSONObject oneTestResult) {
        var testKey = oneTestResult.get("testKey").toString();
        try (var file = new FileWriter(PATH_XRAY_JSON + "/" + "xrayTest-" + testKey + ".json")) {
            file.write(oneTestResult.toString());
            file.flush();
        } catch (IOException e) {
            ExceptionUtils.printRootCauseStackTrace(e);
        }
    }


    private static void addJsonToAllTestsFile(JSONObject oneTestResult) {
        Object object = $LOCK_XRAY_FILE;
        synchronized (object) {
            var file = new File(XRAY_FILE);
            if (!file.exists() && !file.isDirectory()) {
                createEmptyXrayFile();
            }
            addCurrentTestToXrayFile(oneTestResult.toString());
        }
    }


    private static void createEmptyXrayFile() {
        Object object = $LOCK_XRAY_FILE;
        synchronized (object) {
            try {
                Files.write(Paths.get(XRAY_FILE), JSON_EMPTY.getBytes(), StandardOpenOption.CREATE);
            } catch (IOException e) {
                ExceptionUtils.printRootCauseStackTrace(e);
            }
        }
    }


    private static void addCurrentTestToXrayFile(String textToAdd) {
        Object object = $LOCK_XRAY_FILE;
        synchronized (object) {
            try (var xrayFile = new RandomAccessFile(XRAY_FILE, "rw")) {
                xrayFile.seek(xrayFile.length() - JSON_END.length());
                if (xrayFile.length() == JSON_EMPTY.length()) {
                    xrayFile.writeBytes(textToAdd + JSON_END);
                } else {
                    xrayFile.writeBytes(JSON_COMMA + textToAdd + JSON_END);
                }
            } catch (IOException e) {
                ExceptionUtils.printRootCauseStackTrace(e);
            }
        }
    }

    private void createDirectory() {
        new File(PATH_XRAY_JSON).mkdirs();
    }

    private static String encodeFileToBase64Binary(String fileName) {
        try {
            return Base64.getEncoder()
                    .encodeToString(FileUtils.readFileToByteArray(new File(fileName)));
        } catch (IOException e) {
            ExceptionUtils.printRootCauseStackTrace(e);
        }
        return null;
    }
}
