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
	
	private String setCookiesEndpointWithValues;
	
	public Response setCookie(String name, String value) {
		Cookie myCookie = new Cookie.Builder("session_id", "112").setSecured(true)
				.setComment("session id cookie")
				.build();
		
		setCookiesEndpointWithValues = ENDPOINT
				.concat("/")
				.concat(name)
				.concat("/")
				.concat(value);
		
		return DriverManager.getDriverWebAPI()
				.cookie(myCookie)
				.when()
				.get(setCookiesEndpointWithValues);
	}
	
	public String getEndpoint() {
		return setCookiesEndpointWithValues == null ? ENDPOINT : setCookiesEndpointWithValues;
	}
}
