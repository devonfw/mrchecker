package com.capgemini.ntc.test_useCases;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

import com.capgemini.ntc.test.core.logger.BFLogger;
import com.capgemini.ntc.webapi.rest.models.SimpleGETServiceObjectModel;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class SimpleGETExampleTest {
	
	@Test
	public void sendSimpleGETQuery() {
		RequestSpecification requestSpecification = given();
		SimpleGETServiceObjectModel simpleGETServiceObjectModel = new SimpleGETServiceObjectModel(requestSpecification);
		
		BFLogger.logInfo("Step 1 - Send GET query to " + simpleGETServiceObjectModel.getServiceUrl());
		Response response = simpleGETServiceObjectModel.sendGETQuery();
		
		BFLogger.logInfo("Step 2 - Validate response: ");
		assertThat(response.statusCode(), is(200));
	}
	
}
