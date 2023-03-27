package com.capgemini.mrchecker.selenium.example.page;

import com.capgemini.mrchecker.selenium.example.base.BasePageGUI;
import com.capgemini.mrchecker.selenium.example.env.GetEnvironmentParam;
import org.openqa.selenium.By;

public class DemoQAHomePage extends BasePageGUI {
    private final String url = GetEnvironmentParam.DEMO_QA_URL.getValue();
    private final By selectorCategoryCards = By.className("category-cards");

    public boolean isPageOpened(){
        //check unique page element
        if(isElementDisplayedNoException(selectorCategoryCards)) {
            //check URL
            String currentURL = getDriver().getCurrentUrl();
            return currentURL.equals(url);
        }
        return false;
    }
}