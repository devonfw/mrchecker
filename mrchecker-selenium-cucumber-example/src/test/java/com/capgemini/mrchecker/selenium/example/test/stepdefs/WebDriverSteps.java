package com.capgemini.mrchecker.selenium.example.test.stepdefs;

import com.capgemini.mrchecker.selenium.core.BasePage;
import com.capgemini.mrchecker.selenium.core.utils.StepLogger;
import com.capgemini.mrchecker.selenium.example.base.BaseStepDefGUI;
import com.capgemini.mrchecker.selenium.example.data.TestContext;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class WebDriverSteps extends BaseStepDefGUI {
    //Handling data between step classes
    public WebDriverSteps(TestContext testContext) {
        super(testContext);
    }

    @Then("Verify that only one tab is opened")
    public void verifySingleTab() {
        //verify if single tab is opened
        int amountOfOpenedTabs = BasePage.getDriver().getWindowHandles().size();
        assertEquals(1, amountOfOpenedTabs, "Incorrect amount of opened tabs");
        String firstTabHandle = BasePage.getDriver().getWindowHandles().stream().findFirst().get();
        //Add info step to Allure report
        StepLogger.step("There is single tab opened");

        //add first tab handle to test context
        getTestContext().set("firstTabHandle", firstTabHandle);
    }
    
    @Then("Verify that two tabs are opened")
    public void verifyTwoTabs() {
        //verify if new tab was opened
        int amountOfOpenedTabs = BasePage.getDriver().getWindowHandles().size();
        assertEquals(2, amountOfOpenedTabs, "Incorrect amount of opened tabs");
        //Add info step to Allure report
        StepLogger.step("There are two tabs opened");

        //Read variable from test context
        String firstTabHandle = getTestContext().get("firstTabHandle");

        //Driver needs to switch to use second tab
        List<String> handles = new ArrayList<>(BasePage.getDriver().getWindowHandles());
        handles.remove(firstTabHandle);
        String secondTabHandle = handles.get(0);
        BasePage.getDriver().switchTo().window(secondTabHandle);
    }

    @When("Close current tab")
    public void closeTab() {
        //close current active tab
        BasePage.getDriver().close();

        //Read variable from test context
        String firstTabHandle = getTestContext().get("firstTabHandle");

        //Driver needs to switch to use first tab
        BasePage.getDriver().switchTo().window(firstTabHandle);
    }
}