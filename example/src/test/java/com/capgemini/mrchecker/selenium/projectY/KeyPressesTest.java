package com.capgemini.mrchecker.selenium.projectY;

import com.capgemini.mrchecker.core.groupTestCases.testSuites.tags.TestsChrome;
import com.capgemini.mrchecker.core.groupTestCases.testSuites.tags.TestsFirefox;
import com.capgemini.mrchecker.core.groupTestCases.testSuites.tags.TestsIE;
import com.capgemini.mrchecker.core.groupTestCases.testSuites.tags.TestsSelenium;
import com.capgemini.mrchecker.selenium.pages.projectY.KeyPressesPage;
import javafx.scene.input.KeyCode;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@Category({TestsSelenium.class, TestsChrome.class, TestsFirefox.class, TestsIE.class})
public class KeyPressesTest extends TheInternetBaseTest {

    private KeyPressesPage keyPressesPage;

    @Override
    public void setUp() {
        keyPressesPage = shouldTheInternetPageBeOpened().clickKeyPressesLink();

        logStep("Verify if Key Presses page is opened");
        assertTrue("Unable to open Key Presses page", keyPressesPage.isLoaded());
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
