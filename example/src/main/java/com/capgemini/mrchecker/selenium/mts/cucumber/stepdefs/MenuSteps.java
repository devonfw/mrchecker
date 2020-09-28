package com.capgemini.mrchecker.selenium.mts.cucumber.stepdefs;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.StringContains.containsString;

import org.openqa.selenium.By;

import com.capgemini.mrchecker.selenium.mts.pages.ThaiMenuPage;
import com.capgemini.mrchecker.test.core.utils.PageFactory;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class MenuSteps {
	private final ThaiMenuPage thaiMenuPage = PageFactory.getPageInstance(ThaiMenuPage.class);
	
	@When("I type {string} in the search box")
	public void iTypeInTheSearchBox(String searchPhrase) throws InterruptedException {
		Thread.sleep(5000);
		thaiMenuPage.typeSearchPhrase(searchPhrase);
	}
	
	@And("I apply filters")
	public void iApplyFilters() throws InterruptedException {
		thaiMenuPage.applyFilters();
		Thread.sleep(5000);
	}
	
	@Then("The search result contains meals with {string}")
	public void theSearchResultContainsMealsWith(String searchPhrase) {
		thaiMenuPage.getMenuItems()
				.forEach(element -> assertThat(element.findElement(By.xpath("div[2]"))
						.getText()
						.toLowerCase(), containsString(searchPhrase)));
	}
}
