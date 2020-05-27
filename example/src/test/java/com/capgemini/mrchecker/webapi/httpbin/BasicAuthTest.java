package com.capgemini.mrchecker.webapi.httpbin;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;
import org.junit.jupiter.api.BeforeAll;

import com.capgemini.mrchecker.core.groupTestCases.testSuites.tags.TestsWebApi;
import com.capgemini.mrchecker.test.core.logger.BFLogger;
import com.capgemini.mrchecker.webapi.BaseWebApiTest;
import com.capgemini.mrchecker.webapi.pages.httbin.BasicAuthPage;

import io.restassured.response.Response;

@TestsWebApi
public class BasicAuthTest extends BaseWebApiTest {
	
	private final String	validUsername	= "user";
	private final String	validPassword	= "passwd";
	
	private final String	invalidUsername	= "invalidUser";
	private final String	invalidPassword	= "invalidPassword";
	
	private static BasicAuthPage basicAuthPage = new BasicAuthPage();
	
	@BeforeAll
	public static void setUpClass() {
		basicAuthPage.initialize();
	}
	
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
