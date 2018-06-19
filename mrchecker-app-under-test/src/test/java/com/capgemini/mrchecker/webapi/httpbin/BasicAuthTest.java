package com.capgemini.mrchecker.webapi.httpbin;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

import com.capgemini.mrchecker.test.core.logger.BFLogger;
import com.capgemini.mrchecker.webapi.pages.httbin.BasicAuthPage;

import io.restassured.response.Response;

public class BasicAuthTest extends com.capgemini.mrchecker.webapi.BasePageWebApiTest {
	
	private final String	validUsername	= "user";
	private final String	validPassword	= "passwd";
	
	private final String	invalidUsername	= "invalidUser";
	private final String	invalidPassword	= "invalidPassword";
	
	private BasicAuthPage basicAuthPage = new BasicAuthPage();
	
	@Test
	public void basicAuthSuccessfulLogin() {
		
		BFLogger.logInfo("Step 1 - Sending GET query to " + basicAuthPage.getEndpoint() +
				" with valid credentials: " + validUsername + ", " + validPassword);
		Response response = basicAuthPage.sendBasicAuthGETQuery(validUsername, validPassword);
		
		BFLogger.logInfo("Step 2 - Validate response status code (should be 200): ");
		assertThat(response.statusCode(), is(200));
	}
	
	@Test
	public void basicAuthFailedLogin() {
		
		BFLogger.logInfo("Step 1 - Sending GET query to " + basicAuthPage.getEndpoint() +
				" with invalid credentials: " + invalidUsername + ", " + invalidPassword);
		Response response = basicAuthPage.sendBasicAuthGETQuery(invalidUsername, invalidPassword);
		
		BFLogger.logInfo("Step 2 - Validate response status code (should be 401): ");
		assertThat(response.statusCode(), is(401));
	}
	
}
