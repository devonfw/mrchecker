package com.capgemini.mrchecker.selenium.example.test.stepdefs;

import com.capgemini.mrchecker.selenium.core.utils.StepLogger;
import com.capgemini.mrchecker.selenium.example.base.BaseStepDefGUI;
import com.capgemini.mrchecker.selenium.example.data.TestContext;
import com.capgemini.mrchecker.selenium.example.page.DemoQALinksPage;
import com.capgemini.mrchecker.test.core.utils.PageFactory;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.qameta.allure.Step;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class DemoQALinksSteps extends BaseStepDefGUI {
    private DemoQALinksPage demoQALinksPage = PageFactory.getPageInstance(DemoQALinksPage.class);

    //Handling data between step classes
    public DemoQALinksSteps(TestContext testContext) {
        super(testContext);
    }

    @Given("Demo QA Links page is opened")
    public void startDemoQALoginPage() {
        demoQALinksPage.startPage();
    }

    @Then("Verify Home link is visible")
    public void verifyVisibilityOfHomeLink() {
        verifyVisibilityOfElement("Home link", demoQALinksPage.isDisplayedHomeLink());
    }

    @Step("Verify visibility of {elementName}")
    private void verifyVisibilityOfElement(String elementName, boolean isVisible) {
        assertTrue(isVisible, elementName + " is not visible");
        StepLogger.step(elementName + " is visible");
    }


    @When("Use Home link")
    public void clickHomeLink() {
        //use link on the page (wrapped by Allure step on page class)
        demoQALinksPage.clickHomeLink();
    }

    @Then("Verify Links page is opened")
    public void verifyLinksPageIsOpened() {
        //Verify if Links page is opened on the tab
        assertTrue(demoQALinksPage.isPageOpened(), "Links page is not opened in active tab");
        //Add info step to Allure report
        StepLogger.step("Links page is opened in active tab");
        StepLogger.makeScreenShot();
    }
}