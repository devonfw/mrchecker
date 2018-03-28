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
	private final String				randomString	= "random";
	
	@Override
	public void setUp() {
		BFLogger.logInfo("Step1 - open Chrome browser");
		BFLogger.logInfo("Step2 - open web page http://the-internet.herokuapp.com/");
		theInternetPage = new TheInternetPage();
		assertTrue("The-internet page is not loaded", theInternetPage.isLoaded());
	}
	
	@Override
	public void tearDown() {
		BFLogger.logInfo("Step7 - navigate back to The-Internet page");
		BasePage.navigateBack();
	}
	
	@Before
	public void clickJavaScriptAlertPage() {
		BFLogger.logInfo("Step 3: Click JavaScritpt Alert link");
		javaScriptAlertsPage = theInternetPage.clickJavaScriptAlertLink();
		assertTrue("JavaScript Alert page is not loaded", javaScriptAlertsPage.isLoaded());
	}
	
	@Test // TC1
	public void checkOkButtonOnBasicAlert() {
		BFLogger.logInfo("Step 4: Click Alert Button");
		javaScriptAlertsPage.clickAlertButton();
		
		BFLogger.logInfo("Step 5: Click OK Button on alert");
		javaScriptAlertsPage.clickAlertAccept();
		
		BFLogger.logInfo("Step 6: Check the message");
		assertEquals("Ok button inside Alert Box doesn't click",
						"You successfuly clicked an alert",
						javaScriptAlertsPage.readResultLabel());
	}
	
	@Test // TC2
	public void checkOkButtonOnConfirmAlert() {
		BFLogger.logInfo("Step 4: Click Confirm Button");
		javaScriptAlertsPage.clickConfirmButton();
		
		BFLogger.logInfo("Step 5: Click OK Button on alert");
		javaScriptAlertsPage.clickAlertAccept();
		
		BFLogger.logInfo("Step 6: Check the message");
		assertEquals("Ok button inside Confirm Box doesn't click",
						"You clicked: Ok",
						javaScriptAlertsPage.readResultLabel());
	}
	
	@Test // TC3
	public void checkCancelButtonOnConfirmAlert() {
		BFLogger.logInfo("Step 4: Click Confirm Button");
		javaScriptAlertsPage.clickConfirmButton();
		
		BFLogger.logInfo("Step 5: Click Cancel Button on alert");
		javaScriptAlertsPage.clickAlertDismiss();
		
		BFLogger.logInfo("Step 6: Check the message");
		assertEquals("Cancel Button inside Confirm Box doesn't work",
						"You clicked: Cancel",
						javaScriptAlertsPage.readResultLabel());
	}
	
	@Test // TC4
	public void checkInputTextOnPromptAlert() {
		
		BFLogger.logInfo("Step 4: Click Prompt Button");
		javaScriptAlertsPage.clickPromptButton();
		
		BFLogger.logInfo("Step 5: Input text on alert: " + randomString);
		javaScriptAlertsPage.writeTextInAlert(randomString);
		
		BFLogger.logInfo("Step 6: Click OK Button on alert");
		javaScriptAlertsPage.clickAlertAccept();
		
		BFLogger.logInfo("Step 7: Check the message");
		assertEquals("The entered text doesn't match",
						"You entered: " + randomString,
						javaScriptAlertsPage.readResultLabel());
	}
	
	@Test // TC5
	public void checkCancelButtonOnPromptAlert() {
		BFLogger.logInfo("Step 4: Click Prompt Button");
		javaScriptAlertsPage.clickPromptButton();
		
		BFLogger.logInfo("Step 5: Click Cancel Button on alert");
		javaScriptAlertsPage.clickAlertDismiss();
		
		BFLogger.logInfo("Step 6: Check the message");
		assertEquals("Cancel Button inside Prompt Box doesn't work",
						"You entered: null",
						javaScriptAlertsPage.readResultLabel());
	}
	
}
