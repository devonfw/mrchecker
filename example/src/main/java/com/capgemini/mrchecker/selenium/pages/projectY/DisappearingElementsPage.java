package com.capgemini.mrchecker.selenium.pages.projectY;

import com.capgemini.mrchecker.selenium.core.BasePage;
import com.capgemini.mrchecker.selenium.core.base.environment.GetEnvironmentParam;
import com.capgemini.mrchecker.selenium.pages.environment.PageSubURLsProjectYEnum;
import com.capgemini.mrchecker.test.core.logger.BFLogger;
import org.openqa.selenium.By;
import org.openqa.selenium.InvalidArgumentException;
import org.openqa.selenium.WebElement;

public class DisappearingElementsPage extends BasePage {

    private static final By selectorGalleryMenuButton = By.cssSelector("li > a[href*=gallery]");
    private static final By selectorAboutMenuButton = By.cssSelector("li > a[href*=about]");
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
     * Returns WebElement representing NOT-disappearing element of menu.     *
     */
    public WebElement getAboutMenuElement() {
        WebElement webElement = getDriver().findElementQuietly(selectorAboutMenuButton);
        if (webElement != null) {
            return webElement;
        } else {
            throw new InvalidArgumentException("About Menu WebElement not found");
        }
    }

    /**
     * Refreshes web page until WebElement has expected visibility
     *
     * @param webElement        Tested element
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
     * Refreshes web page until WebElement has wrong visibility
     *
     * @param webElement        Tested element
     * @param shouldBeDisplayed Determines if element should be displayed.
     * @param maxRefreshes      Maximal number of refreshes
     * @return true if element had correct visibility after every refresh
     */
    public boolean hasSameVisibilityAfterEveryRefresh(WebElement webElement, boolean shouldBeDisplayed, int maxRefreshes) {
        for (int refreshesDone = 0; refreshesDone < maxRefreshes; refreshesDone++) {
            refreshPage();
            if (webElement.isDisplayed() != shouldBeDisplayed) {
                return false;
            }
        }
        return true;
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

    /**
     * Refreshes page until Gallery menu element is not displayed
     *
     * @param maxRefreshes Maximal number of refreshes
     * @return true if element has expected visibility after last refresh
     */
    public boolean refreshPageUntilGalleryIsNotVisible(int maxRefreshes) {
        return refreshPageUntilWebElementHasExpectedVisibility(getGalleryMenuElement(), false, maxRefreshes);
    }

    /**
     * Refreshes page until Gallery menu element is displayed
     *
     * @param maxRefreshes Maximal number of refreshes
     * @return true if element has expected visibility after last refresh
     */
    public boolean isAboutButtonVisibleAfterEveryRefresh(int maxRefreshes) {
        return hasSameVisibilityAfterEveryRefresh(getAboutMenuElement(), true, maxRefreshes);
    }

}
