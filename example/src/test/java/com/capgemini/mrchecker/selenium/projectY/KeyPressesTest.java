package com.capgemini.mrchecker.selenium.projectY;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.capgemini.mrchecker.core.groupTestCases.testSuites.tags.TestsChrome;
import com.capgemini.mrchecker.core.groupTestCases.testSuites.tags.TestsFirefox;
import com.capgemini.mrchecker.core.groupTestCases.testSuites.tags.TestsIE;
import com.capgemini.mrchecker.core.groupTestCases.testSuites.tags.TestsSelenium;
import com.capgemini.mrchecker.selenium.core.BasePage;
import com.capgemini.mrchecker.selenium.pages.projectY.KeyPressesPage;

@TestsSelenium
@TestsChrome
@TestsFirefox
@TestsIE
public class KeyPressesTest extends TheInternetBaseTest {
	
	private static KeyPressesPage keyPressesPage;
	
	@BeforeEach
	public void setUpBeforeClass() {
		keyPressesPage = shouldTheInternetPageBeOpened().clickKeyPressesLink();
		
		logStep("Verify if Key Presses page is opened");
		assertTrue("Unable to open Key Presses page", keyPressesPage.isLoaded());
	}
	
	@AfterEach
	public void tearDownAfterClass() {
		logStep("Navigate back to The-Internet page");
		BasePage.navigateBack();
	}
	
	@Test
	public void shouldWebsiteReturnInformationAboutPressedKey() {
		char qKey = 'Q';
		logStep("Press keyboard Q key");
		keyPressesPage.pressKey(qKey);
		
		String expectedPageResponse = "You entered: Q";
		logStep("Verify if website give valid information about pressed keyboard key");
		assertEquals("Information about pressed key is invalid", expectedPageResponse, keyPressesPage.getPressedKeyInformation());
	}
	
	@Test
	public void shouldDetectPressEnter() {
		char enterKey = '\n';
		logStep("Press keyboard ENTER key");
		keyPressesPage.pressKey(enterKey);
		
		String expectedPageResponse = "You entered: ENTER";
		logStep("Verify if website give valid information about pressed keyboard key");
		assertEquals("Information about pressed key is invalid", expectedPageResponse, keyPressesPage.getPressedKeyInformation());
	}
	
}
