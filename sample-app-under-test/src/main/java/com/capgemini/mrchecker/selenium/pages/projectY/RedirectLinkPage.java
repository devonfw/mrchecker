package com.capgemini.mrchecker.selenium.pages.projectY;

import org.openqa.selenium.By;

import com.capgemini.mrchecker.selenium.core.BasePage;
import com.capgemini.mrchecker.selenium.core.newDrivers.elementType.Button;
import com.capgemini.mrchecker.selenium.pages.environment.GetEnvironmentParam;
import com.capgemini.mrchecker.selenium.pages.environment.PageSubURLsProjectYEnum;
import com.capgemini.mrchecker.test.core.logger.BFLogger;

public class RedirectLinkPage extends BasePage {
	
	private static final By selectorRedirectHere = By.cssSelector("a#redirect");
	
	@Override
	public boolean isLoaded() {
		getDriver().waitForPageLoaded();
		return getDriver().getCurrentUrl()
						.contains(PageSubURLsProjectYEnum.REDIRECT.getValue());
	}
	
	@Override
	public void load() {
		BFLogger.logDebug("Load 'Redirection' page.");
		getDriver().get(GetEnvironmentParam.THE_INTERNET_MAIN_PAGE.getValue() + PageSubURLsProjectYEnum.REDIRECT.getValue());
		getDriver().waitForPageLoaded();
	}
	
	@Override
	public String pageTitle() {
		return getActualPageTitle();
	}
	
	/**
	 * Clicks 'Redirect here' link.
	 * 
	 * @return StatusCodesHomePage object
	 */
	public StatusCodesHomePage clickRedirectHereLink() {
		new Button(selectorRedirectHere).click();
		return new StatusCodesHomePage();
	}
	
}
