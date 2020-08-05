package com.capgemini.mrchecker.webapi.pages.httbin;

import com.capgemini.mrchecker.webapi.core.BasePageWebAPI;
import com.capgemini.mrchecker.webapi.core.base.driver.DriverManager;
import com.capgemini.mrchecker.webapi.pages.environment.GetEnvironmentParam;

import io.restassured.response.Response;

public class Base64Page extends BasePageWebAPI {
	private final static String	HOSTNAME	= GetEnvironmentParam.HTTPBIN.getValue();
	private final static String	PATH		= "/base64/{value}";
	private final static String	ENDPOINT	= HOSTNAME + PATH;
	
	private Response sendGETQuery(String value) {
		return DriverManager.getDriverWebAPI()
				.given()
				.pathParam("value", value)
				.get(ENDPOINT);
	}
	
	public String getDecodedValue(String base64Data) {
		Response response = sendGETQuery(base64Data);
		if (response.statusCode() == 200 && !response.body()
				.equals(null)) {
			return response.body()
					.asString();
		}
		return null;
	}
	
	@Override
	public String getEndpoint() {
		return ENDPOINT;
	}
}