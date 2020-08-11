package com.capgemini.mrchecker.webapi.pages.httbin;

import com.capgemini.mrchecker.webapi.core.BasePageWebAPI;
import com.capgemini.mrchecker.webapi.core.base.driver.DriverManager;
import com.capgemini.mrchecker.webapi.pages.environment.GetEnvironmentParam;

import io.restassured.response.Response;

public class RandomNBytesPage extends BasePageWebAPI {
	private final static String	HOSTNAME	= GetEnvironmentParam.HTTPBIN.getValue();
	private final static String	PATH		= "/bytes/{length}";
	private final static String	ENDPOINT	= HOSTNAME + PATH;
	private Response			response	= null;
	
	public void sendGETQuery(String length) {
		response = DriverManager.getDriverWebAPI()
				.given()
				.pathParam("length", length)
				.get(ENDPOINT);
	}
	
	public byte[] getBytesFromResponse() {
		if (response != null) {
			return response.body()
					.asByteArray();
		}
		return null;
	}
	
	public int getStatusCodeFromResponse() {
		if (response != null) {
			return response.getStatusCode();
		}
		return -1;
	}
	
	@Override
	public String getEndpoint() {
		return ENDPOINT;
	}
}
