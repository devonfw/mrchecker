package com.capgemini.mrchecker.selenium.pages.projectY;

import org.openqa.selenium.By;

import com.capgemini.mrchecker.selenium.core.BasePage;
import com.capgemini.mrchecker.selenium.pages.environment.GetEnvironmentParam;
import com.capgemini.mrchecker.selenium.pages.environment.PageSubURLsProjectYEnum;
import com.capgemini.mrchecker.test.core.logger.BFLogger;

public class StatusCodesHomePage extends BasePage {
	
	private static final By	selectorLink200Code	= By.linkText("200");
	private static final By	selectorLink301Code	= By.linkText("301");
	private static final By	selectorLink404Code	= By.linkText("404");
	private static final By	selectorLink500Code	= By.linkText("500");
	
	@Override
	public boolean isLoaded() {
		getDriver().waitForPageLoaded();
		return getDriver().getCurrentUrl()
						.contains(PageSubURLsProjectYEnum.STATUS_CODES.getValue());
	}
	
	@Override
	public void load() {
		BFLogger.logDebug("Load 'Status Codes' page.");
		getDriver().get(GetEnvironmentParam.THE_INTERNET_MAIN_PAGE.getValue() + PageSubURLsProjectYEnum.STATUS_CODES.getValue());
		getDriver().waitForPageLoaded();
	}
	
	@Override
	public String pageTitle() {
		return getActualPageTitle();
	}
	
	public boolean isLinkCodeDisplayed(By selector) {
		return getDriver().findElementDynamic(selector)
						.isDisplayed();
		
	}
	
	public StatusCodesCodePage clickCode200Link() {
		return clickCodeLink(selectorLink200Code);
	}
	
	public StatusCodesCodePage clickCode301Link() {
		return clickCodeLink(selectorLink301Code);
	}
	
	public StatusCodesCodePage clickCode404Link() {
		return clickCodeLink(selectorLink404Code);
	}
	
	public StatusCodesCodePage clickCode500Link() {
		return clickCodeLink(selectorLink500Code);
	}
	
	private StatusCodesCodePage clickCodeLink(By selector) {
		String codeNumber = getCodeNumberToCheck(selector);
		getDriver().findElementDynamic(selector)
						.click();
		return new StatusCodesCodePage(codeNumber);
	}
	
	private String getCodeNumberToCheck(By selector) {
		return getDriver().findElementDynamic(selector)
						.getText();
	}
	
}