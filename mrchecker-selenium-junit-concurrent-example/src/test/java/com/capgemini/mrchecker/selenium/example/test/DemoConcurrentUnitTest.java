package com.capgemini.mrchecker.selenium.example.test;

import com.capgemini.mrchecker.selenium.core.newDrivers.DriverManager;
import com.capgemini.mrchecker.selenium.core.utils.StepLogger;
import com.capgemini.mrchecker.selenium.example.base.BaseTestGUI;
import com.capgemini.mrchecker.selenium.example.page.DemoQALoginPage;
import com.capgemini.mrchecker.test.core.utils.PageFactory;
import io.qameta.allure.*;
import org.joda.time.Seconds;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.*;

//Multiple tests in single test class
@Execution(ExecutionMode.CONCURRENT)
public class DemoConcurrentUnitTest extends BaseTestGUI {
    private final DemoQALoginPage demoQALoginPage = PageFactory.getPageInstance(DemoQALoginPage.class);

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
        DriverManager.closeDriver();
    }

    @TmsLink("Test Management System ID")
    @Epic("Epic name")
    @Story("Story name")
    @Description("Test case description")
    @Test
    @Tag("JUnit_Tag")
    @DisplayName("Example test - Demo QA Login Page #1")
    void demoQALoginPage_Visibility_test() {
        //verify elements visibility


        assertTrue(demoQALoginPage.isDisplayedUsernameInput(), "Username input is not visible");
    }

    @TmsLink("Test Management System ID")
    @Epic("Epic name")
    @Story("Story name")
    @Description("Test case description")
    @Test
    @Tag("JUnit_Tag")
    @DisplayName("Example test - Demo QA Login Page #2")
    void demoQALoginPage_Visibility_test2() {
        //verify elements visibility
        assertTrue(demoQALoginPage.isDisplayedPasswordInput(), "Password input is not visible");
    }

    @TmsLink("Test Management System ID")
    @Epic("Epic name")
    @Story("Story name")
    @Description("Test case description")
    @Test
    @Tag("JUnit_Tag")
    @DisplayName("Example test - Demo QA Login Page #3")
    void demoQALoginPage_Visibility_test3() {
        //verify elements visibility
        assertTrue(demoQALoginPage.isDisplayedUsernameInput(), "Login button is not visible");
    }

}