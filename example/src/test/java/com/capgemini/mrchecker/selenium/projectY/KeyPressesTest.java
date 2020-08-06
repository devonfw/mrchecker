package com.capgemini.mrchecker.selenium.projectY;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.capgemini.mrchecker.core.groupTestCases.testSuites.tags.TestsChrome;
import com.capgemini.mrchecker.core.groupTestCases.testSuites.tags.TestsFirefox;
import com.capgemini.mrchecker.core.groupTestCases.testSuites.tags.TestsIE;
import com.capgemini.mrchecker.core.groupTestCases.testSuites.tags.TestsSelenium;
import com.capgemini.mrchecker.selenium.pages.projectY.KeyPressesPage;

@TestsSelenium
@TestsChrome
@TestsFirefox
@TestsIE
public class KeyPressesTest extends TheInternetBaseTest {
	
	private static KeyPressesPage keyPressesPage;
	
	private final String	keyToBePressed	= "Q";
	private final String	expectedMessage	= "You entered: Q";
	
	@BeforeAll
	public static void setUpBeforeClass() {
		keyPressesPage = shouldTheInternetPageBeOpened().clickKeyPressesLink();
		
		logStep("Verify if Key Presses page is opened");
		assertTrue("Unable to open Key Presses page", keyPressesPage.isLoaded());
	}
	
	@Test
	public void shouldWebsiteReturnInformationAboutPressedKey() {
		logStep("Press keyboard key");
		keyPressesPage.pressKey(keyToBePressed);
		
		logStep("Verify if website give valid information about pressed keyboard key");
		assertEquals("Information about pressed key is invalid", expectedMessage, keyPressesPage.getPressedKeyInformation());
	}
	
}
