package com.capgemini.ntc.selenium.projectY;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import com.capgemini.ntc.selenium.core.BasePage;
import com.capgemini.ntc.selenium.pages.projectY.JavaScriptAlertsPage;
import com.capgemini.ntc.selenium.pages.projectY.TheInternetPage;
import com.capgemini.ntc.test.core.BaseTest;
import com.capgemini.ntc.test.core.logger.BFLogger;

public class JavaScriptAlertsTest extends BaseTest {
	
	private TheInternetPage				theInternetPage;
	private static JavaScriptAlertsPage	javaScriptAlertsPage;
	
	@Override
	public void setUp() {
		BFLogger.logInfo("Step1 - open Chrome browser");
		BFLogger.logInfo("Step2 - open web page http://the-internet.herokuapp.com/");
		theInternetPage = new TheInternetPage();
		assertTrue("The-internet page is not loaded", theInternetPage.isLoaded());
	}
	
	@Override
	public void tearDown() {
		BFLogger.logInfo("Step5 - navigate back to The-Internet page");
		BasePage.navigateBack();
	}
	
	@Before
	public void clickJavaScriptAlertPage() {
		BFLogger.logInfo("Step 3: Click JavaScritpt Alert link");
		javaScriptAlertsPage = theInternetPage.clickJavaScriptAlertLink();
		assertTrue("JavaScritp Alert page is not loaded", javaScriptAlertsPage.isLoaded());
	}
	
	@Test
	public void shouldOpenJSAlertBoxAndClickOkButtonTest() {
		BFLogger.logInfo("Step 4: Click Alert Button");
		javaScriptAlertsPage.clickAlertButton();
		
		assertEquals("Ok button inside Alert Box doesn't click",
						"You successfuly clicked an alert",
						javaScriptAlertsPage.readResultLabel());
		
	}
	
	@Test
	public void validateJSConfirm() {
		BFLogger.logInfo("Step 4: Click Confirm Button");
		javaScriptAlertsPage.clickConfirmButton();
		
	}
	
	@Test
	public void validateJSPrompt() {
		BFLogger.logInfo("Step 4: Click Prompt Button");
		javaScriptAlertsPage.clickPromptButton();
		
	}
	
}
