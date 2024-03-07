package com.capgemini.demoQA;


import com.capgemini.framework.enums.DemoQAStudent;
import com.capgemini.framework.enums.PrjEpics;
import com.capgemini.framework.logger.AllureStepLogger;
import com.capgemini.framework.playwright.BaseTest;
import com.capgemini.framework.tags.Status;
import com.capgemini.pages.demoqaforms.DemoQARegistrationFormPage;
import com.capgemini.pages.demoqaforms.DemoQARegistrationSubmittingPage;
import com.capgemini.pages.demoqaforms.helper.DemoQARegistrationFormHelper;

import io.qameta.allure.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;


public class DemoQAStudentRegistrationFormTest extends BaseTest {

	
	private DemoQARegistrationFormPage demoQARegistrationFormPage = new DemoQARegistrationFormPage();
	private DemoQARegistrationFormHelper     demoQARegistrationFormHelper     = new DemoQARegistrationFormHelper();
	private DemoQARegistrationSubmittingPage demoQARegistrationSubmittingPage = new DemoQARegistrationSubmittingPage();
	private DemoQAStudent studentData = new DemoQAStudent();
	
	@BeforeEach
	public void beforeEach() {
		demoQARegistrationFormPage.startPage();
	}
	
	@TmsLink("JIRA-ABC")
	@Epic(PrjEpics.DEMO_QA)
	@Feature("GUI")
	@Test
	@Tag(Status.IN_PROGRESS)
	void createNewStudent_test() {

		createStudentByFillingTheFormular(studentData);
		verifyIfStudentCreated(studentData);
		
	}
	@Step("Create student by filling the formular")
	private void createStudentByFillingTheFormular(DemoQAStudent studentData) {
		studentData.logStudent();
		demoQARegistrationFormHelper.fillRegistrationForm(studentData);
		demoQARegistrationFormPage.clickSubmitButton();
	}
	@Step("Verify if student created")
	private void verifyIfStudentCreated(DemoQAStudent studentData) {
		AllureStepLogger.makeScreenshot();
		demoQARegistrationSubmittingPage.verifyPopupIsVisible();
		demoQARegistrationSubmittingPage.verifyPopupHasStudentData(studentData);
	}
	

	
}