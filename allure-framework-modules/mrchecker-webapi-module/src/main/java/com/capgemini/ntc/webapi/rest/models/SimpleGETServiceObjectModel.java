package com.capgemini.ntc.webapi.rest.models;

import com.capgemini.ntc.webapi.core.RestServiceObjectModel;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class SimpleGETServiceObjectModel extends RestServiceObjectModel {
	
	private final static String	HOSTNAME	= "https://httpbin.org";
	private final static String	PATH		= "/get";
	
	public SimpleGETServiceObjectModel(RequestSpecification requestSpecification) {
		super(requestSpecification, HOSTNAME, PATH);
	}
	
	public Response sendGETQuery() {
		return super.getRequestSpecyfication().when()
						.get(super.getServiceUrl());
	}
}
