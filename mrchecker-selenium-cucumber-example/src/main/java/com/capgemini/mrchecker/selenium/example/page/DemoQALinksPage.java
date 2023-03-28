package com.capgemini.mrchecker.selenium.example.page;

import com.capgemini.mrchecker.selenium.core.utils.WebElementUtils;
import com.capgemini.mrchecker.selenium.example.base.BasePageGUI;
import com.capgemini.mrchecker.selenium.example.env.GetEnvironmentParam;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

public class DemoQALinksPage extends BasePageGUI {
    private final String url = GetEnvironmentParam.DEMO_QA_LINKS_URL.getValue();
    private final By selectorHomeLink = By.id("simpleLink");

    public void startPage() {
        loadPage(url);
    }

    public boolean isPageOpened() {
        //check unique page element
        if (isDisplayedHomeLink()) {
            //check URL
            String currentURL = getDriver().getCurrentUrl();
            return currentURL.equals(url);
        }
        return false;
    }

    @Step("Click Home link")
    public void clickHomeLink() {
        WebElementUtils.clickIfVisible(selectorHomeLink, "Home link");
    }

    public boolean isDisplayedHomeLink() {
        return isElementDisplayedNoException(selectorHomeLink);
    }
}