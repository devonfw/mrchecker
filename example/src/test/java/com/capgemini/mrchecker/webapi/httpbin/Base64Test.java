package com.capgemini.mrchecker.webapi.httpbin;

import com.capgemini.mrchecker.test.core.logger.BFLogger;
import com.capgemini.mrchecker.webapi.BasePageWebApiTest;
import com.capgemini.mrchecker.webapi.pages.httbin.Base64Page;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.text.MessageFormat;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

@RunWith(JUnitParamsRunner.class)
public class Base64Test extends BasePageWebApiTest {
	private Base64Page base64Page = new Base64Page();

	@Test
	@Parameters({
			"SFRUUEJJTiBpcyBhd2Vzb21l, HTTPBIN is awesome",
			"TXIuQ2hlY2tlcg==, Mr.Checker",
			"WmHFvMOzxYLEhyBnxJnFm2zEhSBqYcW6xYQ= , Zażółć gęślą jaźń"
	})
	public void sendGetWithBase64DataAndValidateDecodedResponse(String base64Data, String expectedResult) {
		BFLogger.logInfo(MessageFormat.format("Step 1 - Sending GET query to {0} with valid base 64 data: {1}", base64Page.getEndpoint(), base64Data));
		String response = base64Page.getDecodedValue(base64Data);

		BFLogger.logInfo(MessageFormat.format("Step 2 - Validate response body (should be {0}): ", expectedResult));
		assertThat(response, is(expectedResult));
	}
}