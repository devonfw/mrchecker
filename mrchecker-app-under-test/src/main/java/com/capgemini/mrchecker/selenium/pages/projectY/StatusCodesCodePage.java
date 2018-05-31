package com.capgemini.mrchecker.selenium.pages.projectY;

import org.openqa.selenium.By;

import com.capgemini.mrchecker.selenium.core.BasePage;
import com.capgemini.mrchecker.selenium.pages.environment.GetEnvironmentParam;
import com.capgemini.mrchecker.selenium.pages.environment.PageSubURLsProjectYEnum;
import com.capgemini.mrchecker.test.core.logger.BFLogger;

public class StatusCodesCodePage extends BasePage {
	
	private static final By	selectorDisplayedText	= By.cssSelector("#content > div > p");
	private static final By	selectorLinkToCodesPage	= By.cssSelector("#content > div > p > a");
	
	private String codeNumber;
	
	public StatusCodesCodePage(String codeNumber) {
		this.codeNumber = codeNumber;
	}
	
	@Override
	public boolean isLoaded() {
		getDriver().waitForPageLoaded();
		return getDriver().getCurrentUrl()
						.contains(PageSubURLsProjectYEnum.STATUS_CODES.getValue() + '/');
	}
	
	@Override
	public void load() {
		BFLogger.logDebug("Load 'Status Codes' page.");
		getDriver().get(GetEnvironmentParam.THE_INTERNET_MAIN_PAGE.getValue() + PageSubURLsProjectYEnum.STATUS_CODES.getValue() + '/' + codeNumber);
		getDriver().waitForPageLoaded();
	}
	
	@Override
	public String pageTitle() {
		return getActualPageTitle();
	}
	
	public String getCodeNumber() {
		return codeNumber;
	}
	
	public boolean isLoadedWithStatusCode(String codeNumber) {
		return getDriver().getCurrentUrl()
						.equals(GetEnvironmentParam.THE_INTERNET_MAIN_PAGE.getValue() + PageSubURLsProjectYEnum.STATUS_CODES.getValue() + "/" + codeNumber);
	}
	
	public String getDisplayedCodeNumber() {
		String wholeText = getDriver().findElementDynamic(selectorDisplayedText)
						.getText();
		return wholeText.substring(21, 24); // Get from text "This page returned a 200 status code." status code value
	}
	
	public StatusCodesHomePage clickLinkToCodePage() {
		getDriver().findElementDynamic(selectorLinkToCodesPage)
						.click();
		return new StatusCodesHomePage();
	}
	
}