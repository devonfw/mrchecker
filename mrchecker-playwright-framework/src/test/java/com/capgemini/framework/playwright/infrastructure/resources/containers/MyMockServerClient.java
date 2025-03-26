package com.capgemini.framework.playwright.infrastructure.resources.containers;

import com.capgemini.framework.playwright.infrastructure.Configuration;
import org.mockserver.client.MockServerClient;
import org.mockserver.matchers.TimeToLive;
import org.mockserver.matchers.Times;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Map;

import static org.mockserver.model.HttpRequest.request;
import static org.mockserver.model.HttpResponse.response;
import static org.mockserver.model.StringBody.subString;

public class MyMockServerClient {
    private final Logger logger = LoggerFactory.getLogger(MyMockServerClient.class);
    private final MyMockServer mockServer = Configuration.getInstance().getMyMockServer();
    private static MyMockServerClient instance = null;
    private MockServerClient mockServerClient = null;

    private MyMockServerClient() {
    }

    public static MyMockServerClient getInstance() {
        if (instance == null) {
            instance = new MyMockServerClient();
        }
        return instance;
    }

    public void startMockClient() {
        // Dashboard link: http://localhost:1080/mockserver/dashboard
        try {
            mockServerClient = new MockServerClient(mockServer.getHost(), mockServer.getServerPort());

            // Add rule by file with response
            // http://localhost:1080/my-api/sample-1
            addRule("/my-api/sample-1", "sample-mock-response-1.json");
            addRule("/my-api/sample-2", "sample-mock-response-2.json");

            // Add conditional rule
            // http://localhost:1080/my-api/sample-text-1
            addRuleConditionTxt("/my-api/sample-text-1", "First sample response", Map.of(
                    "Key-string-in-the-body-1", "Second sample response",
                    "Key-string-in-the-body-2", "Third sample response"
            ));

            // Add text response
            // http://localhost:1080/my-api/sample-text-4
            addRuleTxt("/my-api/sample-text-4", "Fourth sample response");

            logger.info("Mock URL: {}:{}", mockServer.getHost(), mockServer.getServerPort());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    //customResponseBody: key -> condition string, value -> response body to return
    private void addRuleConditionTxt(String uriPath, String defaultResponse, Map<String, String> customResponseBody) {
        for (var condition : customResponseBody.entrySet()) {
            addConditionExpectation(uriPath, condition.getKey(), condition.getValue());
            logger.info("Mock rule added for path: http://{}:{}{} and condition: {} -> {}", mockServer.getHost(), mockServer.getServerPort(), uriPath, condition.getKey(), condition.getValue());
        }

        mockServerClient.when(
                        request().withPath(uriPath),
                        Times.unlimited(),
                        TimeToLive.unlimited(),
                        -100
                )
                .respond(response()
                        .withBody(defaultResponse)
                        .withHeader("Content-Type", "application/json;charset=UTF-8")
                );
        logger.info("Mock rule added for path: http://{}:{}{} default response {}", mockServer.getHost(), mockServer.getServerPort(), uriPath, defaultResponse);
    }

    private void addConditionExpectation(String uriPath, String condtion, String responseBody) {
        mockServerClient
                .when(
                        request()
                                .withPath(uriPath)
                                .withBody(subString(condtion))
                )
                .respond(response()
                        .withBody(responseBody)
                        .withHeader("Content-Type", "application/json;charset=UTF-8")
                );
    }

    private void addRuleTxt(String uriPath, String responseBody) {
        logger.info("Mock rule added for path: http://{}:{}{}", mockServer.getHost(), mockServer.getServerPort(), uriPath);
        mockServerClient
                .when(request().withPath(uriPath))
                .respond(response()
                        .withBody(responseBody)
                        .withHeader("Content-Type", "application/json;charset=UTF-8"));
    }

    private void addRule(String uriPath, String filePath) {
        logger.info("Mock rule added for path: http://{}:{}{}", mockServer.getHost(), mockServer.getServerPort(), uriPath);
        mockServerClient
                .when(request().withPath(uriPath))
                .respond(response()
                        .withBody(getMyResponse(filePath))
                        .withHeader("Content-Type", "application/json;charset=UTF-8"));
    }

    private String getMyResponse(String fileToMock) {
        try (var inputStream = ClassLoader.getSystemClassLoader().getResourceAsStream("mock/" + fileToMock)) {
            assert inputStream != null;
            return new String(inputStream.readAllBytes(), StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
