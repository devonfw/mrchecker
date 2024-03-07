package com.capgemini.framework.actions;

import com.capgemini.framework.logger.AllureStepLogger;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.microsoft.playwright.APIResponse;
import com.microsoft.playwright.options.RequestOptions;
import io.qameta.allure.Step;

import java.io.IOException;

import static com.capgemini.framework.playwright.PlaywrightFactory.getAPIRequestContext;

public final class ActionAPI {
	
	@Step("GET {endpoint}")
	public static APIResponse getApiResponse(String endpoint) throws IOException {
		return logResponse(getAPIRequestContext().get(endpoint));
	}
	
	@Step("GET {endpoint}")
	public APIResponse getRequest(String endpoint, RequestOptions options) throws IOException {
		logRequest(options);
		return logResponse(getAPIRequestContext().get(endpoint, options));
	}
	
	@Step("POST {endpoint}")
	public APIResponse postRequest(String endpoint) throws IOException {
		return logResponse(getAPIRequestContext().post(endpoint));
	}
	
	@Step("POST {endpoint}")
	public APIResponse postRequest(String endpoint, RequestOptions options) throws IOException {
		logRequest(options);
		return logResponse(getAPIRequestContext().post(endpoint, options));
	}
	
	@Step("PUT {endpoint}")
	public APIResponse putRequest(String endpoint) throws IOException {
		return logResponse(getAPIRequestContext().put(endpoint));
	}
	
	@Step("PUT {endpoint}")
	public APIResponse putRequest(String endpoint, RequestOptions options) throws IOException {
		logRequest(options);
		return logResponse(getAPIRequestContext().put(endpoint, options));
	}
	
	@Step("PATCH {endpoint}")
	public APIResponse patchRequest(String endpoint) throws IOException {
		return logResponse(getAPIRequestContext().patch(endpoint));
	}
	
	@Step("PATCH {endpoint}")
	public APIResponse patchRequest(String endpoint, RequestOptions options) throws IOException {
		logRequest(options);
		return logResponse(getAPIRequestContext().patch(endpoint, options));
	}
	
	@Step("DELETE {endpoint}")
	public APIResponse deleteRequest(String endpoint) throws IOException {
		return logResponse(getAPIRequestContext().delete(endpoint));
	}
	
	@Step("DELETE {endpoint}")
	public APIResponse deleteRequest(String endpoint, RequestOptions options) throws IOException {
		logRequest(options);
		return logResponse(getAPIRequestContext().delete(endpoint, options));
	}
	
	private static void logRequest(RequestOptions options) {
		AllureStepLogger.saveTextAttachmentToLog("request.txt", options.toString());
	}
	
	private static APIResponse logResponse(APIResponse response) throws IOException {
		AllureStepLogger.saveTextAttachmentToLog("responseHeader.txt", response.headers().toString());
		AllureStepLogger.saveTextAttachmentToLog("responseBody.txt", new ObjectMapper().readTree(response.body()).toPrettyString());
		return response;
	}
}
