package com.capgemini.ntc.selenium.projectY;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;

import com.capgemini.ntc.selenium.core.BasePage;
import com.capgemini.ntc.selenium.pages.projectY.ChallengingDomPage;
import com.capgemini.ntc.selenium.pages.projectY.TheInternetPage;
import com.capgemini.ntc.test.core.BaseTest;
import com.capgemini.ntc.test.core.logger.BFLogger;

public class ChallengingDomTest extends BaseTest {
	
	private TheInternetPage		theInternetPage;
	private ChallengingDomPage	challengingDom;
	
	@Override
	public void setUp() {
		BFLogger.logDebug("Step1 - open Chrome browser");
		BFLogger.logDebug("Step2 - load http://the-internet.herokuapp.com/ page");
		theInternetPage = new TheInternetPage();
		assertTrue("The-internet page is not loaded", theInternetPage.isLoaded());
		BFLogger.logDebug("Step 3 - Click on the Challenging DOM link");
		challengingDom = theInternetPage.clickChallengingDomLink();
		
		BFLogger.logDebug("Step 4 - Verify if Challenging DOM Page opens");
		assertTrue("The Challenging DOM Page was not open", challengingDom.isLoaded());
	}
	
	@Override
	public void tearDown() {
		BFLogger.logDebug("Step 9 - navigate back to The-Internet page");
		BasePage.navigateBack();
	}
	
	@Test
	public void valuesInTableCellsShouldNotChangeAfterClick() {
		BFLogger.logDebug("Step 5 - Getting the table values (before click first button)");
		List<String> tableValuesBeforeClick = challengingDom.getTableValues();
		BFLogger.logDebug("Step 6 - Click first button");
		challengingDom.clickFirstButton();
		BFLogger.logDebug("Step 7 - Getting the table values (after click first button)");
		List<String> tableValuesAfterClick = challengingDom.getTableValues();
		
		BFLogger.logDebug("Step 8 - Comparing the table values before and after click");
		assertEquals("Values from table cells was changed after click", tableValuesBeforeClick, tableValuesAfterClick);
	}
}
