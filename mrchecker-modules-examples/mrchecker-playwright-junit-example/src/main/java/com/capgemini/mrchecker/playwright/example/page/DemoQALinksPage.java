package com.capgemini.mrchecker.playwright.example.page;

import com.capgemini.mrchecker.playwright.example.base.BasePageGUI;
import com.capgemini.mrchecker.playwright.example.env.GetEnvironmentParam;
import io.qameta.allure.Step;

public class DemoQALinksPage extends BasePageGUI {
    private final String url = GetEnvironmentParam.DEMO_QA_LINKS_URL.getValue();
    private final String selectorHomeLink = "#simpleLink";

    public void startPage() {
        loadPage(url);
    }

    public boolean isPageOpened() {
        //check unique page element
        if (getDriver().currentPage().locator(selectorHomeLink).isVisible()) {
            //check URL
            String currentURL = getDriver().currentPage().url();
            return currentURL.equals(url);
        }
        return false;
    }

    @Step("Click Home link")
    public void clickHomeLink() {
        getDriver().currentPage().locator(selectorHomeLink).click();
    }
}