package com.capgemini.ntc.example.rest;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.urlEqualTo;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

import com.capgemini.ntc.test.core.BaseTest;
import com.capgemini.ntc.test.core.logger.BFLogger;
import com.capgemini.ntc.webapi.core.base.driver.DriverManager;
import com.capgemini.ntc.webapi.wiremock.WireMockTestClient;

public class RestExampleTest extends BaseTest {
	
	@Test
	public void exactUrlOnly() {
		DriverManager.getDriver()
				.givenThat(get(urlEqualTo("/some/thing"))
						.willReturn(aResponse()
								.withHeader("Content-Type", "text/plain")
								.withBody("Hello world!")));
		WireMockTestClient testClient = new WireMockTestClient(DriverManager.getDriver()
				.port());
		
		BFLogger.logDebug("RESPONSE /some/thing: " + testClient.get("/some/thing")
				.content()
				.toString());
		assertThat(testClient.get("/some/thing")
				.statusCode(), is(200));
		assertThat(testClient.get("/some/thing/else")
				.statusCode(), is(404));
	}
	
	@Override
	public void setUp() {
		// TASK Auto-generated method stub
		
	}
	
	@Override
	public void tearDown() {
		// TASK Auto-generated method stub
		
	}
	
}
