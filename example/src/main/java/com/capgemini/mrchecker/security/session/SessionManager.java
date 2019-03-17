package com.capgemini.mrchecker.security.session;

import static io.restassured.RestAssured.given;

import java.util.HashMap;
import java.util.Map;

import org.json.JSONObject;

import com.capgemini.mrchecker.security.EnvironmentParam;
import com.capgemini.mrchecker.security.SubUrlEnum;
import com.capgemini.mrchecker.security.core.BasePage;
import com.google.common.base.Preconditions;
import com.google.inject.Singleton;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.parsing.Parser;
import io.restassured.specification.RequestSpecification;

@Singleton
public class SessionManager extends BasePage implements ISessionManager {
	
	/**
	 * Name of the header used to perform request authorization.
	 */
	private static final String AUTHORIZATION_HEADER = "Authorization";
	
	/**
	 * Map to hold all session relevant data.
	 */
	private static final Map<SessionEnum, Headers> authData = new HashMap<>();
	
	/**
	 * Default constructor. Should authenticate all sessions required by all security tests.
	 */
	public SessionManager() {
		authenticateSession(SessionEnum.WAITER, EnvironmentParam.SECURITY_USER1_NAME, EnvironmentParam.SECURITY_USER1_PASSWD);
	}
	
	/**
	 * Authenticates a single user session and stores the session related identifiers (headers) localy.
	 * 
	 * @param session
	 *          Session name.
	 * @param user
	 *          User name.
	 * @param password
	 *          User password.
	 */
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
	
	/*
	 * (non-Javadoc)
	 * @see com.capgemini.ntc.security.ISessionManager#initBuilder(com.capgemini.ntc.security.SessionEnum)
	 */
	@Override
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
	
	/*
	 * (non-Javadoc)
	 * @see com.capgemini.ntc.security.ISessionManager#initBuilder()
	 */
	@Override
	public RequestSpecBuilder initBuilder() {
		return new RequestSpecBuilder();
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.capgemini.ntc.security.ISessionManager#getAuthHeaders(com.capgemini.ntc.security.SessionEnum)
	 */
	@Override
	public Headers getAuthHeaders(SessionEnum session) {
		return authData.get(session);
	}
}
