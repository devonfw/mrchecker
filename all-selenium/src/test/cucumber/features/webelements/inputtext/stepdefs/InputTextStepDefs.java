package com.example.selenium.tests.cucumber.features.webelements.inputtext.stepdefs;

import com.example.selenium.core.BasePage;
import com.example.selenium.pages.enums.PageSubURLsEnum;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.openqa.selenium.By;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by LKURZAJ on 23.03.2017.
 */
public class InputTextStepDefs {

    private static By firstNameInputText = By.cssSelector("input[id='name_3_firstname']");
    private static String url = PageSubURLsEnum.WWW_FONT_URL.subURL() + PageSubURLsEnum.REGISTRATION.subURL();

    @Given("^I'm on Registration page as unlogged user$")
    public void goToRegistrationPageAsUnloggedUser(){
        BasePage.getDriver().get(url);
    }

    @And("^I should see first name InputText$")
    public void iShouldSeeFirstNameInputText(){
        assertTrue(BasePage.getDriver().elementInputText(firstNameInputText).isDisplayed());
    }

    @When("^I put (.+) into first name InputText$")
    public void putTextIntoFirstNameInputText(String text){
        BasePage.getDriver().elementInputText(firstNameInputText).setInputText(text);
    }

    @Then("^I should see first name InputText equal (.+)$")
    public void iShouldSeeFirstNameInputTextValue(String text){
        assertEquals(BasePage.getDriver().elementInputText(firstNameInputText).getValue(),text);
    }
}
