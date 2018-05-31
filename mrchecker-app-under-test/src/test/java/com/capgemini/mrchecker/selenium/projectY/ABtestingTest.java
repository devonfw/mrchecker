package com.capgemini.mrchecker.selenium.projectY;

import static org.junit.Assert.assertTrue;

import org.junit.BeforeClass;
import org.junit.Test;

import com.capgemini.mrchecker.selenium.core.BasePage;
import com.capgemini.mrchecker.selenium.pages.projectY.ABtestPage;
import com.capgemini.mrchecker.selenium.pages.projectY.ElementalSeleniumPage;
import com.capgemini.mrchecker.selenium.pages.projectY.TheInternetPage;
import com.capgemini.mrchecker.test.core.BaseTest;
import com.capgemini.mrchecker.test.core.logger.BFLogger;

public class ABtestingTest extends BaseTest {
	
	private static TheInternetPage theInternetPage;
	
	@BeforeClass
	public static void setUpBeforeClass() {
		BFLogger.logInfo("Step1 - open Chrome browser");
		BFLogger.logInfo("Step2 - load http://the-internet.herokuapp.com/ page");
		theInternetPage = new TheInternetPage();
		assertTrue("The-internet page is not loaded", theInternetPage.isLoaded());
	}
	
	@Override
	public void setUp() {
	}
	
	@Override
	public void tearDown() {
		BasePage.navigateBack();
	}
	
	@Test
	public void shouldOpenABtestWhenClickABtestingLink() {
		BFLogger.logInfo("Step3 - click ABtesting link");
		ABtestPage abTestPage = theInternetPage.clickABtestingLink();
		assertTrue("AB testing page is not loaded", abTestPage.isLoaded());
	}
	
	@Test
	public void shouldOpenElementalSeleniumPageWhenClickElementalSeleniumLink() {
		
		BFLogger.logInfo("Step3 - click ABtesting link");
		ABtestPage abTestPage = theInternetPage.clickABtestingLink();
		assertTrue("AB testing page is not loaded", abTestPage.isLoaded());
		
		BFLogger.logInfo("Step4 - click Elemental Selenium link");
		ElementalSeleniumPage elementalSeleniumPage = abTestPage.clickElementalSeleniumLink();
		
		BFLogger.logInfo("Step5 - switch tab in browser to new opened Elemental Selenium link");
		abTestPage.switchToNextTab();
		assertTrue("Selenium Elemental page is not loaded", elementalSeleniumPage.pageTitle()
				.contains("Elemental Selenium"));
	}
}
