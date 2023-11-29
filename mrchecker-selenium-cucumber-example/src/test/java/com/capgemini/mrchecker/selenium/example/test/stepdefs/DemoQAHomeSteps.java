package com.capgemini.mrchecker.selenium.example.test.stepdefs;

import com.capgemini.mrchecker.selenium.core.utils.StepLogger;
import com.capgemini.mrchecker.selenium.example.base.BaseStepDefGUI;
import com.capgemini.mrchecker.selenium.example.data.TestContext;
import com.capgemini.mrchecker.selenium.example.page.DemoQAHomePage;
import com.capgemini.mrchecker.test.core.utils.PageFactory;
import io.cucumber.java.en.Then;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class DemoQAHomeSteps extends BaseStepDefGUI {
    private DemoQAHomePage demoQAHomePage = PageFactory.getPageInstance(DemoQAHomePage.class);

    //Handling data between step classes
    public DemoQAHomeSteps(TestContext testContext) {
        super(testContext);
    }

    @Then("Verify if Home page is opened on active tab")
    public void verifyPageIsOpened() {
        //verify if Home page is opened on active tab
        assertTrue(demoQAHomePage.isPageOpened(), "Home page is not opened in active tab");
        //Add info step to Allure report
        StepLogger.step("Home page is opened in active tab");
        StepLogger.makeScreenShot();
    }
}