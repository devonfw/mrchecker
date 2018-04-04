package com.capgemini.ntc.selenium.pages.projectY;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.capgemini.ntc.selenium.core.BasePage;
import com.capgemini.ntc.selenium.pages.environment.GetEnvironmentParam;
import com.capgemini.ntc.selenium.pages.environment.PageSubURLsProjectYEnum;
import com.capgemini.ntc.test.core.logger.BFLogger;

public class MultipleWindowsPage extends BasePage {
	
	private final static By selectorLink = By.cssSelector("#content > div > a");
	
	@Override
	public boolean isLoaded() {
		getDriver().waitForPageLoaded();
		return getDriver().getCurrentUrl()
						.contains(PageSubURLsProjectYEnum.WINDOW.getValue());
	}
	
	@Override
	public void load() {
		BFLogger.logDebug("Load 'Opening a new window' page.");
		getDriver().get(GetEnvironmentParam.THE_INTERNET_MAIN_PAGE.getValue() + PageSubURLsProjectYEnum.WINDOW.getValue());
		getDriver().waitForPageLoaded();
	}
	
	@Override
	public String pageTitle() {
		return getActualPageTitle();
	}
	
	public NewWindowPage clickHereLink() {
		WebElement elementLink = getDriver().findElementDynamic(selectorLink);
		elementLink.click();
		getDriver().waitForPageLoaded();
		return new NewWindowPage();
	}
}
