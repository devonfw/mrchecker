package com.capgemini.mrchecker.webapi.pages.httbin.cookies;

import com.capgemini.mrchecker.webapi.core.BasePageWebAPI;
import com.capgemini.mrchecker.webapi.core.base.driver.DriverManager;
import com.capgemini.mrchecker.webapi.pages.environment.GetEnvironmentParam;

import io.restassured.http.Cookie;
import io.restassured.response.Response;

public class SetCookiesPage extends BasePageWebAPI {
	private final static String	HOSTNAME	= GetEnvironmentParam.HTTPBIN.getValue();
	private final static String	PATH		= "/cookies/set";
	private final static String	ENDPOINT	= HOSTNAME + PATH;
	
	private String	setCookiesEndpointWithValues;
	private Cookie	cookie;
	
	public SetCookiesPage(Cookie cookie) {
		this.cookie = cookie;
	}
	
	public Response setCookie(String name, String value) {
		setCookiesEndpointWithValues = ENDPOINT
				.concat("/")
				.concat(name)
				.concat("/")
				.concat(value);
		
		return DriverManager.getDriverWebAPI()
				.cookie(cookie)
				.when()
				.get(setCookiesEndpointWithValues);
	}
	
	public String getEndpoint() {
		return setCookiesEndpointWithValues == null ? ENDPOINT : setCookiesEndpointWithValues;
	}
}
