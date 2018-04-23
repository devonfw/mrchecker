package com.capgemini.ntc.selenium.pages.projectY;

import org.openqa.selenium.By;

import com.capgemini.ntc.selenium.core.BasePage;
import com.capgemini.ntc.selenium.pages.environment.GetEnvironmentParam;
import com.capgemini.ntc.selenium.pages.environment.PageSubURLsProjectYEnum;
import com.capgemini.ntc.test.core.logger.BFLogger;

public class StatusCodesCodePage extends BasePage {
	
	private static final By	selectorDisplayedText	= By.cssSelector("#content > div > p");
	private static final By	selectorLinkToCodesPage	= By.cssSelector("#content > div > p > a");
	
	private String codeNumber;
	
	public StatusCodesCodePage(String codeNumber) {
		this.codeNumber = codeNumber;
	}
	
	@Override
	public boolean isLoaded() {
		return getDriver().getCurrentUrl()
				.equals(GetEnvironmentParam.THE_INTERNET_MAIN_PAGE.getValue() + PageSubURLsProjectYEnum.STATUS_CODES.getValue() + "/");
	}
	
	@Override
	public void load() {
		BFLogger.logError("Unable to load page");
		
	}
	
	@Override
	public String pageTitle() {
		return getDriver().getTitle();
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