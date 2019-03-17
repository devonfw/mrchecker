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
import com.capgemini.mrchecker.selenium.pages.projectY.KeyPressesPage;

@Category({ TestsSelenium.class, TestsChrome.class, TestsFirefox.class, TestsIE.class })
public class KeyPressesTest extends TheInternetBaseTest {
	
	private static KeyPressesPage keyPressesPage;
	
	private final String	keyToBePressed	= "Q";
	private final String	expectedMessage	= "You entered: Q";
	
	@BeforeClass
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
