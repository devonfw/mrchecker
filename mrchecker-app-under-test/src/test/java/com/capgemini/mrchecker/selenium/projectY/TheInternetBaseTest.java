package com.capgemini.mrchecker.selenium.projectY;

import static org.junit.Assert.assertTrue;

import com.capgemini.mrchecker.selenium.core.BasePage;
import com.capgemini.mrchecker.selenium.pages.projectY.TheInternetPage;
import com.capgemini.mrchecker.selenium.pages.projectY.TheInternetSubpage;
import com.capgemini.mrchecker.test.core.BaseTest;
import com.capgemini.mrchecker.test.core.logger.BFLogger;

public class TheInternetBaseTest<V extends TheInternetSubpage> extends BaseTest {
	
	protected static int step = 0;
	// protected TheInternetPage<V> theInternetPage;
	protected static TheInternetPage<TheInternetSubpage> theInternetPage;
	
	@Override
	public void setUp() {
		
	}
	
	/**
	 * The set of operations required for most of test classes with one test method only.
	 * 
	 * @param subpage
	 *            Any sub page accessed by link available at The Internet Page.
	 */
	public static void shouldTheInternetSubpageBeOpened(TheInternetSubpage subpage) {
		
		logStep("Open the Url http://the-internet.herokuapp.com/");
		theInternetPage = new TheInternetPage<>(subpage);
		theInternetPage.load();
		
		logStep("Verify if Url http://the-internet.herokuapp.com/ is opened");
		assertTrue("The Internet Page was not open", theInternetPage.isLoaded());
		
		logStep("Click subpage link");
		theInternetPage.clickPageLink();
		
		logStep("Verify if subpage is opened");
		assertTrue("The Internet subpage: " + subpage.getClass()
						.getSimpleName() + " was not open", subpage.isLoaded());
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
