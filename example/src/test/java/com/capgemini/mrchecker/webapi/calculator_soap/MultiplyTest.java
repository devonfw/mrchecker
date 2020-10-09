package com.capgemini.mrchecker.webapi.calculator_soap;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.capgemini.mrchecker.core.groupTestCases.testSuites.tags.TestsWebApi;
import com.capgemini.mrchecker.test.core.BaseTest;
import com.capgemini.mrchecker.test.core.logger.BFLogger;
import com.capgemini.mrchecker.test.core.utils.PageFactory;
import com.capgemini.mrchecker.webapi.pages.calculator_soap.MultiplyPage;

import io.restassured.response.Response;

@TestsWebApi
public class MultiplyTest extends BaseTest {
	private MultiplyPage multiplyPage;
	
	@BeforeEach
	public void setUpClass() {
		multiplyPage = PageFactory.getPageInstance(MultiplyPage.class);
	}
	
	@Test
	public void multiplyTwoPositiveNumbers() {
		int firstNumberToMultiply = 15;
		int secondNumberToMultiply = 20;
		
		runMultiplicationTest(firstNumberToMultiply, secondNumberToMultiply);
	}
	
	@Test
	public void multiplyTwoNegativeNumbers() {
		int firstNumberToMultiply = -15;
		int secondNumberToMultiply = -20;
		
		runMultiplicationTest(firstNumberToMultiply, secondNumberToMultiply);
	}
	
	@Test
	public void multiplyTwoMixedNumbers() {
		int firstNumberToMultiply = 15;
		int secondNumberToMultiply = -20;
		
		runMultiplicationTest(firstNumberToMultiply, secondNumberToMultiply);
	}
	
	private void runMultiplicationTest(int firstNumberToMultiply, int secondNumberToMultiply) {
		BFLogger.logInfo("Step 1 - Setting first number to multiply: " + firstNumberToMultiply);
		multiplyPage.setIntA(firstNumberToMultiply);
		
		BFLogger.logInfo("Step 2 - Setting second number to multiply: " + secondNumberToMultiply);
		multiplyPage.setIntB(secondNumberToMultiply);
		
		BFLogger.logInfo("Step 3 - Sending SOAP query to multiplication two numbers. Endpoint: " + multiplyPage.getEndpoint());
		Response response = multiplyPage.sendPOST();
		
		BFLogger.logInfo("Step 4 - Validating the response status code");
		assertThat(response.statusCode(), is(200));
		
		BFLogger.logInfo("Step 5 - Validating the response result of multiplication");
		assertThat(response.xmlPath()
				.getInt("Envelope.Body.MultiplyResponse"), is(firstNumberToMultiply * secondNumberToMultiply));
	}
}
