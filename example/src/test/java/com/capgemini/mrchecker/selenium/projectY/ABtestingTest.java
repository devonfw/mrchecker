package com.capgemini.mrchecker.selenium.projectY;

import static org.junit.Assert.assertTrue;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import com.capgemini.mrchecker.core.groupTestCases.testSuites.tags.TestsChrome;
import com.capgemini.mrchecker.core.groupTestCases.testSuites.tags.TestsFirefox;
import com.capgemini.mrchecker.core.groupTestCases.testSuites.tags.TestsIE;
import com.capgemini.mrchecker.core.groupTestCases.testSuites.tags.TestsSelenium;
import com.capgemini.mrchecker.selenium.pages.projectY.ABtestPage;
import com.capgemini.mrchecker.selenium.pages.projectY.ElementalSeleniumPage;

@Category({ TestsSelenium.class, TestsChrome.class, TestsFirefox.class, TestsIE.class })
public class ABtestingTest extends TheInternetBaseTest {
	
	private static ABtestPage abTestPage;
	
	@BeforeClass
	public static void setUpBeforeClass() {
		abTestPage = shouldTheInternetPageBeOpened().clickABtestingLink();
		
		logStep("Verify if ABTest page is opened");
		assertTrue("Unable to open ABTest page", abTestPage.isLoaded());
	}
	
	@Test
	public void shouldOpenElementalSeleniumPageWhenClickElementalSeleniumLink() {
		
		logStep("Click Elemental Selenium link");
		ElementalSeleniumPage elementalSeleniumPage = abTestPage.clickElementalSeleniumLink();
		
		logStep("Switch browser's tab to newly opened one");
		abTestPage.switchToNextTab();
		
		logStep("Verify if Elemental Selenium Page is opened");
		assertTrue("Unable to open Elemental Selenium page", elementalSeleniumPage.isLoaded());
	}
	
}
