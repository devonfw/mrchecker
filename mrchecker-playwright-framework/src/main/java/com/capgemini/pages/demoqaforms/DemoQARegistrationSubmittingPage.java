package com.capgemini.pages.demoqaforms;

import com.capgemini.framework.actions.ActionGui;
import com.capgemini.framework.enums.DemoQAStudent;
import com.capgemini.framework.environment.typesafeconfig.EnvironmentConfig;
import com.capgemini.framework.logger.AllureStepLogger;
import com.microsoft.playwright.Locator;
import io.qameta.allure.Step;

import static com.capgemini.framework.playwright.PlaywrightFactory.getPage;


public class DemoQARegistrationSubmittingPage {
	private static final String  url                  = EnvironmentConfig.getDemoQAStudentRegistrationForm();
	private static final int     PAGE_LOADING_TIMEOUT = 30000;
	private Locator locatorPopup;
	
	public DemoQARegistrationSubmittingPage(){
		initLocatorsForNewBrowserContext();
	}
	public DemoQARegistrationSubmittingPage initLocatorsForNewBrowserContext() {
		 locatorPopup = getPage().getByText("Thanks for submitting the form");
	return this;
	}
	
	public void verifyPopupIsVisible() {
		ActionGui.verifyElementVisible("Submit Form Popup", locatorPopup );
	}
	@Step("Verify Submit form has student data")
	public void verifyPopupHasStudentData(DemoQAStudent studentData) {
		//todo
		AllureStepLogger.info("Not implemented");
	}
}
