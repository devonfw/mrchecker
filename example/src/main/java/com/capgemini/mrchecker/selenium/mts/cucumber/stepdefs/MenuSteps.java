package com.capgemini.mrchecker.selenium.mts.cucumber.stepdefs;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.StringContains.containsString;

import org.openqa.selenium.By;

import com.capgemini.mrchecker.selenium.mts.pages.ThaiMenuPage;
import com.capgemini.mrchecker.test.core.utils.PageFactory;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.qameta.allure.Step;

public class MenuSteps {
	private final ThaiMenuPage thaiMenuPage = PageFactory.getPageInstance(ThaiMenuPage.class);
	
	@Step("I type {searchPhrase} in the search box")
	@When("I type {string} in the search box")
	public void iTypeInTheSearchBox(String searchPhrase) throws InterruptedException {
		Thread.sleep(5000);
		thaiMenuPage.typeSearchPhrase(searchPhrase);
	}
	
	@Step("I apply filters")
	@And("I apply filters")
	public void iApplyFilters() throws InterruptedException {
		thaiMenuPage.applyFilters();
		Thread.sleep(5000);
	}
	
	@Step("The search result contains meals with {searchPhrase}")
	@Then("The search result contains meals with {string}")
	public void theSearchResultContainsMealsWith(String searchPhrase) {
		thaiMenuPage.getMenuItems()
				.forEach(element -> assertThat(element.findElement(By.xpath("div[2]"))
						.getText()
						.toLowerCase(), containsString(searchPhrase)));
	}
}
