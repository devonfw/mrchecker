package com.capgemini.ntc.example.soap.tempconvert;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.equalTo;
import static com.github.tomakehurst.wiremock.client.WireMock.matchingXPath;
import static com.github.tomakehurst.wiremock.client.WireMock.post;
import static com.github.tomakehurst.wiremock.client.WireMock.urlEqualTo;

import com.capgemini.ntc.webapi.core.base.driver.DriverManager;

public class Stub {
	
	// required parameters
	private String conversionEndpointURI;
	
	// optional parameters
	private int statusCode;
	
	public String getConversionEndpointURI() {
		return conversionEndpointURI;
	}
	
	public int getHttpStatusCode() {
		return statusCode;
	}
	
	private Stub(StubBuilder builder) {
		this.conversionEndpointURI = builder.conversionEndpointURI;
		this.statusCode = builder.statusCode;
	}
	
	// Builder Class
	public static class StubBuilder {
		
		// required parameters
		private String conversionEndpointURI;
		
		// optional parameters
		private int		statusCode;
		private String	response	= "JESTES WIELKI";
		
		public StubBuilder(String conversionEndpointURI) {
			this.conversionEndpointURI = conversionEndpointURI;
		}
		
		public StubBuilder setStatusCode(int statusCode) {
			this.statusCode = statusCode;
			return this;
		}
		
		public StubBuilder setResponse(String response) {
			this.response = response;
			return this;
		}
		
		public Stub build() {
			
			String conversionEndpointURI = "/tempconvert.asmx?op=FahrenheitToCelsius";
			String requestXPathQuery = "//soap12:Envelope | //soap12:Body | //FahrenheitToCelsius | //Fahrenheit";
			
			// wireMock.register(get(....)); // Equivalent to stubFor()
			
			DriverManager.getDriver()
					.stubFor(post(urlEqualTo(conversionEndpointURI)).withHeader("Content-Type",
							equalTo("application/soap+xml"))
							.withRequestBody(matchingXPath(requestXPathQuery))
							.willReturn(aResponse().withStatus(statusCode)
									.withBody(response))
							.withHeader("Content-Type", equalTo("application/soap+xml")));
			
			return new Stub(this);
		}
		
	}
	
}
