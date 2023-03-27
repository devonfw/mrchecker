package com.capgemini.mrchecker.selenium.example.test.stepdefs;

import com.capgemini.mrchecker.selenium.example.base.BaseStepDefGUI;
import com.capgemini.mrchecker.selenium.example.data.TestContext;
import com.capgemini.mrchecker.selenium.example.page.DemoQALoginPage;
import com.capgemini.mrchecker.test.core.utils.PageFactory;
import io.cucumber.java.en.Given;

public class DemoQALoginSteps extends BaseStepDefGUI {
    //Handling data between step classes
    public DemoQALoginSteps(TestContext testContext) {
        super(testContext);
    }

    @Given("Demo QA Login page is opened")
    public void startDemoQALoginPage() {
        DemoQALoginPage demoQALoginPage = PageFactory.getPageInstance(DemoQALoginPage.class);
        demoQALoginPage.startPage();
    }
}