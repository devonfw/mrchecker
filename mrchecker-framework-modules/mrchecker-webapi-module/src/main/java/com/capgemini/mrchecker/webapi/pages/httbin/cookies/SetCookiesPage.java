package com.capgemini.mrchecker.webapi.pages.httbin.cookies;

import com.capgemini.mrchecker.webapi.core.BasePageWebAPI;
import com.capgemini.mrchecker.webapi.core.base.driver.DriverManager;
import com.capgemini.mrchecker.webapi.pages.environment.GetEnvironmentParam;

import io.restassured.response.Response;

public class SetCookiesPage extends BasePageWebAPI {
	private final static String	HOSTNAME	= GetEnvironmentParam.HTTPBIN.getValue();
	private final static String	PATH		= "/set";
	private final static String	ENDPOINT	= HOSTNAME + PATH;
	
	public Response sendGETQuery() {
		return DriverManager.getDriverWebAPI()
				.when()
				.get(ENDPOINT);
	}
	
	public String getEndpoint() {
		return ENDPOINT;
	}
}
