package com.capgemini.mrchecker.playwright.example.base;


import com.capgemini.mrchecker.playwright.core.BasePage;
import com.capgemini.mrchecker.playwright.core.utils.StepLogger;
import io.qameta.allure.Step;

public class BasePageGUI extends BasePage {
    @Override
    public String pageTitle() {
        return null;
    }

    @Override
    public boolean isLoaded() {
        return false;
    }

    @Override
    public void load() {}

    @Override
    //Override method to easily use param in Allure step
    @Step("Open page: {url}")
    public void loadPage(String url) {
        super.loadPage(url);
        StepLogger.makeScreenShot();
    }
}