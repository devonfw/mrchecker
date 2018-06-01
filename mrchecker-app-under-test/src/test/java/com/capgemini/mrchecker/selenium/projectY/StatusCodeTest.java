package com.capgemini.mrchecker.selenium.projectY;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import com.capgemini.mrchecker.core.groupTestCases.testSuites.tags.TestsSelenium;
import com.capgemini.mrchecker.selenium.pages.projectY.StatusCodesCodePage;
import com.capgemini.mrchecker.selenium.pages.projectY.StatusCodesHomePage;
import com.capgemini.mrchecker.selenium.pages.projectY.TheInternetPage;
import com.capgemini.mrchecker.test.core.BaseTest;
import com.capgemini.mrchecker.test.core.logger.BFLogger;

@Category({ TestsSelenium.class })
public class StatusCodeTest extends BaseTest {
	
	private static TheInternetPage		theInternetPage;
	private static StatusCodesHomePage	statusCodesHomePage;
	private StatusCodesCodePage			statusCodesCodePage;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		BFLogger.logInfo("BeforeAll Step 1: Open the Url http://the-internet.herokuapp.com/");
		theInternetPage = new TheInternetPage();
		
		BFLogger.logInfo("BeforeAll Step 2: Verify if Url http://the-internet.herokuapp.com/ is open");
		assertTrue("The Internet Page was not open", theInternetPage.isLoaded());
		
		BFLogger.logInfo("BeforeAll Step 3: Open the Status Codes link");
		statusCodesHomePage = theInternetPage.clickStatusCodesLink();
		
		BFLogger.logInfo("BeforeAll Step 4: Verify if http://the-internet.herokuapp.com/status_codes is open");
		assertTrue("The Status Codes page was not open", statusCodesHomePage.isLoaded());
	}
	
	@Override
	public void setUp() {
	}
	
	@Override
	public void tearDown() {
		BFLogger.logInfo("AfterEach Step 1: Click Link to come back to codes page");
		statusCodesHomePage = statusCodesCodePage.clickLinkToCodePage();
	}
	
	@Test
	public void shouldFirstCodeFlowWorksCorrectly() {
		
		BFLogger.logInfo("Step 1: Click link to 200 Code");
		statusCodesCodePage = statusCodesHomePage.clickCode200Link();
		
		BFLogger.logInfo("Step 2: Verify if http://the-internet.herokuapp.com/status_codes/*** is open");
		assertTrue("The Status Code '200' page was not opened", statusCodesCodePage.isLoadedWithStatusCode("200"));
		
		BFLogger.logInfo("Step 3: Verify if correct code is displayed");
		assertEquals(statusCodesCodePage.getCodeNumber(), statusCodesCodePage.getDisplayedCodeNumber());
	}
	
	@Test
	public void shouldSecondCodeFlowWorksCorrectly() {
		
		BFLogger.logInfo("Step 1: Click link to 301 Code");
		statusCodesCodePage = statusCodesHomePage.clickCode301Link();
		
		BFLogger.logInfo("Step 2: Verify if http://the-internet.herokuapp.com/status_codes/*** is open");
		assertTrue("The Status Code '301' page was not opened", statusCodesCodePage.isLoadedWithStatusCode("301"));
		
		BFLogger.logInfo("Step 3: Verify if correct code is displayed");
		assertEquals(statusCodesCodePage.getCodeNumber(), statusCodesCodePage.getDisplayedCodeNumber());
	}
	
	@Test
	public void shouldThirdCodeFlowWorksCorrectly() {
		BFLogger.logInfo("Step 1: Click link to 404 Code");
		statusCodesCodePage = statusCodesHomePage.clickCode404Link();
		
		BFLogger.logInfo("Step 2: Verify if http://the-internet.herokuapp.com/status_codes/*** is open");
		assertTrue("The Status Code '404' page was not opened", statusCodesCodePage.isLoadedWithStatusCode("404"));
		
		BFLogger.logInfo("Step 3: Verify if correct code is displayed");
		assertEquals(statusCodesCodePage.getCodeNumber(), statusCodesCodePage.getDisplayedCodeNumber());
	}
	
	@Test
	public void shouldFourthCodeFlowWorksCorrectly() {
		BFLogger.logInfo("Step 1: Click link to 500 Code");
		statusCodesCodePage = statusCodesHomePage.clickCode500Link();
		
		BFLogger.logInfo("Step 2: Verify if http://the-internet.herokuapp.com/status_codes/*** is open");
		assertTrue("The Status Code '500' page was not opened", statusCodesCodePage.isLoadedWithStatusCode("500"));
		
		BFLogger.logInfo("Step 3: Verify if correct code is displayed");
		assertEquals(statusCodesCodePage.getCodeNumber(), statusCodesCodePage.getDisplayedCodeNumber());
	}
}
