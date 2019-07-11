package com.capgemini.mrchecker.selenium.pages.projectY;

import com.capgemini.mrchecker.selenium.core.BasePage;
import com.capgemini.mrchecker.selenium.core.base.environment.GetEnvironmentParam;
import com.capgemini.mrchecker.selenium.pages.environment.PageSubURLsProjectYEnum;
import com.capgemini.mrchecker.test.core.logger.BFLogger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class DisappearingElementsPage extends BasePage {

    private static final By selectorGalleryMenuButton = By.cssSelector("li > a[href*=gallery]");
    private static final By selectorMenuButtons = By.cssSelector("li");

    @Override
    public boolean isLoaded() {
        getDriver().waitForPageLoaded();
        return getDriver().getCurrentUrl()
                .contains(PageSubURLsProjectYEnum.DISAPPEARING_ELEMENTS.getValue());
    }

    @Override
    public void load() {
        BFLogger.logDebug("Load 'Disappearing Elements' page.");
        getDriver().get(GetEnvironmentParam.THE_INTERNET_MAIN_PAGE.getValue() + PageSubURLsProjectYEnum.DISAPPEARING_ELEMENTS.getValue());
        getDriver().waitForPageLoaded();
    }

    @Override
    public String pageTitle() {
        return getActualPageTitle();
    }

    /**
     * Returns a number of WebElements representing menu buttons.
     */
    public int getNumberOfMenuButtons() {
        return getDriver().findElementDynamics(selectorMenuButtons)
                .size();
    }

    /**
     * Returns WebElement representing disappearing element of menu.
     *
     * @return Disappearing WebElement if visible, null otherwise.
     */
    public WebElement getGalleryMenuElement() {
        return getDriver().findElementQuietly(selectorGalleryMenuButton);
    }

    /**
     * Refreshes web page until WebElement has expected visibility
     *
     * @param webElement        tested element
     * @param shouldBeDisplayed Determines if element should be displayed.
     * @param maxRefreshes      Maximal number of refreshes
     * @return true if element has expected visibility after last refresh
     */
    public boolean refreshPageUntilWebElementHasExpectedVisibility(WebElement webElement, boolean shouldBeDisplayed, int maxRefreshes) {
        for (int refreshesDone = 0; refreshesDone < maxRefreshes; refreshesDone++) {
            refreshPage();
            if (webElement.isDisplayed() == shouldBeDisplayed) {
                return true;
            }
        }
        return false;
    }

    /**
     * Refreshes page until Gallery menu element is displayed
     *
     * @param maxRefreshes Maximal number of refreshes
     * @return true if element has expected visibility after last refresh
     */
    public boolean refreshPageUntilGalleryIsVisible(int maxRefreshes) {
        return refreshPageUntilWebElementHasExpectedVisibility(getGalleryMenuElement(), true, maxRefreshes);
    }

}
