package com.capgemini.mrchecker.selenium.example.test;

import com.capgemini.mrchecker.selenium.core.utils.StepLogger;
import com.capgemini.mrchecker.selenium.example.base.BaseTestGUI;
import com.capgemini.mrchecker.selenium.example.env.GetEnvironmentParam;
import com.capgemini.mrchecker.selenium.example.page.DemoQALinksPage;
import com.capgemini.mrchecker.selenium.example.page.DemoQALoginPage;
import com.capgemini.mrchecker.test.core.utils.PageFactory;
import io.qameta.allure.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

//Multiple tests in single test class
public class DemoQALoginPageMultipleTests extends BaseTestGUI {
    private final String userLogin = GetEnvironmentParam.EXAMPLE_USER_LOGIN.getValue();
    private final String userPassword = GetEnvironmentParam.EXAMPLE_USER_PASSWORD.getValue();
    private final DemoQALoginPage demoQALoginPage = PageFactory.getPageInstance(DemoQALoginPage.class);
    private final DemoQALinksPage demoQALinksPage = PageFactory.getPageInstance(DemoQALinksPage.class);
    private final String expectedOutputText = "Invalid username or password!";

    @Override
    //Pre steps for all tests in this class
    //Allure step - all steps executed in this method will be sub-steps
    @Step("[SETUP]")
    public void setUpTest() {
        StepLogger.step("This is example step inside setUpTest()");
        demoQALoginPage.startPage();
    }

    @Override
    //Post steps for all tests in this class
    //Allure step - all steps executed in this method will be sub-steps
    @Step("[TEARDOWN]")
    public void tearDownTest() {
        StepLogger.step("This is example step inside tearDownTest()");
        demoQALinksPage.startPage();
    }

    //Allure annotations - will have impact on Allure report
    @TmsLink("Test Management System ID")
    @Epic("Epic name")
    @Story("Story name")
    @Description("Test case description")
    //JUnit annotations
    @Test
    @Tag("JUnit_Tag")
    @DisplayName("Example test - Demo QA Login Page #1")
    void demoQALoginPage_Login_test() {
        //make sure output text is empty right after page is opened
        String outputText = demoQALoginPage.getOutputText();
        assertEquals("", outputText, "Output text is not empty");
        //add info to Allure report
        StepLogger.step("Output text is empty");
        //login - page methods has their steps annotation on page class
        demoQALoginPage.fillUsername(userLogin);
        demoQALoginPage.fillPassword(userPassword);
        demoQALoginPage.clickLoginButton();
        StepLogger.makeScreenShot();
        //make sure output text has expected value
        outputText = demoQALoginPage.getOutputText();
        assertFalse(outputText.isEmpty(), "Output text is empty");
        assertEquals(expectedOutputText, outputText, "Output text is not as expected");
        //add info to Allure report
        StepLogger.step("Output text is correct: " + outputText);
    }


    //Allure annotations - will have impact on Allure report
    @TmsLink("Test Management System ID")
    @Epic("Epic name")
    @Story("Story name")
    @Description("Test case description")
    //JUnit annotations
    @Test
    @Tag("JUnit_Tag")
    @DisplayName("Example test - Demo QA Login Page #2")
    void demoQALoginPage_Visibility_test() {
        //verify elements visibility
        verifyVisibilityOfElement("Username input",demoQALoginPage.isDisplayedUsernameInput());
        verifyVisibilityOfElement("Password input",demoQALoginPage.isDisplayedPasswordInput());
        verifyVisibilityOfElement("Login button",demoQALoginPage.isDisplayedLoginButton());
    }

    @Step("Verify visibility of {elementName}")
    private void verifyVisibilityOfElement(String elementName, boolean isVisible) {
        assertTrue(isVisible, elementName + " is not visible");
        StepLogger.step(elementName + " is visible");
    }
}