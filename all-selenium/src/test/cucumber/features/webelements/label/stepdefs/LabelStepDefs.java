package com.example.selenium.tests.cucumber.features.webelements.label.stepdefs;

import com.example.selenium.core.BasePage;
import com.example.selenium.pages.enums.PageSubURLsEnum;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import org.openqa.selenium.By;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by LKURZAJ on 27.03.2017.
 */
public class LabelStepDefs {

    private static By text1Label = By.cssSelector("span.bcd");

    @Given("^I'm on AUTOMATION PRACTICE FORM site as unlogged user$")
    public void iAmOnAutomationPracticeFormAsUnloggedUser(){
        BasePage.getDriver().get(PageSubURLsEnum.TOOLS_QA.subURL() + PageSubURLsEnum.AUTOMATION_PRACTICE_FORM.subURL());
    }

    @And("^I should see text1 Label$")
    public void iShouldSeeText1Label(){
        assertTrue(BasePage.getDriver().elementLabel(LabelStepDefs.text1Label).isDisplayed());
    }

    @And("^I check text1 Label type$")
    public void iCheckText1LabelType(){
        assertEquals("Label",BasePage.getDriver().elementLabel(LabelStepDefs.text1Label).getElementTypeName());
    }

    @Then("^I should see text1 Label with text: (.+)$")
    public void iShouldSeeText1LabelWithText(String text){
        assertEquals(text,BasePage.getDriver().elementLabel(LabelStepDefs.text1Label).getText());
    }
}
