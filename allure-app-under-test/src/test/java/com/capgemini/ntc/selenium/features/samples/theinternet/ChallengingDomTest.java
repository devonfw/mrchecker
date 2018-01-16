package com.capgemini.ntc.selenium.features.samples.theinternet;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.capgemini.ntc.selenium.core.BasePage;
import com.capgemini.ntc.selenium.core.newDrivers.DriverManager;
import com.capgemini.ntc.selenium.pages.features.samples.theinternet.ChallengingDomPage;
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
		assertTrue("The-internet page is not loaded", DriverManager.getDriver()
						.getCurrentUrl()
						.contains("the-internet.herokuapp.com"));
	}
	
	@Override
	public void tearDown() {
		BasePage.navigateBack();
	}
	
	@Test
	public void valuesInTableCellsShouldNotChangeAfterClick() {
		challengingDom = theInternetPage.clickChallengingDomLink();
		challengingDom.saveCellsValuesBeforeClick();
		challengingDom.clickFirstButton();
		assertTrue("Values from table cells was changed after click", challengingDom.compareValuesInCells());
	}
}
