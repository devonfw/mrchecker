package com.capgemini.mrchecker.selenium.mts.cucumber.stepdefs;

import com.capgemini.mrchecker.selenium.mts.pages.ThaiHomePage;
import com.capgemini.mrchecker.test.core.utils.PageFactory;

import io.cucumber.java.en.And;
import io.qameta.allure.Step;

public class MenuBarSteps {
	
	private final ThaiHomePage homePage = PageFactory.getPageInstance(ThaiHomePage.class);
	
	@Step("The {tabName} tab has been opened")
	@And("The {string} has been opened")
	public void hasBeenOpened(String tabName) {
		switch (tabName) {
			case "Home":
				homePage.clickHomeButton();
				return;
			case "Menu":
				homePage.clickMenuButton();
				return;
			case "Book table":
				homePage.clickBookTable();
				return;
			default:
				throw new IllegalArgumentException(tabName + " tab was not found");
		}
	}
	
}
