package com.capgemini.mrchecker.selenium.pages.projectX.registration;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.openqa.selenium.By;

import com.capgemini.mrchecker.selenium.core.BasePage;
import com.capgemini.mrchecker.selenium.core.exceptions.BFElementNotFoundException;
import com.capgemini.mrchecker.selenium.core.newDrivers.INewWebDriver;
import com.capgemini.mrchecker.selenium.core.newDrivers.elementType.DropdownListElement;
import com.capgemini.mrchecker.selenium.core.newDrivers.elementType.InputTextElement;
import com.capgemini.mrchecker.selenium.pages.environment.GetEnvironmentParam;
import com.capgemini.mrchecker.selenium.pages.environment.PageSubURLsProjectXEnum;
import com.capgemini.mrchecker.selenium.pages.environment.PageTitlesEnum;
import com.capgemini.mrchecker.test.core.logger.BFLogger;

public class RegistrationPage extends BasePage {
	
	private BasePage	parent;
	private boolean		loaded;
	
	private final static By	selectorFirstName				= By.cssSelector("input[id='name_3_firstname']");
	private final static By	selectorLastName				= By.cssSelector("input[id='name_3_lastname']");
	private final static By	selectorMaritalStatus			= By.cssSelector("div[class='radio_wrap']");
	private final static By	selectorHobby					= By.cssSelector("li.fields.pageFields_1:nth-child(3) div.radio_wrap");
	private final static By	selectorCountry					= By.cssSelector("select[id='dropdown_7']");
	private final static By	selectorBirthDateDay			= By.cssSelector("select[id='dd_date_8']");
	private final static By	selectorBirthDateMonth			= By.cssSelector("select[id='mm_date_8']");
	private final static By	selectorBirthDateYear			= By.cssSelector("select[id='yy_date_8']");
	private final static By	selectorPhoneNumber				= By.cssSelector("input[id='phone_9']");
	private final static By	selectorUsername				= By.cssSelector("input[id='username']");
	private final static By	selectorEmail					= By.cssSelector("input[id='email_1']");
	private final static By	selectorYourProfilePhotoPath	= By.cssSelector("input[type='file']");
	private final static By	selectorAboutYourself			= By.cssSelector("textarea[ id='description' ]");
	private final static By	selectorPassword				= By.cssSelector("input[id='password_2']");
	private final static By	selectorConfirmPassword			= By.cssSelector("input[id='confirm_password_password_2']");
	private final static By	selectorSubmitButton			= By.cssSelector("input[name='pie_submit']");
	private final static By	selectorRegistrationSucceed		= By.cssSelector("p[class='piereg_message']");
	private final static By	selectorRegistrationError		= By.cssSelector("p[class='piereg_login_error']");
	
	private static String	registrationSucceedText	= "Thank you for your registration";
	private static String	registrationErrorText	= "Error: Username already exists";
	
	private final String pattern = "(.*)-(.*)-(.*)";
	
	public RegistrationPage() {
		this(getDriver());
	}
	
	public RegistrationPage(INewWebDriver driver) {
		super(driver, null);
		
		// Execute for current page
		if (!this.isLoaded()) { // In this scenario check if current page is loaded, if not, then try to
								// load it
			this.load();
		}
	}
	
	@Override
	public boolean isLoaded() {
		return isUrlAndPageTitleAsCurrentPage(GetEnvironmentParam.WWW_FONT_URL.getValue() + PageSubURLsProjectXEnum.REGISTRATION.getValue());
	}
	
	@Override
	public void load() {
		BasePage.getDriver()
						.get(GetEnvironmentParam.WWW_FONT_URL.getValue() + PageSubURLsProjectXEnum.REGISTRATION.getValue());
		loaded = true;
	}
	
	public void load(PageSubURLsProjectXEnum subUrl) {
		BasePage.getDriver()
						.get(GetEnvironmentParam.WWW_FONT_URL.getValue() + subUrl.getValue());
		loaded = true;
	}
	
	public BasePage getParent() {
		return this.parent;
	}
	
	public void setParent(BasePage parent) {
		this.parent = parent;
	}
	
	public boolean wasLoaded() {
		return loaded;
	}
	
	public void clickSubmit() {
		getDriver().elementButton(selectorSubmitButton)
						.click();
	}
	
	public boolean isRegistrationSuceedTextVisible() {
		try {
			return getDriver().findElementDynamic(selectorRegistrationSucceed)
							.getText()
							.equals(registrationSucceedText);
		} catch (BFElementNotFoundException e) {
			return false;
		}
	}
	
