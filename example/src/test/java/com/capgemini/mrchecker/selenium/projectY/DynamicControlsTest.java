package com.capgemini.mrchecker.selenium.projectY;

import com.capgemini.mrchecker.selenium.pages.projectY.DynamicControlsPage;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class DynamicControlsTest extends TheInternetBaseTest {

    private DynamicControlsPage dynamicControlsPage;
    boolean isCheckboxOnPage;

    @Override
    public void setUp() {
        dynamicControlsPage = shouldTheInternetPageBeOpened().clickDynamicControlsLink();

        logStep("Verify if Dynamic Controls page is opened");
        assertTrue("Unable to open Dynamic Controls page", dynamicControlsPage.isLoaded());

        logStep("Verify if checkbox is visible before dynamic hide");
        isCheckboxOnPage = dynamicControlsPage.isCheckboxOnPage();
        assertTrue("Checkbox is not present ", isCheckboxOnPage);
    }


    @Test
    public void shouldRemoveCheckboxAfterButtonClickAndWaitProcess() throws InterruptedException {
        logStep("Click remove button");
        dynamicControlsPage.clickRemoveButton();

        logStep("Wait few seconds to finish loading");
        dynamicControlsPage.waitUntilLoadingIsDone();
        isCheckboxOnPage = dynamicControlsPage.isCheckboxOnPage();

        assertFalse("Checkbox has not disappeared", isCheckboxOnPage);
    }


}
