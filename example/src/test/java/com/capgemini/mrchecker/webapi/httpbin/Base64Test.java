package com.capgemini.mrchecker.webapi.httpbin;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import java.text.MessageFormat;
import java.util.stream.Stream;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import com.capgemini.mrchecker.core.groupTestCases.testSuites.tags.TestsWebApi;
import com.capgemini.mrchecker.test.core.logger.BFLogger;
import com.capgemini.mrchecker.test.core.utils.PageFactory;
import com.capgemini.mrchecker.webapi.BaseWebApiTest;
import com.capgemini.mrchecker.webapi.pages.httbin.Base64Page;

@TestsWebApi
public class Base64Test extends BaseWebApiTest {
	private static Base64Page base64Page;
	
	@BeforeAll
	public static void setUpClass() {
		base64Page = PageFactory.getPageInstance(Base64Page.class);
	}
	
	private static Stream<Arguments> getArguments() {
		return Stream.of(Arguments.of("SFRUUEJJTiBpcyBhd2Vzb21l", "HTTPBIN is awesome"),
				Arguments.of("TXIuQ2hlY2tlcg==", "Mr.Checker"),
				Arguments.of("WmHFvMOzxYLEhyBnxJnFm2zEhSBqYcW6xYQ=", "Zażółć gęślą jaźń"));
	}
	
	@ParameterizedTest
	@MethodSource("getArguments")
	public void sendGetWithBase64DataAndValidateDecodedResponse(String base64Data, String expectedResult) {
		BFLogger.logInfo(MessageFormat.format("Step 1 - Sending GET query to {0} with valid base 64 data: {1}", base64Page.getEndpoint(), base64Data));
		String response = base64Page.getDecodedValue(base64Data);
		
		BFLogger.logInfo(MessageFormat.format("Step 2 - Validate response body (should be {0}): ", expectedResult));
		assertThat(response, is(expectedResult));
	}
}