package com.capgemini.ntc.webapi.httpbin;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

import com.capgemini.ntc.test.core.logger.BFLogger;
import com.capgemini.ntc.webapi.BasePageWebApiTest;
import com.capgemini.ntc.webapi.pages.httbin.SimplePOSTPage;

import io.restassured.response.Response;

public class SimplePOSTTest extends BasePageWebApiTest {
	
	private SimplePOSTPage simplePOSTPage;
	
	@Test
	public void sendSimplePOSTQuery() {
		simplePOSTPage = new SimplePOSTPage();
		
		BFLogger.logInfo("Step 1 - Sending POST query to " + simplePOSTPage.getEndpoint());
		Response response = simplePOSTPage.sendPOSTQuery();
		
		BFLogger.logInfo("Step 2 - Validate response status code: ");
		assertThat(response.statusCode(), is(200));
		
		BFLogger.logInfo("RESPONSE Body " + response.thenReturn()
						.body()
						.asString());
	}
	
}