	public boolean isRegistryErrorTextVisible() {
		try {
			getDriver().waitForElementVisible(selectorRegistrationError);
			BFLogger.logDebug(getDriver().waitForElementVisible(selectorRegistrationError)
							.getText());
			return getDriver().elementLabel(selectorRegistrationError)
							.getText()
							.equals(registrationErrorText);
		} catch (BFElementNotFoundException e) {
			return false;
		}
	}
	
	public String getRegistryErrorText() {
		getDriver().waitForElementVisible(selectorRegistrationError);
		return getDriver().elementLabel(selectorRegistrationError)
						.getText();
	}
	
	@Override
	public String pageTitle() {
		return PageTitlesEnum.REGISTRATION.toString();
	}
	
	public void setFirstName(String value) {
		InputTextElement inputTextElement = new InputTextElement(selectorFirstName);
		inputTextElement.clearInputText();
		inputTextElement.setInputText(value);
	}
	
	public void setLastName(String value) {
		InputTextElement inputTextElement = new InputTextElement(selectorLastName);
		inputTextElement.clearInputText();
		inputTextElement.setInputText(value);
	}
	
	public void setMaritalStatus(MaritalStatus maritalStatus) {
		
		switch (maritalStatus) {
			case SINGLE: {
				getDriver().elementRadioButton(selectorMaritalStatus)
								.selectItemByValue("single");
				
				// radioButtonElement.selectElement("Single");
				break;
			}
			case MARRIED: {
				getDriver().elementRadioButton(selectorMaritalStatus)
								.selectItemByValue("married");
				// radioButtonElement.selectElement("Married");
				break;
			}
			case DIVORCED: {
				getDriver().elementRadioButton(selectorMaritalStatus)
								.selectItemByValue("divorced");
				// radioButtonElement.selectElement("Divorced");
				break;
			}
		}
	}
	
	public void setHobby(com.capgemini.mrchecker.selenium.pages.projectX.registration.Hobby hobby) {
		getDriver().elementCheckbox(selectorHobby)
						.setCheckBoxByValue(hobby.toString());
	}
	
	public void setHobby(com.capgemini.mrchecker.selenium.pages.projectX.registration.Hobby[] hobbies) {
		com.capgemini.mrchecker.selenium.pages.projectX.registration.Hobby hobby;
		
		for (int i = 0; i < hobbies.length; i++) {
			hobby = hobbies[i];
			this.setHobby(hobby);
		}
	}
	
	public void setCountry(String value) {
		this.setComboBoxValue(selectorCountry, value);
	}
	
	public void setBirthDate(String date) {
		// date format -> mm-dd-yyyy
		Pattern r = Pattern.compile(pattern);
		Matcher m = r.matcher(date);
		
		if (m.find()) {
			this.setComboBoxValue(selectorBirthDateMonth, m.group(1));
			this.setComboBoxValue(selectorBirthDateDay, m.group(2));
			this.setComboBoxValue(selectorBirthDateYear, m.group(3));
		} else {
			System.out.println("NO MATCH");
		}
	}
	
	public void setPhoneNumber(String value) {
		InputTextElement inputTextElement = new InputTextElement(selectorPhoneNumber);
		inputTextElement.clearInputText();
		inputTextElement.setInputText(value);
	}
	
	public void setUsername(String value) {
		InputTextElement inputTextElement = new InputTextElement(selectorUsername);
		inputTextElement.clearInputText();
		inputTextElement.setInputText(value);
	}
	
	public void setEmail(String value) {
		InputTextElement inputTextElement = new InputTextElement(selectorEmail);
		inputTextElement.clearInputText();
		inputTextElement.setInputText(value);
	}
	
	public void setProfilePicture(String value) {
		getDriver().findElementDynamic(selectorYourProfilePhotoPath)
						.sendKeys(value);
	}
	
	public void setAboutYourself(String value) {
		InputTextElement inputTextElement = new InputTextElement(selectorAboutYourself);
		inputTextElement.clearInputText();
		inputTextElement.setInputText(value);
	}
	
	public void setPassword(String value) {
		InputTextElement inputTextElement = new InputTextElement(selectorPassword);
		inputTextElement.clearInputText();
		inputTextElement.setInputText(value);
	}
	
	public void setConfirmPassword(String value) {
		InputTextElement inputTextElement = new InputTextElement(selectorConfirmPassword);
		inputTextElement.clearInputText();
		inputTextElement.setInputText(value);
	}
	
	private void setComboBoxValue(By comboBoxObject, String value) {
		new DropdownListElement(comboBoxObject).selectDropdownByValue(value);
	}
	
}
