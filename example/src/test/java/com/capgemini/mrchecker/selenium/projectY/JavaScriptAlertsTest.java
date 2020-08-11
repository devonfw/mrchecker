package com.capgemini.mrchecker.selenium.projectY;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.capgemini.mrchecker.core.groupTestCases.testSuites.tags.TestsChrome;
import com.capgemini.mrchecker.core.groupTestCases.testSuites.tags.TestsFirefox;
import com.capgemini.mrchecker.core.groupTestCases.testSuites.tags.TestsIE;
import com.capgemini.mrchecker.core.groupTestCases.testSuites.tags.TestsSelenium;
import com.capgemini.mrchecker.selenium.core.BasePage;
import com.capgemini.mrchecker.selenium.pages.projectY.JavaScriptAlertsPage;

@TestsSelenium
@TestsChrome
@TestsFirefox
@TestsIE
public class JavaScriptAlertsTest extends TheInternetBaseTest {
	
	private static JavaScriptAlertsPage javaScriptAlertsPage;
	
	private final String	jsAlertConfirmMessage	= "You successfuly clicked an alert";
	private final String	jsConfirmConfirmMessage	= "You clicked: Ok";
	private final String	jsConfirmCancelMessage	= "You clicked: Cancel";
	private final String	jsPromptConfirmMessage	= "You entered: ";
	private final String	jsPromptCancelMessage	= "You entered: null";
	private final String	randomString			= "rAndOM 1 2 % @ * 8 END";
	
	@BeforeAll
	public static void setUpBeforeClass() {
		javaScriptAlertsPage = shouldTheInternetPageBeOpened().clickJavaScriptAlertLink();
		
		logStep("Verify if JavaScript Alerts page is opened");
		assertTrue("Unable to open JavaScript Alerts page", javaScriptAlertsPage.isLoaded());
	}
	
	@AfterAll
	public static void tearDownAfterClass() {
		logStep("Navigate back to The-Internet page");
		BasePage.navigateBack();
	}
	
	@Test
	public void shouldJSAlertCloseWithProperMessageAfterPressOkButton() {
		logStep("Click Alert button");
		javaScriptAlertsPage.clickAlertButton();
		
		logStep("Click 'OK' button on alert");
		javaScriptAlertsPage.clickAlertAccept();
		
		logStep("Verify returned message");
		assertEquals("Incorrect message returned after click",
				jsAlertConfirmMessage, javaScriptAlertsPage.readResultLabel());
	}
	
	@Test
	public void shouldJSConfirmCloseWithProperMessageAfterPressOkButton() {
		logStep("Click Confirm button");
		javaScriptAlertsPage.clickConfirmButton();
		
		logStep("Click 'OK' button on alert");
		javaScriptAlertsPage.clickAlertAccept();
		
		logStep("Verify returned message");
		assertEquals("Incorrect message returned after click",
				jsConfirmConfirmMessage, javaScriptAlertsPage.readResultLabel());
	}
	
	@Test
	public void shouldJSConfirmCloseWithProperMessageAfterPressCancelButton() {
		logStep("Click Confirm button");
		javaScriptAlertsPage.clickConfirmButton();
		
		logStep("Click 'Cancel' button on alert");
		javaScriptAlertsPage.clickAlertDismiss();
		
		logStep("Verify returned message");
		assertEquals("Incorrect message returned after click",
				jsConfirmCancelMessage, javaScriptAlertsPage.readResultLabel());
	}
	
	@Test
	public void shouldJSPromptCloseWithProperMessageAfterPressOKButton() {
		logStep("Click Prompt button");
		javaScriptAlertsPage.clickPromptButton();
		
		logStep("Insert text to alert: " + randomString);
		javaScriptAlertsPage.writeTextInAlert(randomString);
		
		logStep("Click 'OK' button on alert");
		javaScriptAlertsPage.clickAlertAccept();
		
		logStep("Verify returned message");
		assertEquals("Incorrect message returned after click",
				jsPromptConfirmMessage + randomString, javaScriptAlertsPage.readResultLabel());
	}
	
	@Test
	public void shouldJSPromptCloseWithProperMessageAfterPressCancelButton() {
		logStep("Click Prompt button");
		javaScriptAlertsPage.clickPromptButton();
		
		logStep("Click 'Cancel' button on alert");
		javaScriptAlertsPage.clickAlertDismiss();
		
		logStep("Verify returned message");
		assertEquals("Incorrect message returned after click",
				jsPromptCancelMessage, javaScriptAlertsPage.readResultLabel());
	}
	
	@Override
	public void tearDown() {
		logStep("Refresh JavaScriptAlertsPage");
		javaScriptAlertsPage.refreshPage();
	}
	
}
