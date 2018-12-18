package com.capgemini.mrchecker.webapi.calculator_soap;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

import com.capgemini.mrchecker.test.core.logger.BFLogger;
import com.capgemini.mrchecker.webapi.pages.calculator_soap.AddPage;

import io.restassured.response.Response;

public class AddTest extends com.capgemini.mrchecker.webapi.BasePageWebApiTest {
	
	private AddPage addPage = new AddPage();
	
	@Test
	public void addTwoPositiveNumbers() {
		int firstNumberToAdd = 10;
		int secondNumberToAdd = 11;
		
		runAddingTest(firstNumberToAdd, secondNumberToAdd);
	}
	
	@Test
	public void addTwoNegativeNumbers() {
		int firstNumberToAdd = -10;
		int secondNumberToAdd = -11;
		
		runAddingTest(firstNumberToAdd, secondNumberToAdd);
	}
	
	@Test
	public void addTwoMixedNumbers() {
		int firstNumberToAdd = 10;
		int secondNumberToAdd = -11;
		
		runAddingTest(firstNumberToAdd, secondNumberToAdd);
	}
	
	private void runAddingTest(int firstNumberToAdd, int secondNumberToAdd) {
		BFLogger.logInfo("Step 1 - Setting first number to add: " + firstNumberToAdd);
		addPage.setIntA(firstNumberToAdd);
		
		BFLogger.logInfo("Step 2 - Setting second number to add: " + secondNumberToAdd);
		addPage.setIntB(secondNumberToAdd);
		
		BFLogger.logInfo("Step 3 - Sending SOAP query to add two numbers. Endpoint: " + addPage.getEndpoint());
		Response response = addPage.sendPOST();
		
		BFLogger.logInfo("Step 4 - Validating the response status code");
		assertThat(response.statusCode(), is(200));
		
		BFLogger.logInfo("Step 5 - Validating the response result of adding");
		assertThat(response.xmlPath()
				.getInt("Envelope.Body.AddResponse"), is(firstNumberToAdd + secondNumberToAdd));
	}
}
