package com.capgemini.mrchecker.selenium.projectY;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import com.capgemini.mrchecker.core.groupTestCases.testSuites.tags.TestsChrome;
import com.capgemini.mrchecker.core.groupTestCases.testSuites.tags.TestsFirefox;
import com.capgemini.mrchecker.core.groupTestCases.testSuites.tags.TestsIE;
import com.capgemini.mrchecker.core.groupTestCases.testSuites.tags.TestsSelenium;
import com.capgemini.mrchecker.selenium.pages.projectY.StatusCodesCodePage;
import com.capgemini.mrchecker.selenium.pages.projectY.StatusCodesHomePage;

@Category({ TestsSelenium.class, TestsChrome.class, TestsFirefox.class, TestsIE.class })
public class StatusCodeTest extends TheInternetBaseTest {
	
	private static StatusCodesHomePage	statusCodesHomePage;
	private StatusCodesCodePage			statusCodesCodePage;
	
	private String[] codes = { "200", "301", "404", "500" };
	
	@BeforeClass
	public static void setUpBeforeClass() {
		statusCodesHomePage = shouldTheInternetPageBeOpened().clickStatusCodesLink();
		
		logStep("Verify if Status Codes Home page is opened");
		assertTrue("Unable to open Status Codes Home page", statusCodesHomePage.isLoaded());
	}
	
	@Test
	public void shouldProperCodeBeDisplayedAfterClickCodeLink() {
		
		for (String code : codes) {
			logStep("Click link to " + code + " code");
			statusCodesCodePage = statusCodesHomePage.clickCodeLink(code);
			
			logStep("Verify if proper web page corresponding to code is opened");
			assertTrue("Unable to open proper web page", statusCodesCodePage.isLoadedWithStatusCode(code));
			
			logStep("Verify if displayed code is equal to expected one");
			assertEquals(code, statusCodesCodePage.getDisplayedCodeNumber());
			
			logStep("Click link to come back to 'Status Codes' page");
			statusCodesCodePage.clickLinkToCodePage();
		}
	}
	
}
