package com.capgemini.mrchecker.webapi.virtualization.stubs;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.any;
import static com.github.tomakehurst.wiremock.client.WireMock.anyUrl;
import static com.github.tomakehurst.wiremock.client.WireMock.delete;
import static com.github.tomakehurst.wiremock.client.WireMock.equalTo;
import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.matchingXPath;
import static com.github.tomakehurst.wiremock.client.WireMock.post;
import static com.github.tomakehurst.wiremock.client.WireMock.put;
import static com.github.tomakehurst.wiremock.client.WireMock.urlMatching;

import com.capgemini.mrchecker.webapi.core.base.driver.DriverManager;

public class StubSOAP_Builder {
	
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
	
	private StubSOAP_Builder(StubBuilder builder) {
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
		
		public StubSOAP_Builder build() {
			
			// GET
			DriverManager.getDriverVirtualService()
					.givenThat(
							// Given that request with ...
							get(urlMatching(this.endpointURI))
									.withHeader("Content-Type", equalTo("application/soap+xml"))
									.withRequestBody(matchingXPath(this.requestXPathQuery))
									// Return given response ...
									.willReturn(aResponse().withStatus(this.statusCode)
											.withStatus(this.statusCode)
											.withHeader("Content-Type", "application/soap+xml")
											.withBody(this.response)
											.withTransformers("body-transformer")));
			
			// POST
			DriverManager.getDriverVirtualService()
					.givenThat(
							// Given that request with ...
							// post(urlEqualTo(this.endpointURI))
							post(urlMatching(this.endpointURI))
									.withHeader("Content-Type", equalTo("application/soap+xml"))
									.withRequestBody(matchingXPath(this.requestXPathQuery))
									// Return given response ...
									.willReturn(aResponse().withStatus(this.statusCode)
											.withStatus(this.statusCode)
											.withHeader("Content-Type", "application/soap+xml")
											.withBody(this.response)
											.withTransformers("body-transformer")));
			
			// PUT
			DriverManager.getDriverVirtualService()
					.givenThat(
							// Given that request with ...
							put(urlMatching(this.endpointURI))
									.withHeader("Content-Type", equalTo("application/soap+xml"))
									.withRequestBody(matchingXPath(this.requestXPathQuery))
									// Return given response ...
									.willReturn(aResponse().withStatus(this.statusCode)
											.withStatus(this.statusCode)
											.withHeader("Content-Type", "application/soap+xml")
											.withBody(this.response)
											.withTransformers("body-transformer")));
			
			// DELETE
			DriverManager.getDriverVirtualService()
					.givenThat(
							// Given that request with ...
							delete(urlMatching(this.endpointURI))
									.withHeader("Content-Type", equalTo("application/soap+xml"))
									.withRequestBody(matchingXPath(this.requestXPathQuery))
									// Return given response ...
									.willReturn(aResponse().withStatus(this.statusCode)
											.withStatus(this.statusCode)
											.withHeader("Content-Type", "application/soap+xml")
											.withBody(this.response)
											.withTransformers("body-transformer")));
			
			// CATCH any other requests
			DriverManager.getDriverVirtualService()
					.givenThat(
							any(anyUrl())
									.atPriority(10)
									.willReturn(aResponse()
											.withStatus(404)
											.withHeader("Content-Type", "application/soap+xml")
											.withBody("<status><error_message>Endpoint not found</error_message></status>")
											.withTransformers("body-transformer")));
			
			return new StubSOAP_Builder(this);
		}
		
	}
	
}
