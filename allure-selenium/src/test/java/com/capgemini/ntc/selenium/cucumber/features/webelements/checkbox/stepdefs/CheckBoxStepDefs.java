package com.example.selenium.tests.cucumber.features.webelements.checkbox.stepdefs;

import com.capgemini.ntc.selenium.core.BasePage;
import com.example.selenium.pages.enums.PageSubURLsEnum;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.openqa.selenium.By;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by LKURZAJ on 20.03.2017.
 */
public class CheckBoxStepDefs {

    private static final By hobbyCheckBoxSelector = By.cssSelector("li.fields.pageFields_1:nth-child(3) div.radio_wrap");
    private static final String url = PageSubURLsEnum.WWW_FONT_URL.subURL() + PageSubURLsEnum.REGISTRATION.subURL();

    @Given("^I'm as unlogged user on Registraion page$")
    public void unloggedUserOnRegistrationPage(){
        if (!BasePage.getDriver().getCurrentUrl().equals(url)){
            BasePage.getDriver().get(url);
        }
        assertTrue(BasePage.getDriver().getCurrentUrl().equals(url));
    }

    @When("^I set hobby checkbox with index (\\d{1,2})$")
    public void iSetHobbyCheckboxWithIndex(int index){
        BasePage.getDriver().elementCheckbox(CheckBoxStepDefs.hobbyCheckBoxSelector).setCheckBoxByIndex(index);
    }

    @When("^I unset hobby checkbox with index (\\d{1,2})$")
    public void iUnsetHobbyCheckboxWithIndex(int index){
        BasePage.getDriver().elementCheckbox(CheckBoxStepDefs.hobbyCheckBoxSelector).unsetCheckBoxByIndex(index);
    }
    @Then("^I should see set hobby checkbox with index (\\d{1,2})$")
    public void iShouldSeeSetHobbyCheckboxWithText(int index) {
        assertTrue(BasePage.getDriver().elementCheckbox(hobbyCheckBoxSelector).isCheckBoxSetByIndex(index));
    }

    @Then("^I should see unset hobby checkbox with index (\\d{1,2})$")
    public void iShouldSeeUnSetHobbyCheckboxWithText(int index) {
        assertTrue(!BasePage.getDriver().elementCheckbox(hobbyCheckBoxSelector).isCheckBoxSetByIndex(index));
    }


    @When("^I set hobby checkbox with text (.+)$")
    public void iSetHobbyCheckboxWithText(String text){
        BasePage.getDriver().elementCheckbox(CheckBoxStepDefs.hobbyCheckBoxSelector).setCheckBoxByText(text);
    }

    @When("^I unset hobby checkbox with text (.+)$")
    public void iUnsetHobbyCheckboxWithText(String text){
        BasePage.getDriver().elementCheckbox(CheckBoxStepDefs.hobbyCheckBoxSelector).unsetCheckBoxByText(text);
    }

    @Then("^I should see set hobby checkbox with text (.+)$")
    public void iShouldSeeSetHobbyCheckboxWithText(String text){
        assertTrue(BasePage.getDriver().elementCheckbox(CheckBoxStepDefs.hobbyCheckBoxSelector).isCheckBoxSetByText(text));
    }

    @Then("^I should see unset hobby checkbox with text (.+)$")
    public void iShouldSeeUnSetHobbyCheckboxWithText(String text){
        assertTrue(!BasePage.getDriver().elementCheckbox(CheckBoxStepDefs.hobbyCheckBoxSelector).isCheckBoxSetByText(text));
    }

    @When("^I set hobby checkbox with value (.+)$")
    public void iSetHobbyCheckboxWithValue(String value){
        BasePage.getDriver().elementCheckbox(CheckBoxStepDefs.hobbyCheckBoxSelector).setCheckBoxByValue(value);
    }

    @When("^I unset hobby checkbox with value (.+)$")
    public void iUnSetHobbyCheckboxWithValue(String value){
        BasePage.getDriver().elementCheckbox(CheckBoxStepDefs.hobbyCheckBoxSelector).unsetCheckBoxByValue(value);
    }

    @Then("^I should see set hobby checkbox with value (.+)$")
    public void iShouldSeeSetHobbyCheckboxWithValue(String value){
        assertTrue(BasePage.getDriver().elementCheckbox(CheckBoxStepDefs.hobbyCheckBoxSelector).isCheckBoxSetByValue(value));
    }

    @Then("^I should see unset hobby checkbox with value (.+)$")
    public void iShouldSeeUnsetHobbyCheckboxWithValue(String value){
        assertTrue(!BasePage.getDriver().elementCheckbox(CheckBoxStepDefs.hobbyCheckBoxSelector).isCheckBoxSetByValue(value));
    }

    @When("^I set all hobby checkboxes$")
    public void setAllCheckboxes(){
        BasePage.getDriver().elementCheckbox(CheckBoxStepDefs.hobbyCheckBoxSelector).setAllCheckBoxes();
    }

    @When("^I unset all hobby checkboxes$")
    public void unsetAllCheckboxes(){
        BasePage.getDriver().elementCheckbox(CheckBoxStepDefs.hobbyCheckBoxSelector).unsetAllCheckBoxes();
    }

    @Then("^I should see set all hobby checkboxes$")
    public void iShouldSeeAllCheckboxesSet(){
        assertTrue(BasePage.getDriver().elementCheckbox(CheckBoxStepDefs.hobbyCheckBoxSelector).isAllCheckboxesSet());
    }

    @Then("^I should see unset all hobby checkboxes$")
    public void iShouldSeeAllCheckboxesUnset(){
        assertTrue(!BasePage.getDriver().elementCheckbox(CheckBoxStepDefs.hobbyCheckBoxSelector).isAllCheckboxesSet());
    }

    @Then("^I should see (\\d{1,2}+) hobby checkboxes$")
    public void iShouldSeeNumberHobbyCheckboxes(int checkboxesNumber){
        assertEquals(checkboxesNumber,BasePage.getDriver().elementCheckbox(CheckBoxStepDefs.hobbyCheckBoxSelector).getTextList().size());
    }
}
