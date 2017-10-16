package com.example.selenium.tests.cucumber.features.webelements.button.stepdefs;

import com.example.selenium.core.BasePage;
import com.example.selenium.pages.enums.PageSubURLsEnum;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.openqa.selenium.By;
import org.springframework.stereotype.Component;
import org.springframework.test.context.ContextConfiguration;

import static org.junit.Assert.assertTrue;

/**
 * Created by LKURZAJ on 20.03.2017.
 */
@Component
@ContextConfiguration("classpath:spring-context.xml")
public class ButtonStepDefs {

    private By submitButton = By.cssSelector("button#submit");
    private String url = PageSubURLsEnum.TOOLS_QA.subURL() + PageSubURLsEnum.AUTOMATION_PRACTICE_FORM.subURL();


    @Given("^I'm as unlogger user on AUTOMATION PRACTICE FORM site$")
    public void navigateToAutomationPracticeFormSite(){
        if (!BasePage.getDriver().getCurrentUrl().equals(url)){
            BasePage.getDriver().get(url);
        }
        assertTrue(BasePage.getDriver().getCurrentUrl().equals(url));
    }

    @And("^I should see Submit Button$")
    public void iShouldSeeSubmitButton(){
        assertTrue(BasePage.getDriver().elementButton(submitButton).isDisplayed());
    }

    @When("^I check Submit Button type$")
    public void iCheckSubmitButtonType(){
        assertTrue(BasePage.getDriver().elementButton(submitButton).getElementTypeName().equals("Button"));
    }

    @And("^I click on Submit Button$")
    public void clickOnSubmitButton(){
        BasePage.getDriver().elementButton(submitButton).click();
    }

    @Then("^I should be on Submit site$")
    public void iShouldBeOnSubmitSite(){
        assertTrue(BasePage.getDriver().getCurrentUrl().contains("&submit="));
    }

}
