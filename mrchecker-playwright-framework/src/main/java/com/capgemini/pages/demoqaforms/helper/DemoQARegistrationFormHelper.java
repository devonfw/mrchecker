package com.capgemini.pages.demoqaforms.helper;

import com.capgemini.framework.enums.DemoQAStudent;
import com.capgemini.framework.environment.typesafeconfig.EnvironmentConfig;
import com.capgemini.framework.logger.AllureStepLogger;
import com.capgemini.pages.demoqaforms.DemoQARegistrationFormPage;
import com.microsoft.playwright.Page;
import io.qameta.allure.Step;

import static com.capgemini.framework.playwright.PlaywrightFactory.getPage;

public class DemoQARegistrationFormHelper {
	
	@Step("Fill registration Form")
	public void fillRegistrationForm(DemoQAStudent student) {
		
		var demoQARegistrationFormPage = new DemoQARegistrationFormPage();
		demoQARegistrationFormPage.initLocatorsForNewBrowserContext();
		
		demoQARegistrationFormPage
				.fillFirstName(student.getFirstName())
				.fillLastName(student.getLastName())
				.fillMail(student.getMail())
				.selectGender(student.getGender())
				.fillMobileNumber(student.getMobileNumber())
				.fillDateOfBirth(student.getDateOfBirth())
				.fillSubject(
						student.getSubject())
				.fillHobbies(student.getHobbies())
				.fillCurrentAddress(student.getCurrentAddress())
				.fillState(student.getState())
				.fillCity(student.getCity());
	}
}
