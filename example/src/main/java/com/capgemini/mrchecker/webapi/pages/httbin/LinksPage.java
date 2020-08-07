package com.capgemini.mrchecker.webapi.pages.httbin;

import com.capgemini.mrchecker.webapi.core.BasePageWebAPI;
import com.capgemini.mrchecker.webapi.core.base.driver.DriverManager;
import com.capgemini.mrchecker.webapi.pages.environment.GetEnvironmentParam;

import io.restassured.response.Response;

public class LinksPage extends BasePageWebAPI {
	private final static String	HOSTNAME	= GetEnvironmentParam.HTTPBIN.getValue();
	private final static String	PATH		= "/links/{n}/{offset}";
	private final static String	ENDPOINT	= HOSTNAME + PATH;
	
	public Response getHtmlDocument(int n, int offset) {
		return DriverManager.getDriverWebAPI()
				.pathParam("n", n)
				.pathParam("offset", offset)
				.get(ENDPOINT);
	}
	
	@Override
	public String getEndpoint() {
		return ENDPOINT;
	}
}
