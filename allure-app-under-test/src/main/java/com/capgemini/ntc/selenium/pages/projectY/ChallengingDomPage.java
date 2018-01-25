package com.capgemini.ntc.selenium.pages.projectY;

import java.util.List;

import org.openqa.selenium.By;

import com.capgemini.ntc.selenium.core.BasePage;
import com.capgemini.ntc.selenium.core.newDrivers.elementType.Button;
import com.capgemini.ntc.selenium.jsoupHelper.JsoupHelper;
import com.capgemini.ntc.selenium.pages.environment.GetEnvironmentParam;
import com.capgemini.ntc.selenium.pages.environment.PageSubURLsProjectYEnum;
import com.capgemini.ntc.test.core.logger.BFLogger;

public class ChallengingDomPage extends BasePage {
	
	private final By	selectorTableRows	= By.cssSelector(".large-10 > table > tbody > tr");
	private final By	selectorFirstButton	= By.cssSelector(".large-2.columns > .button:nth-child(1)");
	
	@Override
	public boolean isLoaded() {
		BFLogger.logDebug("The ChallengingDOM page is loaded.");
		return getDriver().getCurrentUrl()
						.equals(GetEnvironmentParam.THE_INTERNET_MAIN_PAGE.getValue() + PageSubURLsProjectYEnum.CHALLENGING_DOM.getValue());
	}
	
	@Override
	public void load() {
		getDriver().get(GetEnvironmentParam.THE_INTERNET_MAIN_PAGE.getValue() + PageSubURLsProjectYEnum.CHALLENGING_DOM.getValue());
		getDriver().waitForPageLoaded();
	}
	
	@Override
	public String pageTitle() {
		return getDriver().getTitle();
	}
	
	public List<String> getTableValues() {
		return JsoupHelper.findTexts(selectorTableRows);
	}
	
	public void clickFirstButton() {
		Button firstButton = getDriver().elementButton(selectorFirstButton);
		firstButton.click();
		getDriver().waitForPageLoaded();
	}
}
