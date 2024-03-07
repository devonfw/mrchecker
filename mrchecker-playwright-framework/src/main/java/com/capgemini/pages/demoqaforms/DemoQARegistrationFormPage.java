package com.capgemini.pages.demoqaforms;

import com.capgemini.framework.actions.ActionGui;
import com.capgemini.framework.enums.DemoQAStudent;
import com.capgemini.framework.environment.typesafeconfig.EnvironmentConfig;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;
import io.qameta.allure.Step;

import java.util.regex.Pattern;

import static com.capgemini.framework.playwright.PlaywrightFactory.getPage;

public class DemoQARegistrationFormPage {
	private static final String  url                  = EnvironmentConfig.getDemoQAStudentRegistrationForm();
	private static final int     PAGE_LOADING_TIMEOUT = 30000;
	private              Locator locatorFistNameInput;
	private final        String  selectorFistName     = "form[id='userForm'] input[id='firstName']";
	private              Locator locatorLastNameInput;
	private              Locator locatorMailInput;
	private              Locator locatorMaleRadioButton;
	private              Locator locatorFemaleRadioButton;
	private              Locator locatorOtherRadioButton;
	private              Locator locatorMobileNumberInput;
	private              Locator locatorDateOfBirthInput;
	private              Locator locatorSubjectInput;
	private              Locator locatorCurrentAddressInput;
	private              Locator locatorStateComboBox;
	private              Locator locatorStateInput;
	private              Locator locatorCityComboBox;
	private              Locator locatorDateOfBirthLabel;
	private              Locator locatorCityInput;
	private              Locator locatorStateCityLabel;
	private              Locator locatorSubmitButton;
	
	@Step("Open Registration Form")
	public void startPage() {
		Page page = getPage();
		ActionGui.navigate(url, PAGE_LOADING_TIMEOUT);
		page.getByLabel("Consent", new Page.GetByLabelOptions().setExact(true))
				.click();
		initLocatorsForNewBrowserContext();
	}
	

	
	public DemoQARegistrationFormPage() {
	}
	
	public DemoQARegistrationFormPage initLocatorsForNewBrowserContext() {
		var page = getPage();
		//page.waitForLoadState(LoadState.NETWORKIDLE, new Page.WaitForLoadStateOptions().setTimeout(PAGE_LOADING_TIMEOUT));
		page.waitForSelector("#firstName", new Page.WaitForSelectorOptions().setTimeout(PAGE_LOADING_TIMEOUT));
		locatorFistNameInput = page.getByPlaceholder("First Name");
		locatorLastNameInput = page.getByPlaceholder("Last Name");
		locatorMailInput = page.getByPlaceholder("name@example.com");
		locatorMaleRadioButton = page.getByText("Male", new Page.GetByTextOptions().setExact(true));
		
		locatorFemaleRadioButton = page.getByText("MaleFemaleOther")
				.filter()
				.getByText("Female");
		locatorOtherRadioButton = page.getByText("MaleFemaleOther")
				.filter()
				.getByText("Other");
		locatorMobileNumberInput = page.getByPlaceholder("Mobile Number");
		locatorDateOfBirthInput = page.locator("#dateOfBirthInput");
		locatorDateOfBirthLabel = page.getByText("Date of Birth");
		
		locatorSubjectInput = page.locator("#subjectsInput");
		locatorCurrentAddressInput = page.getByPlaceholder("Current Address");
		locatorStateComboBox = page.locator("#state");
		locatorStateInput = page.locator("#state input");
		locatorCityComboBox = page.locator("#city");
		locatorCityInput = page.locator("#city input");
		locatorStateCityLabel = page.getByText("State and City");
		locatorSubmitButton = page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Submit"));
		return this;
	}
	
	public DemoQARegistrationFormPage fillFirstName(String firstName) {
		ActionGui.fillTextBox("First name", firstName, locatorFistNameInput);
		return this;
	}
	
	public DemoQARegistrationFormPage fillLastName(String lastName) {
		ActionGui.fillTextBox("Last name", lastName, locatorLastNameInput);
		return this;
	}
	
	public DemoQARegistrationFormPage fillMail(String mail) {
		ActionGui.fillTextBox("Mail", mail, locatorMailInput);
		return this;
	}
	
	public DemoQARegistrationFormPage selectGender(DemoQAStudent.Gender gender) {
		switch (gender) {
			case MALE -> ActionGui.setRadioButton("Gender Male", locatorMaleRadioButton);
			case FEMALE -> ActionGui.setRadioButton("Gender Female", locatorFemaleRadioButton);
			case OTHER -> ActionGui.setRadioButton("Gender Other", locatorOtherRadioButton);
		}
		return this;
	}
	
	public DemoQARegistrationFormPage fillMobileNumber(String mobileNumber) {
		ActionGui.fillTextBox("Mobile number", mobileNumber, locatorMobileNumberInput);
		return this;
	}
	
	public DemoQARegistrationFormPage fillDateOfBirth(String dateOfBirth) {
		ActionGui.fillTextBox("Date of birth", dateOfBirth, locatorDateOfBirthInput);
		ActionGui.click("Date of birth", locatorDateOfBirthLabel);
		
		return this;
	}
	
	public DemoQARegistrationFormPage fillSubject(String subject) {
		ActionGui.fillTextBox("Subject", subject + "\n", locatorSubjectInput);
		return this;
	}
	
	@Step("Fill hobby with {hobbies}")
	public DemoQARegistrationFormPage fillHobbies(DemoQAStudent.Hobbies hobbies) {
		switch (hobbies) {
			case Sports -> getPage().getByText("Sports")
					.click();
			case Reading -> getPage().getByText("Reading")
					.click();
			case Music -> getPage().getByText("Music")
					.click();
		}
		
		return this;
	}
	
	public DemoQARegistrationFormPage fillCurrentAddress(String currentAddress) {
		ActionGui.fillTextBox("CurrentAddress", currentAddress, locatorCurrentAddressInput);
		return this;
	}
	
	@Step("Fill state with {state}")
	public DemoQARegistrationFormPage fillState(String state) {
		locatorSubmitButton.scrollIntoViewIfNeeded();
		ActionGui.click("State", locatorStateComboBox);
		getPage().locator("div")
				.filter(new Locator.FilterOptions().setHasText(Pattern.compile("^" + state + "$")))
				.click();
		
		return this;
	}
	
	@Step("Fill city with {city}")
	public DemoQARegistrationFormPage fillCity(DemoQAStudent.Ncr_City city) {
		locatorSubmitButton.scrollIntoViewIfNeeded();
		ActionGui.click("City", locatorCityComboBox);
		getPage().getByText(city.name(), new Page.GetByTextOptions().setExact(true))
				.click();
		return this;
	}
	
	public void clickSubmitButton() {
		ActionGui.click("Submit button", locatorSubmitButton);
	}
}
