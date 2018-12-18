package com.capgemini.mrchecker.webapi.httpbin;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

import com.capgemini.mrchecker.test.core.logger.BFLogger;
import com.capgemini.mrchecker.webapi.pages.httbin.SimpleGETPage;

import io.restassured.response.Response;

public class SimpleGETTest extends com.capgemini.mrchecker.webapi.BasePageWebApiTest {
	
	private SimpleGETPage simpleGETPage;
	
	@Test
	public void sendSimpleGETQuery() {
		simpleGETPage = new SimpleGETPage();
		
		BFLogger.logInfo("Step 1 - Sending GET query to " + simpleGETPage.getEndpoint());
		Response response = simpleGETPage.sendGETQuery();
		
		BFLogger.logInfo("Step 2 - Validate response status code: ");
		assertThat(response.statusCode(), is(200));
	}
	
}
