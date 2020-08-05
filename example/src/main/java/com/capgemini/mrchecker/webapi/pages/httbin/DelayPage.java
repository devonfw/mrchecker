package com.capgemini.mrchecker.webapi.pages.httbin;

import com.capgemini.mrchecker.webapi.core.BasePageWebAPI;
import com.capgemini.mrchecker.webapi.core.base.driver.DriverManager;
import com.capgemini.mrchecker.webapi.pages.environment.GetEnvironmentParam;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class DelayPage extends BasePageWebAPI {
	
	private final static String	HOSTNAME	= GetEnvironmentParam.HTTPBIN.getValue();
	private final static String	PATH		= "/delay/{seconds}";
	private final static String	ENDPOINT	= HOSTNAME + PATH;
	
	public Response sendDelayGETQuery(String seconds) {
		return prepareRequestSpec(seconds).get(ENDPOINT);
	}
	
	public Response sendDelayPOSTQuery(String seconds) {
		return prepareRequestSpec(seconds).post(ENDPOINT);
	}
	
	public Response sendDelayPUTQuery(String seconds) {
		return prepareRequestSpec(seconds).put(ENDPOINT);
	}
	
	public Response sendDelayDELETEQuery(String seconds) {
		return prepareRequestSpec(seconds).delete(ENDPOINT);
	}
	
	public Response sendDelayPATCHQuery(String seconds) {
		return prepareRequestSpec(seconds).patch(ENDPOINT);
	}
	
	private RequestSpecification prepareRequestSpec(String seconds) {
		return DriverManager.getDriverWebAPI()
				.given()
				.pathParam("seconds", seconds)
				.when();
	}
	
	@Override
	public String getEndpoint() {
		return ENDPOINT;
	}
}