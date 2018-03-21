package com.capgemini.ntc.endpoint.soap;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import java.io.IOException;

import org.junit.BeforeClass;
import org.junit.Test;

import com.capgemini.ntc.endpoint.soap.FarenheitToCelsiusMethod_Request;
import com.capgemini.ntc.endpoint.soap.FarenheitToCelsiusMethod_Response;
import com.capgemini.ntc.test.core.BaseTest;
import com.capgemini.ntc.test.core.logger.BFLogger;
import com.capgemini.ntc.webapi.core.base.driver.DriverManager;
import com.capgemini.ntc.webapi.core.stubs.StubSOAP_Builder;

import io.restassured.RestAssured;
import io.restassured.config.EncoderConfig;
import io.restassured.config.RestAssuredConfig;
import io.restassured.response.Response;

public class FarenheitToCelsiusMethod_Test extends BaseTest {
	
	private static String endpointBaseUri;
	
	@BeforeClass
	public static void beforeClass() {
		String baseURI = "http://localhost";
		int port = DriverManager.getDriver()
				.port();
		endpointBaseUri = baseURI + ":" + port;
		RestAssured.config = new RestAssuredConfig().encoderConfig(new EncoderConfig().appendDefaultContentCharsetToContentTypeIfUndefined(false));
	}
	
	@Override
	public void setUp() {
	}
	
	@Override
	public void tearDown() {
	}
	
	@Test
	public void testSoapMessageFromFileWithVitualResponse() throws IOException {
		
		BFLogger.logInfo("#1 Start wiremock server");
		DriverManager.getDriver();
		
		BFLogger.logInfo("#2 Create Stub content message");
		FarenheitToCelsiusMethod_Response farenheitToCelsiusMethod_Response = new FarenheitToCelsiusMethod_Response();
		String endpointURI = "/tempconvert.asmx?op=FahrenheitToCelsius";
		String requestXPathQuery = "//soap12:Envelope | //soap12:Body | //FahrenheitToCelsius | //Fahrenheit";
		
		/*
		 * ----------
		 * Mock response. Map request with virtual asset from file
		 * -----------
		 */
		BFLogger.logInfo("#3 Add resource to wiremock server");
		new StubSOAP_Builder.StubBuilder(endpointURI)
				.setRequestXPathQuery(requestXPathQuery)
				.setResponse(farenheitToCelsiusMethod_Response.fromFile_response())
				.setStatusCode(200)
				.build();
		
		/*
		 * ----------
		 * Time to validate virtual response
		 * -----------
		 */
		BFLogger.logInfo("#4 Send request to generated stub");
		FarenheitToCelsiusMethod_Request farenheitToCelsiusMethod_Request = new FarenheitToCelsiusMethod_Request();
		Response response = given()
				.with()
				.contentType("application/soap+xml")
				.body(farenheitToCelsiusMethod_Request.fromFile_request())
				.log()
				.all()
				.when()
				.post(endpointBaseUri + endpointURI)
				.thenReturn();
		
		BFLogger.logInfo("#5 Validate reposponse ");
		BFLogger.logDebug("NEW RESPONSE /tempconvert.asmx?op=FahrenheitToCelsius: " + response.asString());
		assertThat(response.statusCode(), is(200));
	}
	
	@Test
	public void testSoapMessageFromClassObjectWithVitualResponse() throws Exception {
		BFLogger.logInfo("#1 Start wiremock server");
		DriverManager.getDriver();
		
		BFLogger.logInfo("#2 Create Stub content message");
		FarenheitToCelsiusMethod_Response farenheitToCelsiusMethod_Response = new FarenheitToCelsiusMethod_Response();
		String endpointURI = "/tempconvert.asmx?op=FahrenheitToCelsius";
		String requestXPathQuery = "//soap12:Envelope | //soap12:Body | //FahrenheitToCelsius | //Fahrenheit";
		
		/*
		 * ----------
		 * Mock response. Map request with virtual asset from Object SOAP representation
		 * -----------
		 */
		BFLogger.logInfo("#3 Add resource to wiremock server");
		new StubSOAP_Builder.StubBuilder(endpointURI)
				.setRequestXPathQuery(requestXPathQuery)
				.setResponse(farenheitToCelsiusMethod_Response.setFahrenheitToCelsiusResult(37.8888)
						.fromCode_response())
				.setStatusCode(200)
				.build();
		
		/*
		 * ----------
		 * Time to validate virtual response
		 * -----------
		 */
		BFLogger.logInfo("#4 Send request to generated stub");
		FarenheitToCelsiusMethod_Request farenheitToCelsiusMethod_Request = new FarenheitToCelsiusMethod_Request();
		Response response = given()
				.with()
				.contentType("application/soap+xml")
				.body(farenheitToCelsiusMethod_Request.fromFile_request())
				.log()
				.all()
				.when()
				.post(endpointBaseUri + endpointURI)
				.thenReturn();
		
		BFLogger.logInfo("#5 Validate reposponse ");
		BFLogger.logDebug("NEW RESPONSE /tempconvert.asmx?op=FahrenheitToCelsius: " + response.asString());
		assertThat(response.statusCode(), is(200));
	}
	
}
