package com.capgemini.ntc.webapi.core.stubs;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.equalTo;
import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.urlEqualTo;

import com.capgemini.ntc.webapi.core.base.driver.DriverManager;

import io.restassured.http.ContentType;

public class StubREST_Builder {
	
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
	
	private StubREST_Builder(StubBuilder builder) {
		this.endpointURI = builder.endpointURI;
		this.statusCode = builder.statusCode;
	}
	
	// Builder Class
	public static class StubBuilder {
		
		// required parameters
		private String endpointURI;
		
		// optional parameters
		private int		statusCode	= 200;
		private String	response	= "{ \"message\": \"Hello\" }";
		
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
		
		public StubREST_Builder build() {
			
			DriverManager.getDriverVirtualService()
					.givenThat(
							// Given that request with ...
							get(urlEqualTo(this.endpointURI))
									.withHeader("Content-Type", equalTo(ContentType.JSON.toString()))
									// Return given response ...
									.willReturn(aResponse()
											.withStatus(this.statusCode)
											.withHeader("Content-Type", ContentType.JSON.toString())
											.withBody(this.response)));
			
			return new StubREST_Builder(this);
		}
		
	}
	
}
