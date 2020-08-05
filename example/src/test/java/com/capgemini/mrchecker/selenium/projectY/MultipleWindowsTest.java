package com.capgemini.mrchecker.selenium.projectY;

import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.capgemini.mrchecker.core.groupTestCases.testSuites.tags.TestsChrome;
import com.capgemini.mrchecker.core.groupTestCases.testSuites.tags.TestsFirefox;
import com.capgemini.mrchecker.core.groupTestCases.testSuites.tags.TestsIE;
import com.capgemini.mrchecker.core.groupTestCases.testSuites.tags.TestsSelenium;
import com.capgemini.mrchecker.selenium.pages.projectY.MultipleWindowsPage;
import com.capgemini.mrchecker.selenium.pages.projectY.NewWindowPage;

@TestsSelenium
@TestsChrome
@TestsFirefox
@TestsIE
public class MultipleWindowsTest extends TheInternetBaseTest {
	
	private static MultipleWindowsPage	multipleWindowsPage;
	private static NewWindowPage		newWindowPage;
	
	@BeforeAll
	public static void setUpBeforeClass() {
		multipleWindowsPage = shouldTheInternetPageBeOpened().clickmultipleWindowsLink();
		
		logStep("Verify if Multiple Windows page is opened");
		assertTrue("Unable to open Multiple Windows page", multipleWindowsPage.isLoaded());
	}
	
	@Test
	public void verifyIfNewBrowserWindowOpen() {
		logStep("Click 'Click here' link");
		newWindowPage = multipleWindowsPage.clickHereLink();
		
		logStep("Verify if 'New window page' is opened");
		assertTrue("Unable to open new browser's window", newWindowPage.isLoaded());
	}
}
