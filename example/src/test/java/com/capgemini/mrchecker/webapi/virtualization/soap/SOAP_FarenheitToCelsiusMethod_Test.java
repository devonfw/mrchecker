package com.capgemini.mrchecker.webapi.virtualization.soap;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import java.io.IOException;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.capgemini.mrchecker.core.groupTestCases.testSuites.tags.TestsWebApi;
import com.capgemini.mrchecker.test.core.BaseTest;
import com.capgemini.mrchecker.test.core.logger.BFLogger;
import com.capgemini.mrchecker.webapi.core.base.driver.DriverManager;
import com.capgemini.mrchecker.webapi.virtualization.stubs.StubSOAP_Builder;

import io.restassured.response.Response;

@TestsWebApi
public class SOAP_FarenheitToCelsiusMethod_Test extends BaseTest {
	
	@BeforeAll
	public static void beforeClass() {
		// DriverManager.clearAllDrivers();
		//
		// // Start Virtual Server
		// BFLogger.logInfo("#1 Start wiremock server");
		// WireMock driverVirtualService = DriverManager.getDriverVirtualService();
		//
		// // Get Virtual Server running http port
		// int httpPort = DriverManager.getHttpPort();
		// String baseURI = DriverManager.getHttpHost();
		// endpointBaseUri = baseURI + ":" + httpPort;
		//
		// RestAssured.config = new RestAssuredConfig().encoderConfig(new
		// EncoderConfig().appendDefaultContentCharsetToContentTypeIfUndefined(false));
		
	}
	
	@Override
	public void setUp() {
	}
	
	@Override
	public void tearDown() {
	}
	
	@Test
	public void testSoapMessageFromFileWithVitualResponse() throws IOException {
		
		BFLogger.logInfo("#2 Create Stub content message");
		final String responseMessage = new FarenheitToCelsiusMethod_Response_FromFile().getMessage();
		String requestXPathQuery = "//soap12:Envelope | //soap12:Body | //FahrenheitToCelsius | //Fahrenheit";
		
		/*
		 * ----------
		 * Mock response. Map request with virtual asset from file
		 * -----------
		 */
		BFLogger.logInfo("#3 Add resource to wiremock server");
		String endpointUriRegExp = "/tempconvert.asmx.*";
		StubSOAP_Builder stubSOAP_builder = new StubSOAP_Builder.StubBuilder(endpointUriRegExp)
				.setRequestXPathQuery(requestXPathQuery)
				.setResponse(responseMessage)
				.setStatusCode(200)
				.build();
		
		// Get Virtual Server running endpoint URI
		String endpointBaseUri = stubSOAP_builder.getEndpointBaseUri();
		
		/*
		 * ----------
		 * Time to validate virtual response
		 * -----------
		 */
		BFLogger.logInfo("#4 Send request to generated stub");
		String endpointUri = "/tempconvert.asmx";
		Response response = DriverManager.getDriverWebAPI()
				.with()
				.contentType("application/soap+xml")
				.body(new FarenheitToCelsiusMethod_Request_FromCode()
						.setFahrenheit(30)
						.setSmth("Hello")
						.getMessage())
				.log()
				.all()
				.when()
				.post(endpointBaseUri + endpointUri)
				.thenReturn();
		
		BFLogger.logInfo("#5 Validate reposponse ");
		BFLogger.logDebug("NEW RESPONSE /tempconvert.asmx?op=FahrenheitToCelsius: " + response.xmlPath()
				.prettyPrint());
		assertThat(response.statusCode(), is(200));
	}
	
	@Test
	public void testSoapMessageFromClassObjectWithVitualResponse() throws Exception {
		BFLogger.logInfo("#1 Start wiremock server");
		DriverManager.getDriverVirtualService();
		
		BFLogger.logInfo("#2 Create Stub content message");
		final String responseMessage = new FarenheitToCelsiusMethod_Response_FromCode().setFahrenheitToCelsiusResult(37.8888)
				.getMessage();
		String requestXPathQuery = "//soap12:Envelope | //soap12:Body | //FahrenheitToCelsius | //Fahrenheit";
		
		/*
		 * ----------
		 * Mock response. Map request with virtual asset from Object SOAP representation
		 * -----------
		 */
		BFLogger.logInfo("#3 Add resource to wiremock server");
		String endpointUriRegExp = "/tempconvert.asmx\\?op=FahrenheitToCelsius";
		
		StubSOAP_Builder stubSOAP_builder = new StubSOAP_Builder.StubBuilder(endpointUriRegExp)
				.setRequestXPathQuery(requestXPathQuery)
				.setResponse(responseMessage)
				.setStatusCode(200)
				.build();
		
		// Get Virtual Server running endpoint URI
		String endpointBaseUri = stubSOAP_builder.getEndpointBaseUri();
		
		/*
		 * ----------
		 * Time to validate virtual response
		 * -----------
		 */
		BFLogger.logInfo("#4 Send request to generated stub");
		String endpointUri = "/tempconvert.asmx?op=FahrenheitToCelsius";
		Response response = DriverManager.getDriverWebAPI()
				.with()
				.contentType("application/soap+xml")
				.body(new FarenheitToCelsiusMethod_Request_FromFile().getMessage())
				.log()
				.all()
				.when()
				.post(endpointBaseUri + endpointUri)
				.thenReturn();
		
		BFLogger.logInfo("#5 Validate reposponse ");
		BFLogger.logDebug("NEW RESPONSE /tempconvert.asmx?op=FahrenheitToCelsius: " + response.xmlPath()
				.prettyPrint());
		assertThat(response.statusCode(), is(200));
	}
	
}
