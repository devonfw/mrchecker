package com.capgemini.mrchecker.selenium.example.test.stepdefs;

import com.capgemini.mrchecker.selenium.core.utils.StepLogger;
import com.capgemini.mrchecker.selenium.example.base.BaseStepDefGUI;
import com.capgemini.mrchecker.selenium.example.data.TestContext;
import com.capgemini.mrchecker.selenium.example.env.GetEnvironmentParam;
import com.capgemini.mrchecker.selenium.example.page.DemoQALoginPage;
import com.capgemini.mrchecker.test.core.utils.PageFactory;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.qameta.allure.Step;

import static org.junit.jupiter.api.Assertions.*;

public class DemoQALoginSteps extends BaseStepDefGUI {
    private DemoQALoginPage demoQALoginPage = PageFactory.getPageInstance(DemoQALoginPage.class);

    //Handling data between step classes
    public DemoQALoginSteps(TestContext testContext) {
        super(testContext);
    }

    @Given("Demo QA Login page is opened")
    public void startDemoQALoginPage() {
        demoQALoginPage.startPage();
    }

    @Then("Verify that output label is empty")
    public void verifyEmptyOutputLabel() {
        //make sure output text is empty right after page is opened
        String outputText = demoQALoginPage.getOutputText();
        assertEquals("", outputText, "Output text is not empty");
        //add info to Allure report
        StepLogger.step("Output text is empty");
    }

    @When("Enter example login credentials")
    public void enterExampleLoginCredentials() {
        String userLogin = GetEnvironmentParam.EXAMPLE_USER_LOGIN.getValue();
        String userPassword = GetEnvironmentParam.EXAMPLE_USER_PASSWORD.getValue();
        //login - page methods has their steps annotation on page class
        demoQALoginPage.fillUsername(userLogin);
        demoQALoginPage.fillPassword(userPassword);
        demoQALoginPage.clickLoginButton();
        StepLogger.makeScreenShot();
    }

    @Then("Verify that output label has proper text")
    public void verifyOutputLabelText() {
        String expectedOutputText = "Invalid username or password!";
        //make sure output text has expected value
        String outputText = demoQALoginPage.getOutputText();
        assertFalse(outputText.isEmpty(), "Output text is empty");
        assertEquals(expectedOutputText, outputText, "Output text is not as expected");
        //add info to Allure report
        StepLogger.step("Output text is correct: " + outputText);
    }

    @Then("Verify Username input is visible")
    public void verifyVisibilityOfUsernameInput() {
        //verify elements visibility
        verifyVisibilityOfElement("Username input", demoQALoginPage.isDisplayedUsernameInput());
    }

    @Then("Verify Password input is visible")
    public void verifyVisibilityOfPasswordInput() {
        verifyVisibilityOfElement("Password input", demoQALoginPage.isDisplayedPasswordInput());
    }

    @Then("Verify Login button is visible")
    public void verifyVisibilityOfLoginButton() {
        verifyVisibilityOfElement("Login button", demoQALoginPage.isDisplayedLoginButton());
    }

    @Step("Verify visibility of {elementName}")
    private void verifyVisibilityOfElement(String elementName, boolean isVisible) {
        assertTrue(isVisible, elementName + " is not visible");
        StepLogger.step(elementName + " is visible");
    }
}