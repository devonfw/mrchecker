package com.capgemini.mrchecker.webapi.calculator_soap;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.capgemini.mrchecker.core.groupTestCases.testSuites.tags.TestsWebApi;
import com.capgemini.mrchecker.test.core.BaseTest;
import com.capgemini.mrchecker.test.core.logger.BFLogger;
import com.capgemini.mrchecker.test.core.utils.PageFactory;
import com.capgemini.mrchecker.webapi.pages.calculator_soap.DividePage;

import io.restassured.response.Response;

@TestsWebApi
public class DivideTest extends BaseTest {
	
	private DividePage dividePage;
	
	@BeforeEach
	public void setUpClass() {
		dividePage = PageFactory.getPageInstance(DividePage.class);
	}
	
	@Test
	public void divideTwoPositiveNumbers() {
		int firstNumberToDivide = 100;
		int secondNumberToDivide = 10;
		
		runDividingTest(firstNumberToDivide, secondNumberToDivide);
	}
	
	@Test
	public void divideTwoNegativeNumbers() {
		int firstNumberToDivide = -100;
		int secondNumberToDivide = -10;
		
		runDividingTest(firstNumberToDivide, secondNumberToDivide);
	}
	
	@Test
	public void divideTwoMixedNumbers() {
		int firstNumberToDivide = 100;
		int secondNumberToDivide = -10;
		
		runDividingTest(firstNumberToDivide, secondNumberToDivide);
	}
	
	private void runDividingTest(int firstNumberToDivide, int secondNumberToDivide) {
		BFLogger.logInfo("Step 1 - Setting first number to divide: " + firstNumberToDivide);
		dividePage.setIntA(firstNumberToDivide);
		
		BFLogger.logInfo("Step 2 - Setting second number to divide: " + secondNumberToDivide);
		dividePage.setIntB(secondNumberToDivide);
		
		BFLogger.logInfo("Step 3 - Sending SOAP query to divide two numbers. Endpoint: " + dividePage.getEndpoint());
		Response response = dividePage.sendPOST();
		
		BFLogger.logInfo("Step 4 - Validating the response status code");
		assertThat(response.statusCode(), is(200));
		
		BFLogger.logInfo("Step 5 - Validating the response result of dividing");
		assertThat(response.xmlPath()
				.getInt("Envelope.Body.DivideResponse"), is(firstNumberToDivide / secondNumberToDivide));
	}
}
