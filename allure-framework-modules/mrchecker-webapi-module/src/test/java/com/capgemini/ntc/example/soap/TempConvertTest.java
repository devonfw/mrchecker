package com.capgemini.ntc.example.soap;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.urlEqualTo;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import java.io.IOException;

import org.junit.Test;

import com.capgemini.ntc.example.soap.tempconvert.FarenheitToCelsiusMethod;
import com.capgemini.ntc.example.soap.tempconvert.Stub;
import com.capgemini.ntc.test.core.BaseTest;
import com.capgemini.ntc.test.core.logger.BFLogger;
import com.capgemini.ntc.webapi.core.base.driver.DriverManager;
import com.capgemini.ntc.webapi.testsupport.TestHttpHeader;
import com.capgemini.ntc.webapi.testsupport.WireMockResponse;
import com.capgemini.ntc.webapi.testsupport.WireMockTestClient;

public class TempConvertTest extends BaseTest {
	
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
	
	@Test
	public void test1() throws IOException {
		
		BFLogger.logInfo("#1 Start wiremock server");
		
		BFLogger.logInfo("#2 Create Stub message");
		FarenheitToCelsiusMethod farenheitToCelsiusMethod = new FarenheitToCelsiusMethod();
		BFLogger.logDebug(farenheitToCelsiusMethod.fromFile_response());
		
		String conversionEndpointURI = "/tempconvert.asmx?op=FahrenheitToCelsius";
		String requestXPathQuery = "//soap12:Envelope | //soap12:Body | //FahrenheitToCelsius | //Fahrenheit";
		
		BFLogger.logInfo("#3 Add resource to wiremock server");
		new Stub.StubBuilder(conversionEndpointURI).setStatusCode(200)
				.build();
		
		BFLogger.logInfo("#4 Send request to generated stub");
		// SoapClient client = SoapClient.builder()
		// .endpointUri(WIREMOCK_BASE_URI + conversionEndpointURI)
		// .build();
		// client.post(requestEnvelope);
		
		BFLogger.logInfo("#5 Validate reposponse ");
		
		WireMockTestClient testClient = new WireMockTestClient(DriverManager.getDriver()
				.port());
		
		WireMockResponse postXml = testClient.postXml("/tempconvert.asmx?op=FahrenheitToCelsius", farenheitToCelsiusMethod.fromFile_request(),
				new TestHttpHeader("Content-Type", "application/soap+xml"));
		BFLogger.logDebug("RESPONSE /tempconvert.asmx?op=FahrenheitToCelsius: " + postXml.content());
		assertThat(postXml.statusCode(), is(200));
		
		// DriverManager.getDriver()
		// .verify(postRequestedFor(urlEqualTo(conversionEndpointURI)).withRequestBody(matchingXPath(requestXPathQuery)));
		//
	}
	
	@Test
	public void test2() throws Exception {
		FarenheitToCelsiusMethod farenheitToCelsiusMethod = new FarenheitToCelsiusMethod();
		System.out.println(farenheitToCelsiusMethod.fromFile_request());
		
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
