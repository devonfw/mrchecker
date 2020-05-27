package com.capgemini.mrchecker.webapi.httpbin;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import java.text.MessageFormat;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import com.capgemini.mrchecker.core.groupTestCases.testSuites.tags.TestsWebApi;
import com.capgemini.mrchecker.test.core.logger.BFLogger;
import com.capgemini.mrchecker.webapi.BaseWebApiTest;
import com.capgemini.mrchecker.webapi.pages.httbin.RandomNBytesPage;

@TestsWebApi
public class RandomNBytesTest extends BaseWebApiTest {
	private static RandomNBytesPage randomNBytesPage = new RandomNBytesPage();
	
	@BeforeAll
	public static void setUpClass() {
		randomNBytesPage.initialize();
	}
	
	@ParameterizedTest
	@ValueSource(ints = { 1, 6, 30, 90 })
	public void randomNBytesGenerationTest(Integer length) {
		validateNBytesOfData(length);
	}
	
	private void validateNBytesOfData(Integer size) {
		BFLogger.logInfo(MessageFormat.format("Step 1 - Sending GET query to {0} with valid value(size): {1}", randomNBytesPage.getEndpoint(), size));
		randomNBytesPage.sendGETQuery(Integer.toString(size));
		
		BFLogger.logInfo("Step 2 - Validate response status code (should be 200): ");
		assertThat(randomNBytesPage.getStatusCodeFromResponse(), is(200));
		
		BFLogger.logInfo(MessageFormat.format("Step 3 - Validate response body (should be {0}): ", size));
		assertThat(randomNBytesPage.getBytesFromResponse().length, is(size));
	}
}
