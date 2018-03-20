package com.capgemini.ntc.example.soap;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import java.io.IOException;

import org.junit.Test;

import com.capgemini.ntc.example.soap.tempconvert.FarenheitToCelsiusMethod;
import com.capgemini.ntc.test.core.BaseTest;
import com.capgemini.ntc.test.core.logger.BFLogger;
import com.capgemini.ntc.webapi.core.base.driver.DriverManager;
import com.capgemini.ntc.webapi.core.stubs.StubSOAP;
import com.capgemini.ntc.webapi.wiremock.TestHttpHeader;
import com.capgemini.ntc.webapi.wiremock.WireMockResponse;
import com.capgemini.ntc.webapi.wiremock.WireMockTestClient;

public class SOAPTempConvertTest extends BaseTest {
	
	@Test
	public void testSoapMessageFromFileWithVitualResponse() throws IOException {
		
		BFLogger.logInfo("#1 Start wiremock server");
		DriverManager.getDriver();
		
		BFLogger.logInfo("#2 Create Stub content message");
		FarenheitToCelsiusMethod farenheitToCelsiusMethod = new FarenheitToCelsiusMethod();
		String endpointURI = "/tempconvert.asmx?op=FahrenheitToCelsius";
		String requestXPathQuery = "//soap12:Envelope | //soap12:Body | //FahrenheitToCelsius | //Fahrenheit";
		
		/*
		 * ----------
		 * Mock response. Map request with virtual asset from file
		 * -----------
		 */
		BFLogger.logInfo("#3 Add resource to wiremock server");
		new StubSOAP.StubBuilder(endpointURI)
						.setRequestXPathQuery(requestXPathQuery)
						.setResponse(farenheitToCelsiusMethod.fromFile_response())
						.setStatusCode(200)
						.build();
		
		/*
		 * ----------
		 * Time to validate virtualized response
		 * -----------
		 */
		
		BFLogger.logInfo("#4 Send request to generated stub");
		// TASK: Switch from WireMockTestClient to RestAssure
		WireMockTestClient testClient = new WireMockTestClient(DriverManager.getDriver()
						.port());
		WireMockResponse postXml = testClient.postXml(endpointURI,
						farenheitToCelsiusMethod.fromFile_request(),
						new TestHttpHeader("Content-Type", "application/soap+xml"));
		
		BFLogger.logInfo("#5 Validate reposponse ");
		BFLogger.logDebug("RESPONSE /tempconvert.asmx?op=FahrenheitToCelsius: \n" + postXml.content());
		assertThat(postXml.statusCode(), is(200));
	}
	
	@Test
	public void testSoapMessageFromClassObjectWithVitualResponse() throws Exception {
		BFLogger.logInfo("#1 Start wiremock server");
		DriverManager.getDriver();
		
		BFLogger.logInfo("#2 Create Stub content message");
		FarenheitToCelsiusMethod farenheitToCelsiusMethod = new FarenheitToCelsiusMethod();
		String endpointURI = "/tempconvert.asmx?op=FahrenheitToCelsius";
		String requestXPathQuery = "//soap12:Envelope | //soap12:Body | //FahrenheitToCelsius | //Fahrenheit";
		
		/*
		 * ----------
		 * Mock response. Map request with virtual asset from Object SOAP representation
		 * -----------
		 */
		BFLogger.logInfo("#3 Add resource to wiremock server");
		new StubSOAP.StubBuilder(endpointURI)
						.setRequestXPathQuery(requestXPathQuery)
						.setResponse(farenheitToCelsiusMethod.setFahrenheitToCelsiusResult(37.8888)
										.fromCode_response())
						.setStatusCode(200)
						.build();
		
		/*
		 * ----------
		 * Time to validate virtualized response
		 * -----------
		 */
		BFLogger.logInfo("#4 Send request to generated stub");
		// TASK: Switch from WireMockTestClient to RestAssure
		WireMockTestClient testClient = new WireMockTestClient(DriverManager.getDriver()
						.port());
		WireMockResponse postXml = testClient.postXml(endpointURI,
						farenheitToCelsiusMethod.fromFile_request(),
						new TestHttpHeader("Content-Type", "application/soap+xml"));
		
		BFLogger.logInfo("#5 Validate reposponse ");
		BFLogger.logDebug("RESPONSE /tempconvert.asmx?op=FahrenheitToCelsius: " + postXml.content());
		assertThat(postXml.statusCode(), is(200));
		
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
