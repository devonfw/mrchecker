package com.capgemini.mrchecker.webapi.mts.common;

import io.restassured.response.Response;

public final class Context {
	private static final Context	instance	= new Context();
	private Response				response;
	
	public static Context getInstance() {
		return instance;
	}
	
	private Context() {
	}
	
	public void setResponse(Response response) {
		this.response = response;
	}
	
	public int getResponseCode() {
		return response.statusCode();
	}
	
	public String getResponseHeaderValue(String header) {
		return response.getHeader(header);
	}
}
