package com.capgemini.mrchecker.selenium.projectY;

import com.capgemini.mrchecker.selenium.pages.projectY.DynamicControlsPage;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class DynamicControlsTest extends TheInternetBaseTest{

    private static DynamicControlsPage dynamicControlsPage;
    boolean isCheckboxOnPage;

    @BeforeClass
    public static void setUpBeforeClass() {
        dynamicControlsPage = shouldTheInternetPageBeOpened().clickDynamicControlsLink();

        logStep("Verify if Dynamic Controls page is opened");
        assertTrue("Unable to open Dynamic Controls page", dynamicControlsPage.isLoaded());
    }

    @Test
    public void shouldHaveCheckboxOnLoad(){
        isCheckboxOnPage = dynamicControlsPage.isCheckboxOnPage();

        assertTrue("Checkbox is not present ", isCheckboxOnPage);
    }

    @Test
    public void shouldRemoveCheckboxAfterButtonClickAndWaitProcess(){
        dynamicControlsPage.clickRemoveButton();
        dynamicControlsPage.waitUntilLoadingIsDone();

        isCheckboxOnPage = dynamicControlsPage.isCheckboxOnPage();

        assertFalse("Checkbox has not disappeared", isCheckboxOnPage);
    }


}
