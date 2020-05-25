package com.capgemini.mrchecker.webapi.httpbin;

import com.capgemini.mrchecker.test.core.logger.BFLogger;
import com.capgemini.mrchecker.webapi.pages.httbin.SimpleGETPage;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

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
