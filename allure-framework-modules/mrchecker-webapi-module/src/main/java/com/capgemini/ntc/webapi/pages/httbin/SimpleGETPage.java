package com.capgemini.ntc.webapi.pages.httbin;

import com.capgemini.ntc.webapi.core.BasePageWebAPI;
import com.capgemini.ntc.webapi.core.base.driver.DriverManager;
import com.capgemini.ntc.webapi.pages.environment.GetEnvironmentParam;

import io.restassured.response.Response;

public class SimpleGETPage extends BasePageWebAPI {
	
	private final static String	HOSTNAME	= GetEnvironmentParam.HTTPBIN.getValue();
	private final static String	PATH		= "/get";
	
	public Response sendGETQuery() {
		getDriver();
		return DriverManager.getDriverWebAPI()
						.when()
						.get(HOSTNAME + PATH);
	}
	
	@Override
	public String getMessage() {
		// TASK Auto-generated method stub
		return null;
	}
}
