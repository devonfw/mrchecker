package com.capgemini.mrchecker.cucumber.stepdefs.selenium.projectY;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;

import com.capgemini.mrchecker.selenium.pages.projectY.CheckboxesPage;
import com.capgemini.mrchecker.test.core.utils.PageFactory;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.qameta.allure.Step;

public class CheckboxesStepdefs {
	
	private CheckboxesPage checkboxesPage;
	
	@Step("CheckboxesPage is opened")
	@Given("CheckboxesPage is opened")
	public void setCheckboxesPageIsOpened() {
		checkboxesPage = PageFactory.getPageInstance(CheckboxesPage.class);
		checkboxesPage.load();
	}
	
	@Step("The {checkboxNumber} checkbox initial state is {state}")
	@And("The {string} checkbox initial state is {string}")
	public void theCheckboxInitialStateIs(String checkboxNumber, String state) {
		CheckboxState expectedCheckboxState = CheckboxState.valueOf(state.toUpperCase());
		int checkboxIndex = normalizeIndex(checkboxNumber);
		if (checkboxesPage.isCheckboxSelected(checkboxIndex) != expectedCheckboxState.isChecked())
			checkboxesPage.selectCheckbox(checkboxIndex);
		
		theCheckboxIsIn(checkboxNumber, state);
	}
	
	@Step("The {checkboxNumber} checkbox is in {state}")
	@And("The {string} checkbox is in {string}")
	public void theCheckboxIsIn(String checkboxNumber, String state) {
		CheckboxState expectedCheckboxState = CheckboxState.valueOf(state.toUpperCase());
		int checkboxIndex = normalizeIndex(checkboxNumber);
		assertThat(checkboxesPage.isCheckboxSelected(checkboxIndex), is(equalTo(expectedCheckboxState.isChecked())));
	}
	
	@Step("I click on the {checkboxNumber} checkbox")
	@When("I click on the {string} checkbox")
	public void iClickOnTheCheckbox(String checkboxNumber) {
		int checkboxIndex = normalizeIndex(checkboxNumber);
		checkboxesPage.selectCheckbox(checkboxIndex);
	}
	
	private static int normalizeIndex(String initial) {
		return Integer.parseInt(initial) - 1;
	}
	
	private enum CheckboxState {
		CHECKED(true),
		UNCHECKED(false);
		
		private final boolean isChecked;
		
		CheckboxState(boolean isChecked) {
			this.isChecked = isChecked;
		}
		
		public boolean isChecked() {
			return isChecked;
		}
	}
}
