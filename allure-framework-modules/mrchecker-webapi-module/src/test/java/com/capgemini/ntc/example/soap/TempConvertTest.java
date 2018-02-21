package com.capgemini.ntc.example.soap;

import static com.github.tomakehurst.wiremock.client.WireMock.matchingXPath;
import static com.github.tomakehurst.wiremock.client.WireMock.postRequestedFor;
import static com.github.tomakehurst.wiremock.client.WireMock.urlEqualTo;

import java.io.IOException;

import org.junit.Test;

import com.capgemini.ntc.example.soap.tempconvert.FarenheitToCelsiusMethod;
import com.capgemini.ntc.example.soap.tempconvert.Stub;
import com.capgemini.ntc.test.core.BaseTest;
import com.capgemini.ntc.test.core.logger.BFLogger;
import com.capgemini.ntc.webapi.core.base.driver.DriverManager;

public class TempConvertTest extends BaseTest {
	
	@Test
	public void test1() throws IOException {
		
		BFLogger.logInfo("#1 Start wiremock server");
		
		BFLogger.logInfo("#2 Create Stub message");
		FarenheitToCelsiusMethod farenheitToCelsiusMethod = new FarenheitToCelsiusMethod();
		System.out.println(farenheitToCelsiusMethod.fromFile_response());
		
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
		DriverManager.getDriver()
				.verify(postRequestedFor(urlEqualTo(conversionEndpointURI)).withRequestBody(matchingXPath(requestXPathQuery)));
		
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
