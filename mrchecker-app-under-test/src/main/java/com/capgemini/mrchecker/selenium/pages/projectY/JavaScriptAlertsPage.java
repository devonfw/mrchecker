package com.capgemini.mrchecker.selenium.pages.projectY;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.capgemini.mrchecker.selenium.core.BasePage;
import com.capgemini.mrchecker.selenium.core.newDrivers.elementType.Button;
import com.capgemini.mrchecker.selenium.core.newDrivers.elementType.LabelElement;
import com.capgemini.mrchecker.selenium.pages.environment.GetEnvironmentParam;
import com.capgemini.mrchecker.selenium.pages.environment.PageSubURLsProjectYEnum;
import com.capgemini.mrchecker.test.core.logger.BFLogger;

public class JavaScriptAlertsPage extends BasePage {
	
	private static final By	selectorAlertButton		= By.cssSelector("button[onclick*=jsAlert]");
	private static final By	selectorConfirmButton	= By.cssSelector("button[onclick*=jsConfirm]");
	private static final By	selectorPromptButton	= By.cssSelector("button[onclick*=jsPrompt]");
	private static final By	resultLabelSelector		= By.cssSelector("p#result");
	
	@Override
	public boolean isLoaded() {
		getDriver().waitForPageLoaded();
		return getDriver().getCurrentUrl()
						.contains(PageSubURLsProjectYEnum.JAVASCRIPT_ALERTS.getValue());
	}
	
	@Override
	public void load() {
		BFLogger.logDebug("Load 'JavaScript Alerts' page.");
		getDriver().get(GetEnvironmentParam.THE_INTERNET_MAIN_PAGE.getValue() + PageSubURLsProjectYEnum.JAVASCRIPT_ALERTS.getValue());
		getDriver().waitForPageLoaded();
	}
	
	@Override
	public String pageTitle() {
		return getActualPageTitle();
	}
	
	/**
	 * Clicks 'JS alert' button.
	 */
	public void clickAlertButton() {
		new Button(selectorAlertButton).click();
		WebDriverWait wait = new WebDriverWait(getDriver(), 2);
		wait.until(ExpectedConditions.alertIsPresent());
	}
	
	/**
	 * Clicks 'JS confirm' button.
	 */
	public void clickConfirmButton() {
		new Button(selectorConfirmButton).click();
		WebDriverWait wait = new WebDriverWait(getDriver(), 2);
		wait.until(ExpectedConditions.alertIsPresent());
	}
	
	/**
	 * Clicks 'JS prompt' button.
	 */
	public void clickPromptButton() {
		new Button(selectorPromptButton).click();
		WebDriverWait wait = new WebDriverWait(getDriver(), 2);
		wait.until(ExpectedConditions.alertIsPresent());
	}
	
	/**
	 * Returns message displayed by popup.
	 * 
	 * @return String object representing message displayed by popup
	 */
	public String readResultLabel() {
		return new LabelElement(resultLabelSelector).getText();
	}
	
	/**
	 * Clicks alert's 'OK' button.
	 */
	public void clickAlertAccept() {
		getDriver().switchTo()
						.alert()
						.accept();
	}
	
	/**
	 * Clicks alert's 'Cancel' button.
	 */
	public void clickAlertDismiss() {
		getDriver().switchTo()
						.alert()
						.dismiss();
	}
	
	/**
	 * Types text into alert's text field.
	 * 
	 * @param text
	 *            String object sent into alert's text field
	 */
	public void writeTextInAlert(String text) {
		getDriver().switchTo()
						.alert()
						.sendKeys(text);
	}
	
}
