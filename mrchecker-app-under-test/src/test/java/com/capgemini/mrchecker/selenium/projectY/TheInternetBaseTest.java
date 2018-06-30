package com.capgemini.mrchecker.selenium.projectY;

import static org.junit.Assert.assertTrue;

import com.capgemini.mrchecker.selenium.core.BasePage;
import com.capgemini.mrchecker.selenium.pages.projectY.TheInternetPage;
import com.capgemini.mrchecker.test.core.BaseTest;
import com.capgemini.mrchecker.test.core.logger.BFLogger;

public class TheInternetBaseTest extends BaseTest {
	
	protected static int				step	= 0;
	protected static TheInternetPage	theInternetPage;
	
	@Override
	public void setUp() {
		
	}
	
	/**
	 * Performs operations required for verifying if The Internet Page is properly opened.
	 * 
	 * @return TheInternetPage
	 */
	public static TheInternetPage shouldTheInternetPageBeOpened() {
		
		logStep("Open the Url http://the-internet.herokuapp.com/");
		theInternetPage = new TheInternetPage();
		theInternetPage.load();
		
		logStep("Verify if Url http://the-internet.herokuapp.com/ is opened");
		assertTrue("Unable to load The Internet Page", theInternetPage.isLoaded());
		
		return theInternetPage;
		
	}
	
	/**
	 * Logs test step including step number calculated individually for each test.
	 * 
	 * @param message
	 *            Text message representing step description.
	 */
	public static void logStep(String message) {
		BFLogger.logInfo("Step " + ++step + ": " + message);
	}
	
	@Override
	public void tearDown() {
		logStep("Navigate back to The-Internet page");
		BasePage.navigateBack();
	}
	
}
