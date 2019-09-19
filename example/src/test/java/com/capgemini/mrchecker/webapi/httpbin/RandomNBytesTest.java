package com.capgemini.mrchecker.webapi.httpbin;

import com.capgemini.mrchecker.test.core.logger.BFLogger;
import com.capgemini.mrchecker.webapi.BasePageWebApiTest;
import com.capgemini.mrchecker.webapi.pages.httbin.RandomNBytesPage;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.text.MessageFormat;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

@RunWith(JUnitParamsRunner.class)
public class RandomNBytesTest extends BasePageWebApiTest {
	private RandomNBytesPage randomNBytesPage = new RandomNBytesPage();

	@Test
	@Parameters({ "1", "6", "30", "90" })
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
