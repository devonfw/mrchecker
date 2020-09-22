package com.capgemini.mrchecker.webapi.mts.pages;

import com.capgemini.mrchecker.webapi.core.BasePageWebAPI;
import com.capgemini.mrchecker.webapi.mts.environment.GetEnvironmentParam;

import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;

public abstract class MTSWebApiBasePage extends BasePageWebAPI {
	protected String	accessToken;
	protected String	serviceBaseUrs	= GetEnvironmentParam.MAY_THAI_STAR_API_URL.getValue();
	
	public RequestSpecification request() {
		return RestAssured
				.with()
				.contentType("application/json")
				.header("Authorization", "Bearer " + accessToken);
	}
}
