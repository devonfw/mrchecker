package com.capgemini.mrchecker.selenium.projectY;

import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.capgemini.mrchecker.core.groupTestCases.testSuites.tags.TestsChrome;
import com.capgemini.mrchecker.core.groupTestCases.testSuites.tags.TestsFirefox;
import com.capgemini.mrchecker.core.groupTestCases.testSuites.tags.TestsIE;
import com.capgemini.mrchecker.core.groupTestCases.testSuites.tags.TestsSelenium;
import com.capgemini.mrchecker.selenium.pages.projectY.RedirectLinkPage;
import com.capgemini.mrchecker.selenium.pages.projectY.StatusCodesHomePage;

@TestsSelenium
@TestsChrome
@TestsFirefox
@TestsIE
public class RedirectLinkTest extends TheInternetBaseTest {
	
	private static RedirectLinkPage		redirectLinkPage;
	private static StatusCodesHomePage	statusCodesHomePage;
	
	@BeforeAll
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
