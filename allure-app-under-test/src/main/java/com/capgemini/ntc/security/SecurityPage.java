package com.capgemini.ntc.security;

import static io.restassured.RestAssured.given;

import java.util.HashMap;
import java.util.Map;

import org.json.JSONObject;

import com.capgemini.ntc.security.core.BasePage;
import com.google.common.base.Preconditions;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.parsing.Parser;
import io.restassured.specification.RequestSpecification;

public abstract class SecurityPage extends BasePage {
	
	private static final String						AUTHORIZATION_HEADER	= "Authorization";
	private static final Map<SessionEnum, Headers>	authData				= new HashMap<>();
	
	{
		authenticateSession(SessionEnum.WAITER, EnvironmentParam.SECURITY_USER1_NAME, EnvironmentParam.SECURITY_USER1_PASSWD);
	}
	
	private void authenticateSession(SessionEnum session, EnvironmentParam user, EnvironmentParam password) {
		RestAssured.defaultParser = Parser.TEXT;
		
		JSONObject request = new JSONObject();
		request.put("username", user);
		request.put("password", password);
		
		RequestSpecification rs = new RequestSpecBuilder()
						.setBody(request.toString())
						.setBaseUri(EnvironmentParam.SECURITY_SERVER_ORIGIN.getValue())
						.setBasePath(SubUrlEnum.LOGIN.getValue())
						.build();
		Headers headers = given(rs)
						.when()
						.post()
						.getHeaders();
		
		if (!headers.hasHeaderWithName(AUTHORIZATION_HEADER)) {
			throw new RuntimeException("No authorization header found. "
							+ "Expected a header 'Authorization' holding a Bearer token.");
		}
		
		Header authHeader = headers.get(AUTHORIZATION_HEADER);
		Headers authHeaders = new Headers(authHeader);
		authData.put(session, authHeaders);
	}
	
	public RequestSpecBuilder initBuilder(SessionEnum session) {
		Preconditions.checkNotNull(session);
		if (!authData.containsKey(session)) {
			return initBuilder();
		}
		
		RequestSpecBuilder result = new RequestSpecBuilder();
		for (Header header : authData.get(session)
						.asList()) {
			result.addHeader(header.getName(), header.getValue());
		}
		return result;
	}
	
	public RequestSpecBuilder initBuilder() {
		return new RequestSpecBuilder();
	}
	
	public Headers getAuthHeaders(SessionEnum session) {
		return authData.get(session);
	}
}
