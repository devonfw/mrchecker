package com.capgemini.ntc.endpoint.rest;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import org.junit.BeforeClass;
import org.junit.Test;

import com.capgemini.ntc.test.core.BaseTest;
import com.capgemini.ntc.test.core.logger.BFLogger;
import com.capgemini.ntc.webapi.core.base.driver.DriverManager;
import com.capgemini.ntc.webapi.endpoint.stubs.StubREST_Builder;
import com.github.tomakehurst.wiremock.WireMockServer;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class REST_FarenheitToCelsiusMethod_Test extends BaseTest {
	
	private static String endpointBaseUri;
	
	@BeforeClass
	public static void beforeClass() {
		
		// Start Virtual Server
		WireMockServer driverVirtualService = DriverManager.getDriverVirtualService();
		
		// Get Virtual Server running http and https ports
		int httpPort = driverVirtualService.port();
		int httpsPort = driverVirtualService.httpsPort();
		
		// Print is Virtual server running
		BFLogger.logDebug("Is Virtual server running: " + driverVirtualService.isRunning());
		
		String baseURI = "http://localhost";
		endpointBaseUri = baseURI + ":" + httpPort;
	}
	
	@Override
	public void setUp() {
		/*
		 * ----------
		 * Mock response. Map request with virtual asset from file
		 * -----------
		 */
		BFLogger.logInfo("#1 Create Stub content message");
		BFLogger.logInfo("#2 Add resource to wiremock server");
		new StubREST_Builder.StubBuilder("/some/thing")
						.setResponse("{ \"FahrenheitToCelsiusResponse\":{\"FahrenheitToCelsiusResult\":37.7777777777778}}")
						.setStatusCode(200)
						.build();
		
	}
	
	@Override
	public void tearDown() {
	}
	
	@Test
	public void testRestMock_UrlExists() {
		
		BFLogger.logInfo("#3 Send request to generated stub");
		Response response = DriverManager.getDriverWebAPI()
						.with()
						.header("Content-Type", ContentType.JSON.toString())
						.log()
						.all()
						.when()
						.get(endpointBaseUri + "/some/thing")
						.thenReturn();
		
		BFLogger.logInfo("#4 Validate response ");
		BFLogger.logDebug("/some/thing: " + response.jsonPath()
						.prettyPrint());
		assertThat(response.statusCode(), is(200));
	}
	
	@Test
	public void testRestMock_UrlDoesNotExist() throws Exception {
		
		BFLogger.logInfo("#3 Send request to generated stub");
		Response response = DriverManager.getDriverWebAPI()
						.with()
						.header("Content-Type", ContentType.JSON.toString())
						.log()
						.all()
						.when()
						.get(endpointBaseUri + "/some/thing/else")
						.thenReturn();
		
		BFLogger.logInfo("#4 Validate response ");
		assertThat(response.statusCode(), is(404));
		
	}
	
	@Test
	public void testRestMock_post() throws Exception {
		
		BFLogger.logInfo("#3 Send request to generated stub");
		Response response = DriverManager.getDriverWebAPI()
						.with()
						.header("Content-Type", ContentType.JSON.toString())
						.body("{\"FahrenheitToCelsius\":{\"Fahrenheit\":10}}")
						.log()
						.all()
						.when()
						.post(endpointBaseUri + "/some/thing")
						.thenReturn();
		
		BFLogger.logInfo("#4 Validate response ");
		BFLogger.logDebug("/some/thing: " + response.jsonPath()
						.prettyPrint());
		assertThat(response.statusCode(), is(200));
	}
	
}
