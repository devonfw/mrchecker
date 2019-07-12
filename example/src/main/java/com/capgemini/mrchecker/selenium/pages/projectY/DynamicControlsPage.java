package com.capgemini.mrchecker.selenium.pages.projectY;

import com.capgemini.mrchecker.selenium.core.BasePage;
import com.capgemini.mrchecker.selenium.pages.environment.GetEnvironmentParam;
import com.capgemini.mrchecker.selenium.pages.environment.PageSubURLsProjectYEnum;
import com.capgemini.mrchecker.test.core.logger.BFLogger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.sql.Time;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

public class DynamicControlsPage extends BasePage {

    private static final String removeButtonText = "Remove"; // By.id("checkbox-example").findElement("Remove")
    private static final By checkboxSelector = By.id("checkbox");
    private static final int secondsToWaitForChanges = 4;

    @Override
    public boolean isLoaded() {
        getDriver().waitForPageLoaded();
        return getDriver().getCurrentUrl()
                .contains(PageSubURLsProjectYEnum.DYNAMIC_CONTROLS.getValue());
    }

    @Override
    public void load() {
        BFLogger.logDebug("Load 'Dynamic Controls' page.");
        getDriver().get(GetEnvironmentParam.THE_INTERNET_MAIN_PAGE.getValue() + PageSubURLsProjectYEnum.DYNAMIC_CONTROLS.getValue());
        getDriver().waitForPageLoaded();
    }

    @Override
    public String pageTitle() {
        return getActualPageTitle();
    }

    public void clickRemoveButton() {
        findButtonByText(removeButtonText).get().click();
    }

    public Optional<WebElement> findButtonByText(String textOnButton) {
        By buttonSelector = By.cssSelector("button");
        return getDriver().findElementDynamics(buttonSelector).stream().filter(element -> element.getText().equals(textOnButton)).findFirst();
    }

    public boolean isCheckboxOnPage() {
        WebElement checkbox = getDriver().findElementQuietly(checkboxSelector);
        return checkbox != null;
    }

    public void waitUntilLoadingIsDone() {
        try {
            TimeUnit.SECONDS.sleep(secondsToWaitForChanges);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
