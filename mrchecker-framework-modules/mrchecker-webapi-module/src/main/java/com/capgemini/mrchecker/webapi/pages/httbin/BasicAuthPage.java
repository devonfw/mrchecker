package com.capgemini.mrchecker.webapi.pages.httbin;

import com.capgemini.mrchecker.webapi.core.BasePageWebAPI;
import com.capgemini.mrchecker.webapi.core.base.driver.DriverManager;
import com.capgemini.mrchecker.webapi.pages.environment.GetEnvironmentParam;

import io.restassured.response.Response;

public class BasicAuthPage extends BasePageWebAPI {
	
	private final static String	HOSTNAME	= GetEnvironmentParam.HTTPBIN.getValue();
	private final static String	PATH		= "/basic-auth/user/passwd";
	private final static String	ENDPOINT	= HOSTNAME + PATH;
	
	public Response sendBasicAuthGETQuery(String username, String password) {
		return DriverManager.getDriverWebAPI()
						.auth()
						.basic(username, password)
						.when()
						.get(ENDPOINT);
	}
	
	@Override
	public String getEndpoint() {
		return ENDPOINT;
	}
}