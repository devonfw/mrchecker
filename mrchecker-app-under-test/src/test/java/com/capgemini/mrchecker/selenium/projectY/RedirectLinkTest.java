package com.capgemini.mrchecker.selenium.projectY;

import static org.junit.Assert.assertTrue;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import com.capgemini.mrchecker.core.groupTestCases.testSuites.tags.TestsChrome;
import com.capgemini.mrchecker.core.groupTestCases.testSuites.tags.TestsFirefox;
import com.capgemini.mrchecker.core.groupTestCases.testSuites.tags.TestsIE;
import com.capgemini.mrchecker.core.groupTestCases.testSuites.tags.TestsSelenium;
import com.capgemini.mrchecker.selenium.pages.projectY.RedirectLinkPage;
import com.capgemini.mrchecker.selenium.pages.projectY.StatusCodesHomePage;

@Category({ TestsSelenium.class, TestsChrome.class, TestsFirefox.class, TestsIE.class })
public class RedirectLinkTest extends TheInternetBaseTest {
	
	private static RedirectLinkPage		redirectLinkPage;
	private static StatusCodesHomePage	statusCodesHomePage;
	
	@BeforeClass
	public static void setUpBeforeClass() {
		redirectLinkPage = shouldTheInternetPageBeOpened().clickRedirectLink();
		
		logStep("Verify if Redirect Link page is opened");
		assertTrue("Unable to open Redirect Link page", redirectLinkPage.isLoaded());
	}
	
	@Test
	public void shouldUserBeRedirectedToStatusCodePage() {
		logStep("Click 'Redirect here' link");
		statusCodesHomePage = redirectLinkPage.clickRedirectHereLink();
		
		logStep("Verify redirection to Status Code page");
		assertTrue("User hasn't been redirected to expected website", statusCodesHomePage.isLoaded());
	}
	
}
