package com.capgemini.ntc.webapi.pages.httbin;

import com.capgemini.ntc.webapi.core.BasePageWebAPI;
import com.capgemini.ntc.webapi.core.base.driver.DriverManager;
import com.capgemini.ntc.webapi.pages.environment.GetEnvironmentParam;

import io.restassured.response.Response;

public class SimpleGETPage extends BasePageWebAPI {
	
	private final static String	HOSTNAME	= GetEnvironmentParam.HTTPBIN.getValue();
	private final static String	PATH		= "/get";
	private final static String	ENDPOINT	= HOSTNAME + PATH;
	
	public Response sendGETQuery() {
		return DriverManager.getDriverWebAPI()
						.when()
						.get(ENDPOINT);
	}
	
	@Override
	public String getEndpoint() {
		return ENDPOINT;
	}
	
	@Override
	public String getMessage() {
		// Useful only for SOAP queries
		return null;
	}
}
