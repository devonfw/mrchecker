package com.capgemini.mrchecker.webapi.virtualization.stubs;

import static com.github.tomakehurst.wiremock.client.WireMock.*;

import com.capgemini.mrchecker.webapi.core.base.driver.DriverManager;
import com.github.tomakehurst.wiremock.client.WireMock;

public class StubSOAP_Builder {
	
	// required parameters
	private String endpointURI;
	
	// optional parameters
	private int statusCode;
	
	// Driver config
	private final String endpointBaseUri;
	
	public String getEndpointURI() {
		return endpointURI;
	}
	
	public int getStatusCode() {
		return statusCode;
	}
	
	public String getEndpointBaseUri() {
		return this.endpointBaseUri;
	}
	
	private StubSOAP_Builder(StubBuilder builder) {
		this.endpointURI = builder.endpointURI;
		this.statusCode = builder.statusCode;
		this.endpointBaseUri = builder.endpointBaseUri;
	}
	
	// Builder Class
	public static class StubBuilder {
		
		// required parameters
		private String endpointURI;
		
		// optional parameters
		private int		statusCode			= 200;
		private String	response			= "Hello";
		private String	requestXPathQuery	= "";
		
		/// Driver config
		private String endpointBaseUri;
		
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
			
			// Bind all stubbers to running WireMock client connection
			WireMock driver = DriverManager.getDriverVirtualService();
			this.endpointBaseUri = DriverManager.getEndpointBaseUri();
			WireMock.configureFor(driver);
			
			// GET
			WireMock
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
			WireMock
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
			WireMock
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
			WireMock
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
			WireMock
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