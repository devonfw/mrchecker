package com.capgemini.mrchecker.selenium.example.test;

import com.capgemini.mrchecker.selenium.core.BasePage;
import com.capgemini.mrchecker.selenium.core.utils.StepLogger;
import com.capgemini.mrchecker.selenium.example.base.BaseTestGUI;
import com.capgemini.mrchecker.selenium.example.page.DemoQAHomePage;
import com.capgemini.mrchecker.selenium.example.page.DemoQALinksPage;
import com.capgemini.mrchecker.test.core.utils.PageFactory;
import io.qameta.allure.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class DemoQALinksPageSingleTest extends BaseTestGUI {
    private final DemoQAHomePage demoQAHomePage = PageFactory.getPageInstance(DemoQAHomePage.class);
    private final DemoQALinksPage demoQALinksPage = PageFactory.getPageInstance(DemoQALinksPage.class);

    @Override
    //Pre steps for all tests in this class
    //Allure step - all steps executed in this method will be sub-steps
    @Step("[SETUP]")
    public void setUpTest() {
        StepLogger.step("This is example step inside setUpTest()");
        demoQALinksPage.startPage();
    }

    @Override
    //Post steps for all tests in this class
    //Allure step - all steps executed in this method will be sub-steps
    @Step("[TEARDOWN]")
    public void tearDownTest() {
        StepLogger.step("This is example step inside tearDownTest()");
    }

    //Allure annotations - will have impact on Allure report
    @TmsLink("Test Management System ID")
    @Epic("Epic name")
    @Story("Story name")
    @Description("Test case description")
    //JUnit annotations
    @Test
    @Tag("JUnit_Tag")
    @DisplayName("Example test - Demo QA Links Page #1")
    void demoQALoginPage_Login_test() {
        //verify if single tab is opened
        int amountOfOpenedTabs = BasePage.getDriver().getWindowHandles().size();
        assertEquals(1, amountOfOpenedTabs, "Incorrect amount of opened tabs");
        String firstTabHandle = BasePage.getDriver().getWindowHandles().stream().findFirst().get();
        //Add info step to Allure report
        StepLogger.step("There is single tab opened");
        //use link on the page (wrapped by Allure step on page class)
        demoQALinksPage.clickHomeLink();
        //verify if new tab was opened
        amountOfOpenedTabs = BasePage.getDriver().getWindowHandles().size();
        assertEquals(2, amountOfOpenedTabs, "Incorrect amount of opened tabs");
        //Add info step to Allure report
        StepLogger.step("There are two tabs opened");

        //Driver needs to switch to use second tab
        List<String> handles = new ArrayList<>(BasePage.getDriver().getWindowHandles());
        handles.remove(firstTabHandle);
        String secondTabHandle = handles.get(0);
        BasePage.getDriver().switchTo().window(secondTabHandle);

        //verify if Home page is opened on active tab
        assertTrue(demoQAHomePage.isPageOpened(), "Home page is not opened in active tab");
        //Add info step to Allure report
        StepLogger.step("Home page is opened in active tab");
        StepLogger.makeScreenShot();
        //close current active tab
        BasePage.getDriver().close();
        //verify if single tab is opened
        amountOfOpenedTabs = BasePage.getDriver().getWindowHandles().size();
        assertEquals(1, amountOfOpenedTabs, "Incorrect amount of opened tabs");
        //Add info step to Allure report
        StepLogger.step("There is single tab opened");

        //Driver needs to switch to use first tab
        BasePage.getDriver().switchTo().window(firstTabHandle);

        //Verify if Links page is opened on the tab
        assertTrue(demoQALinksPage.isPageOpened(), "Links page is not opened in active tab");
        //Add info step to Allure report
        StepLogger.step("Links page is opened in active tab");
        StepLogger.makeScreenShot();
    }
}