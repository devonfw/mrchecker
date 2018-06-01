package com.capgemini.mrchecker.selenium.projectY;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.capgemini.mrchecker.selenium.core.BasePage;
import com.capgemini.mrchecker.selenium.pages.projectY.RedirectLinkPage;
import com.capgemini.mrchecker.selenium.pages.projectY.StatusCodesHomePage;
import com.capgemini.mrchecker.selenium.pages.projectY.TheInternetPage;
import com.capgemini.mrchecker.test.core.BaseTest;
import com.capgemini.mrchecker.test.core.logger.BFLogger;

public class RedirectLinkTest extends BaseTest {
	
	private TheInternetPage				theInternetPage;
	private static RedirectLinkPage		redirectLinkPage;
	private static StatusCodesHomePage	statusCodesHomePage;
	
	@Override
	public void setUp() {
		BFLogger.logInfo("Step1 - open Chrome browser");
		BFLogger.logInfo("Step2 - open web page http://the-internet.herokuapp.com/");
		theInternetPage = new TheInternetPage();
		assertTrue("The-internet page is not loaded", theInternetPage.isLoaded());
	}
	
	@Test
	public void redirectToStatusCodePage() {
		BFLogger.logDebug("Step 3 - Click on the Redirect link");
		redirectLinkPage = theInternetPage.clickRedirectLinkPage();
		
		BFLogger.logDebug("Step 4 - Verify if the RedirectLink page opens");
		assertTrue("The Redirect Page was not open", redirectLinkPage.isLoaded());
		
		BFLogger.logDebug("Step 5 - Click 'Redirect here' link");
		statusCodesHomePage = redirectLinkPage.clickRedirectHereLink();
		
		BFLogger.logDebug("Step 6 - Check redirection to Status Code page");
		assertTrue("Text under first avatar doesn't appear", statusCodesHomePage.isLoaded());
	}
	
	@Override
	public void tearDown() {
		BFLogger.logInfo("Step 7 - navigate back to The-Internet page");
		BasePage.navigateBack();
	}
	
}
