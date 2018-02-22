package com.capgemini.ntc.webapi.core.stubs;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.equalTo;
import static com.github.tomakehurst.wiremock.client.WireMock.matchingXPath;
import static com.github.tomakehurst.wiremock.client.WireMock.post;
import static com.github.tomakehurst.wiremock.client.WireMock.urlEqualTo;

import com.capgemini.ntc.webapi.core.base.driver.DriverManager;

public class StubSOAP {
	
	// required parameters
	private String endpointURI;
	
	// optional parameters
	private int statusCode;
	
	public String getEndpointURI() {
		return endpointURI;
	}
	
	public int getStatusCode() {
		return statusCode;
	}
	
	private StubSOAP(StubBuilder builder) {
		this.endpointURI = builder.endpointURI;
		this.statusCode = builder.statusCode;
	}
	
	// Builder Class
	public static class StubBuilder {
		
		// required parameters
		private String endpointURI;
		
		// optional parameters
		private int		statusCode			= 200;
		private String	response			= "Hello";
		private String	requestXPathQuery	= "";
		
		public StubBuilder(String endpointURI) {
			this.endpointURI = endpointURI;
		}
		
		public StubBuilder setStatusCode(int statusCode) {
			this.statusCode = statusCode;
			return this;
		}
		
		public StubBuilder setResponse(String response) {
			this.response = response;
			return this;
		}
		
		public StubBuilder setRequestXPathQuery(String requestXPathQuery) {
			this.requestXPathQuery = requestXPathQuery;
			return this;
			
		}
		
		public StubSOAP build() {
			
			DriverManager.getDriver()
					.givenThat(
							// Given that request with ...
							post(urlEqualTo(this.endpointURI))
									.withHeader("Content-Type", equalTo("application/soap+xml"))
									.withRequestBody(matchingXPath(this.requestXPathQuery))
									// Return given response ...
									.willReturn(aResponse().withStatus(this.statusCode)
											.withBody(this.response))
									.withHeader("Content-Type", equalTo("application/soap+xml")));
			
			return new StubSOAP(this);
		}
		
	}
	
}
