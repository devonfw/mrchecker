package com.capgemini.ntc.selenium.pages.projectY;

import java.util.function.Function;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.capgemini.ntc.selenium.core.BasePage;
import com.capgemini.ntc.selenium.core.newDrivers.elementType.Button;
import com.capgemini.ntc.selenium.core.newDrivers.elementType.LabelElement;
import com.capgemini.ntc.selenium.pages.environment.GetEnvironmentParam;
import com.capgemini.ntc.selenium.pages.environment.PageSubURLsProjectYEnum;
import com.capgemini.ntc.test.core.logger.BFLogger;

public class JavaScriptAlertsPage<V, Alert> extends BasePage {
	
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
	
	public void clickAlertButton() {
		new Button(selectorAlertButton).click();
		WebDriverWait wait = new WebDriverWait(getDriver(), 2);
		wait.until((Function<? super WebDriver, Alert>) ExpectedConditions.alertIsPresent());
	}
	
	public void clickConfirmButton() {
		new Button(selectorConfirmButton).click();
		WebDriverWait wait = new WebDriverWait(getDriver(), 2);
		wait.until((Function<? super WebDriver, Alert>) ExpectedConditions.alertIsPresent());
	}
	
	public void clickPromptButton() {
		new Button(selectorPromptButton).click();
		WebDriverWait wait = new WebDriverWait(getDriver(), 2);
		wait.until((Function<? super WebDriver, Alert>) ExpectedConditions.alertIsPresent());
	}
	
	public String readResultLabel() {
		return new LabelElement(resultLabelSelector).getText();
	}
	
	public void clickAlertAccept() {
		getDriver().switchTo()
						.alert()
						.accept();
	}
	
	public void clickAlertDismiss() {
		getDriver().switchTo()
						.alert()
						.dismiss();
	}
	
	public void writeTextInAlert(String text) {
		getDriver().switchTo()
						.alert()
						.sendKeys(text);
	}
	
}
