package com.capgemini.mrchecker.webapi.example.base;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.Map;

import com.capgemini.mrchecker.test.core.logger.BFLogger;
import com.capgemini.mrchecker.webapi.core.BaseEndpoint;
import com.capgemini.mrchecker.webapi.core.base.driver.DriverManager;
import com.github.tomakehurst.wiremock.client.WireMock;

import io.qameta.allure.Step;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.ExtractableResponse;

public abstract class BaseEndpointWebAPI extends BaseEndpoint {
	private static String	bearerToken;			// Remove if token not needed
	private static boolean	isVirtualized	= false;
	private static String	virtualizedHost	= "";
	
	public static void startVirtualizeService() {
		if (!isVirtualized) {
			WireMock driver = DriverManager.getDriverVirtualService();
			WireMock.configureFor(driver);
			virtualizedHost = DriverManager.getEndpointBaseUri();
			BFLogger.logInfo("Stub mapping size: " + DriverManager.getDriverVirtualServerService()
					.getStubMappings()
					.size());
			isVirtualized = true;
		} else {
			fail("Service cannot be started twice");
		}
	}
	
	public static String getToken() {
		return bearerToken;
	}
	
	public static void setToken(String token) {
		bearerToken = token;
	}
	
	@Step("Get entity: {path}")
	public static ExtractableResponse getFromHost(String host, String path) {
		
		return given()
				.filter(new AllureRestAssured())
				// .cookie(cookie)
				.when()
				.contentType("application/json")
				.get(host + path)
				.then()
				.statusCode(200)
				.extract();
	}
	
	@Step("Get entity: {path}")
	public static ExtractableResponse getFromHostLoggedUser(String host, String path) {
		
		return given()
				.filter(new AllureRestAssured())
				.headers(
						"Authorization",
						"Bearer " + bearerToken)
				.when()
				.contentType("application/json")
				.get(host + path)
				.then()
				.statusCode(200)
				.extract();
	}
	
	@Step("Get entity: {path}")
	public static ExtractableResponse getFromHostFail(String host, String path, int errorCode) {
		
		return given().filter(new AllureRestAssured())
				.when()
				.contentType("application/json")
				.get(host + path)
				.then()
				.statusCode(errorCode)
				.extract();
	}
	
	@Step("Create entity: {path}")
	public static ExtractableResponse postToHost(String host, String path, Object body) {
		return given().filter(new AllureRestAssured())
				.headers(
						"Authorization",
						"Bearer " + bearerToken)
				.when()
				.contentType("application/json")
				.body(body)
				.post(host + path)
				.then()
				.statusCode(200)
				.extract();
	}
	
	@Step("Create entity: {path}")
	public static ExtractableResponse postToHost(String host, String path, Object body, int statusCode) {
		return given().filter(new AllureRestAssured())
				.headers(
						"Authorization",
						"Bearer " + bearerToken)
				.when()
				.contentType("application/json")
				.body(body)
				.post(host + path)
				.then()
				.statusCode(statusCode)
				.extract();
	}
	
	@Step("Create entity: {path}")
	public static ExtractableResponse postToLoginHost(String host, String path, Object body) {
		return given()
				.when()
				.contentType("application/json")
				.body(body)
				.post(host + path)
				.then()
				.statusCode(200)
				.extract();
	}
	
	@Step("Create entity: {path}")
	public static ExtractableResponse postToHostFail(String host, String path, Object body, int errorCode) {
		return given().filter(new AllureRestAssured())
				.when()
				.contentType("application/json")
				.body(body)
				.post(host + path)
				.then()
				.statusCode(errorCode)
				.extract();
	}
	
	@Step("Create entity: {path}")
	public static ExtractableResponse postFormToHost(String host, String path, Map<String, String> params) {
		return given().filter(new AllureRestAssured())
				.contentType(ContentType.URLENC)
				.when()
				.formParams(params)
				.post(host + path)
				.then()
				.statusCode(200)
				.extract();
	}
	
	@Step("Get entity: {path}")
	public static ExtractableResponse getFromVirtualizedService(String path) {
		if (virtualizedHost.isEmpty()) {
			fail("Virtualization must be started first");
		}
		return given().filter(new AllureRestAssured())
				.when()
				.contentType("application/json")
				.get(virtualizedHost + path)
				.then()
				.statusCode(200)
				.extract();
	}
	
	@Step("Update entity: {path}")
	public static ExtractableResponse putToHost(String host, String path) {
		return given().filter(new AllureRestAssured())
				.when()
				.contentType("application/json")
				.put(host + path)
				.then()
				.statusCode(200)
				.extract();
	}
	
	@Step("Delete entity: {path}")
	public static ExtractableResponse deleteFromHost(String host, String path) {
		return given().filter(new AllureRestAssured())
				.when()
				.contentType("application/json")
				.delete(host + path)
				.then()
				.statusCode(200)
				.extract();
	}
	
	@Step("Delete entity: {path}")
	public static ExtractableResponse deleteFromHost(String host, String path, int statusCode) {
		return given().filter(new AllureRestAssured())
				.headers(
						"Authorization",
						"Bearer " + bearerToken)
				.when()
				.contentType("application/json")
				.delete(host + path)
				.then()
				.statusCode(statusCode)
				.extract();
	}
}
