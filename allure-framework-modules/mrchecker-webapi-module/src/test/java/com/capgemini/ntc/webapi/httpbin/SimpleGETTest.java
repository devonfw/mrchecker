package com.capgemini.ntc.webapi.httpbin;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

import com.capgemini.ntc.test.core.logger.BFLogger;
import com.capgemini.ntc.webapi.BasePageWebApiTest;
import com.capgemini.ntc.webapi.pages.httbin.SimpleGETPage;

import io.restassured.response.Response;

public class SimpleGETTest extends BasePageWebApiTest {
	
	private SimpleGETPage simpleGETPage;
	
	@Test
	public void sendSimpleGETQuery() {
		simpleGETPage = new SimpleGETPage();
		
		BFLogger.logInfo("Step 1 - Send GET query to " + simpleGETPage.getEndpoint());
		Response response = simpleGETPage.sendGETQuery();
		
		BFLogger.logInfo("Step 2 - Validate response status code: ");
		assertThat(response.statusCode(), is(200));
	}
	
}
