package com.capgemini.ntc.selenium.projectY;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.capgemini.ntc.selenium.core.BasePage;
import com.capgemini.ntc.selenium.pages.projectY.KeyPressesPage;
import com.capgemini.ntc.selenium.pages.projectY.TheInternetPage;
import com.capgemini.ntc.test.core.BaseTest;
import com.capgemini.ntc.test.core.logger.BFLogger;

public class KeyPressesTest extends BaseTest {
	
	private TheInternetPage theInternetPage;
	private static KeyPressesPage keyPressesPage;
	private final String key = "You entered: Q";
	
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
	
	@Test
	public void pressKeyTest() {
		BFLogger.logDebug("Step 3 - Click on the Key Presses link");
		keyPressesPage = theInternetPage.clickKeyPressesLink();
		BFLogger.logDebug("Step 4 - Verify if the Url http://the-internet.herokuapp.com/key_presses opens");
		assertTrue("The Key Presses Page was not open", keyPressesPage.isLoaded());
		assertTrue("The expected key doesn't pressed", keyPressesPage.getSendKeyPress("Q")
				.equals(key));
	}
	
}
