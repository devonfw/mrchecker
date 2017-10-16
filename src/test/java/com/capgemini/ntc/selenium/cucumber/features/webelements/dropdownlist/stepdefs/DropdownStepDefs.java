package com.capgemini.ntc.selenium.tests.cucumber.features.webelements.dropdownlist.stepdefs;

import com.capgemini.ntc.selenium.core.BasePage;
import com.capgemini.ntc.selenium.pages.enums.PageSubURLsEnum;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.openqa.selenium.By;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by LKURZAJ on 22.03.2017.
 */
public class DropdownStepDefs {

    private static By countryDropdown = By.cssSelector("select#dropdown_7");
    private String url = PageSubURLsEnum.WWW_FONT_URL.subURL() + PageSubURLsEnum.REGISTRATION.subURL();

    @Given("^I'm on Registration page as unlogged user$")
    public void unloggedUserOnRegistrationPage(){
        if (!BasePage.getDriver().getCurrentUrl().equals(url)){
            BasePage.getDriver().get(url);
        }
        assertTrue(BasePage.getDriver().getCurrentUrl().equals(url));
    }

    @When("^I select country dropdown list element with index (\\d{1,2})$")
    public void selectCountryDropdownElementWithIndex(int index){
        BasePage.getDriver().elementDropdownList(DropdownStepDefs.countryDropdown).selectDropdownByIndex(index);
    }

    @Then("^I should see selected country dropdown list element with index (\\d+)$")
    public void isCountryDropdownElementSelectedByIndex(int index){
        assertTrue(BasePage.getDriver().elementDropdownList(countryDropdown).isDropdownElementSelectedByIndex(index));
    }

    @When("^I select country dropdown list element with text (.+)$")
    public void selectCountryDropdownElementWithText(String text){
        BasePage.getDriver().elementDropdownList(DropdownStepDefs.countryDropdown).selectDropdownByVisibleText(text);
    }

    @Then("^I should see selected country dropdown list element with text (.+)$")
    public void isCountryDropdownElementSelectedByText(String text){
        assertEquals(BasePage.getDriver().elementDropdownList(countryDropdown).getFirstSelectedOptionText(), text);
    }

    @When("^I select country dropdown list element with value (.+)$")
    public void selectContryDropdownElementWithValue(String value){
        BasePage.getDriver().elementDropdownList(DropdownStepDefs.countryDropdown).selectDropdownByValue(value);
    }

    @Then("^I should see selected country dropdown list element with value (.+)$")
    public void isCountryDropdownElementSelectedByValue(String value){
        assertTrue(BasePage.getDriver().elementDropdownList(countryDropdown).isDropdownElementSelectedByValue(value));
    }

    @And("^I should see country dropdown list$")
    public void isCountryDropdownListDisplayed(){
        assertTrue(BasePage.getDriver().elementDropdownList(countryDropdown).isDisplayed());
    }

    @Then("^I should see country dropdown list with (\\d+) possible values$")
    public void isNumberOfPossibleValuesInCountryDropdownListEquals(int number){
        assertEquals(BasePage.getDriver().elementDropdownList(countryDropdown).getAmountOfPossibleValues(),number);
    }

    @Then("^I should see (\\d+) selected country dropdown list element$")
    public void isNumberOfSelectedCountryDropdownListElementsEquals(int number){
        assertEquals(BasePage.getDriver().elementDropdownList(countryDropdown).getAllSelectedOptionsText().size(),number);
    }
}
