package com.capgemini.mrchecker.webapi.virtualization.stubs;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.any;
import static com.github.tomakehurst.wiremock.client.WireMock.anyUrl;
import static com.github.tomakehurst.wiremock.client.WireMock.delete;
import static com.github.tomakehurst.wiremock.client.WireMock.equalTo;
import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.post;
import static com.github.tomakehurst.wiremock.client.WireMock.put;
import static com.github.tomakehurst.wiremock.client.WireMock.urlMatching;

import java.util.UUID;

import com.capgemini.mrchecker.test.core.logger.BFLogger;
import com.capgemini.mrchecker.webapi.core.base.driver.DriverManager;

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
			UUID id;
			
			// GET
			id = DriverManager.getDriverVirtualService()
					.givenThat(
							// Given that request with ...
							get(urlMatching(this.endpointURI))
									.withHeader("Content-Type", equalTo(ContentType.JSON.toString()))
									// Return given response ...
									.willReturn(aResponse()
											.withStatus(this.statusCode)
											.withHeader("Content-Type", ContentType.JSON.toString())
											.withBody(this.response)
											.withTransformers("body-transformer")))
					.getId();
			BFLogger.logDebug("Mapped GET with ID=" + id.toString());
			
			// POST
			id = DriverManager.getDriverVirtualService()
					.givenThat(
							// Given that request with ...
							post(urlMatching(this.endpointURI))
									.withHeader("Content-Type", equalTo(ContentType.JSON.toString()))
									// Return given response ...
									.willReturn(aResponse()
											.withStatus(this.statusCode)
											.withHeader("Content-Type", ContentType.JSON.toString())
											.withBody(this.response)
											.withTransformers("body-transformer")))
					.getId();
			BFLogger.logDebug("Mapped POST with ID=" + id.toString());
			
			// PUT
			id = DriverManager.getDriverVirtualService()
					.givenThat(
							// Given that request with ...
							put(urlMatching(this.endpointURI))
									.withHeader("Content-Type", equalTo(ContentType.JSON.toString()))
									// Return given response ...
									.willReturn(aResponse()
											.withStatus(this.statusCode)
											.withHeader("Content-Type", ContentType.JSON.toString())
											.withBody(this.response)
											.withTransformers("body-transformer")))
					.getId();
			BFLogger.logDebug("Mapped PUT with ID=" + id.toString());
			
			// DELETE
			id = DriverManager.getDriverVirtualService()
					.givenThat(
							// Given that request with ...
							delete(urlMatching(this.endpointURI))
									.withHeader("Content-Type", equalTo(ContentType.JSON.toString()))
									// Return given response ...
									.willReturn(aResponse()
											.withStatus(this.statusCode)
											.withHeader("Content-Type", ContentType.JSON.toString())
											.withBody(this.response)
											.withTransformers("body-transformer")))
					.getId();
			BFLogger.logDebug("Mapped DELETE with ID=" + id.toString());
			
			// CATCH any other requests
			id = DriverManager.getDriverVirtualService()
					.givenThat(
							any(anyUrl())
									.atPriority(10)
									.willReturn(aResponse()
											.withStatus(404)
											.withHeader("Content-Type", ContentType.JSON.toString())
											.withBody("{\"status\":\"Error\",\"message\":\"Endpoint not found\"}")
											.withTransformers("body-transformer")))
					.getId();
			BFLogger.logDebug("Mapped AnyOther with ID=" + id.toString());
			
			return new StubREST_Builder(this);
		}
		
	}
	
}
