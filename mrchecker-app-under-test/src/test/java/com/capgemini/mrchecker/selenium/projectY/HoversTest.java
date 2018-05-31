package com.capgemini.mrchecker.selenium.projectY;

import static org.junit.Assert.assertTrue;

import java.util.Random;

import org.junit.Test;

import com.capgemini.mrchecker.selenium.core.BasePage;
import com.capgemini.mrchecker.selenium.pages.projectY.HoversPage;
import com.capgemini.mrchecker.selenium.pages.projectY.TheInternetPage;
import com.capgemini.mrchecker.test.core.BaseTest;
import com.capgemini.mrchecker.test.core.logger.BFLogger;

public class HoversTest extends BaseTest {
	
	private TheInternetPage		theInternetPage;
	private static HoversPage	hoversPage;
	private final String		names[]	= { "name: user1", "name: user2", "name: user3" };
	
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
	public void HoverOverRandomElementTest() {
		BFLogger.logDebug("Step 3 - Click on the Hovers link");
		hoversPage = theInternetPage.clickHoversLink();
		BFLogger.logDebug("Step 4 - Verify if the Hovers page opens");
		assertTrue("The Hovers Page was not open", hoversPage.isLoaded());
		
		int randomIndex = new Random().nextInt(names.length);
		hoversPage.hoverUnderAvatar(randomIndex);
		assertTrue("Text under first avatar doesn't appear", hoversPage.getTextUnderAvatar(randomIndex)
						.equals(names[randomIndex]));
	}
	
}
