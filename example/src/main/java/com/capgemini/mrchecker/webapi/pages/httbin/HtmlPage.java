package com.capgemini.mrchecker.webapi.pages.httbin;

import com.capgemini.mrchecker.webapi.core.BasePageWebAPI;
import com.capgemini.mrchecker.webapi.core.base.driver.DriverManager;
import com.capgemini.mrchecker.webapi.pages.environment.GetEnvironmentParam;
import io.restassured.response.Response;

public class HtmlPage extends BasePageWebAPI {
	private final static String HOSTNAME = GetEnvironmentParam.HTTPBIN.getValue();
	private final static String PATH     = "/html";
	private final static String ENDPOINT = HOSTNAME + PATH;

	public Response getHtmlDocument() {
		return DriverManager.getDriverWebAPI()
				.get(ENDPOINT);
	}

	@Override
	public String getEndpoint() {
		return ENDPOINT;
	}
}
