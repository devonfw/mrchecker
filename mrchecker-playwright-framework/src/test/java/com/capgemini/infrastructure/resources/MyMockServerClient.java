package com.capgemini.infrastructure.resources;

import com.capgemini.infrastructure.Configuration;
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
        try {
            mockServerClient = new MockServerClient(mockServer.getHost(), mockServer.getServerPort());

            // Add rule by file
            // http://localhost:1080/my-api/sample-1
            /*
            {
"source": "MySystem",
"transportMode": "AIR",
"totalPackages": 1000,
"commercialValue": {
"cost": 200,
"currency": "USD"
},
"isCancelled": false
}
             */
            addRule("/my-api/sample-1", "sample-mock-response-1.json");

            // Add conditional rule
            addRuleConditionTxt("/cst/integration/api/createRequestV3", "20220415-43872", Map.of(
                    "AI24000595", "20220415-66666",
                    "BY24001263", "20220415-66666"
            ));

            // Add text response
            addRuleTxt("/cst/integration/api/closeRequest", "CST request closed");
            addRuleTxt("/cst/integration/api/proceedNextStep", "CST request proceeded to the next step");

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
