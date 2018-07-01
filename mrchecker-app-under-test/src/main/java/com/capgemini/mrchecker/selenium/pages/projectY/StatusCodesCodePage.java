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
	
	/**
	 * Verifies if page is loaded with given code number.
	 * 
	 * @param codeNumber
	 *            Expected code number
	 * @return true if expected code number is loaded with web page
	 */
	public boolean isLoadedWithStatusCode(String codeNumber) {
		return getDriver().getCurrentUrl()
						.equals(GetEnvironmentParam.THE_INTERNET_MAIN_PAGE.getValue() + PageSubURLsProjectYEnum.STATUS_CODES.getValue() + "/" + codeNumber);
	}
	
	/**
	 * Returns displayed code number.
	 * <p>
	 * Code number is retrieved from following text displayed on the page:<b>
	 * 'This page returned a *** status code.', where *** are representing code number to be returned.
	 * </p>
	 * 
	 * @return String object representing displayed code number retrieved from specific sentence.
	 */
	public String getDisplayedCodeNumber() {
		return getDriver().findElementDynamic(selectorDisplayedText)
						.getText()
						.substring(21, 24);
	}
	
	/**
	 * Clicks link to return to 'Code Page'.
	 * 
	 * @return StatusCodesHomePage object
	 */
	public StatusCodesHomePage clickLinkToCodePage() {
		getDriver().findElementDynamic(selectorLinkToCodesPage)
						.click();
		return new StatusCodesHomePage();
	}
	
}