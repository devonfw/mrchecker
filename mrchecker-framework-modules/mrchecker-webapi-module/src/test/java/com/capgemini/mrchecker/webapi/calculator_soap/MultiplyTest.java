package com.capgemini.mrchecker.webapi.calculator_soap;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

import com.capgemini.mrchecker.test.core.logger.BFLogger;
import com.capgemini.mrchecker.webapi.BasePageWebApiTest;
import com.capgemini.mrchecker.webapi.pages.calculator_soap.MultiplyPage;

import io.restassured.response.Response;

public class MultiplyTest extends BasePageWebApiTest {
	
	private MultiplyPage multiplyPage = new MultiplyPage();
	
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
