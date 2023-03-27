package com.capgemini.mrchecker.selenium.example.page;

import com.capgemini.mrchecker.selenium.example.base.BasePageGUI;
import com.capgemini.mrchecker.selenium.example.env.GetEnvironmentParam;

public class DemoQAHomePage extends BasePageGUI {
    private final String url = GetEnvironmentParam.DEMO_QA_URL.getValue();
    private final String selectorCategoryCards = ".category-cards";

    public boolean isPageOpened() {
        //check unique page element
        if (getDriver().currentPage().locator(selectorCategoryCards).isVisible()) {
            //check URL
            String currentURL = getDriver().currentPage().url();
            return currentURL.equals(url);
        }
        return false;
    }
}