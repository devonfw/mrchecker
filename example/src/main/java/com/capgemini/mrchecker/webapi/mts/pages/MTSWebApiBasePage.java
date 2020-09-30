package com.capgemini.mrchecker.webapi.mts.pages;

import com.capgemini.mrchecker.webapi.core.BasePageWebAPI;
import com.capgemini.mrchecker.webapi.mts.config.AllureReportApiFilter;
import com.capgemini.mrchecker.webapi.mts.config.CustomFilter;
import com.capgemini.mrchecker.webapi.mts.environment.GetEnvironmentParam;

import io.restassured.RestAssured;
import io.restassured.config.ConnectionConfig;
import io.restassured.config.HttpClientConfig;
import io.restassured.config.RestAssuredConfig;
import io.restassured.specification.RequestSpecification;

public abstract class MTSWebApiBasePage extends BasePageWebAPI {
	protected static String		accessToken					= "";
	protected String			serviceBaseUrs				= GetEnvironmentParam.MAY_THAI_STAR_API_URL.getValue();
	private static final String	REQUEST_TEMPLATE			= "http-mc-request.ftl";
	private static final String	RESPONSE_TEMPLATE			= "http-mc-response.ftl";
	public static final int		SERVER_RESPONSE_TIMEOUT_SEC	= 5;
	
	protected RequestSpecification getLoginRequestBuilder() {
		return RestAssured.given()
				.baseUri(serviceBaseUrs)
				.filter(new CustomFilter())
				.filter(new AllureReportApiFilter().setRequestTemplate(REQUEST_TEMPLATE)
						.setResponseTemplate(RESPONSE_TEMPLATE))
				.contentType("application/json")
				.config(RestAssuredConfig.config()
						.httpClient(HttpClientConfig.httpClientConfig()
								.dontReuseHttpClientInstance())
						.connectionConfig(ConnectionConfig.connectionConfig()));
	}
	
	protected RequestSpecification getRequestBuilder() {
		return getLoginRequestBuilder()
				.header("Authorization", accessToken);
	}
}
