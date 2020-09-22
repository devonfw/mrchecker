package com.capgemini.mrchecker.selenium.mts.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

import com.capgemini.mrchecker.selenium.core.BasePage;
import com.capgemini.mrchecker.test.core.logger.BFLogger;
import com.capgemini.mrchecker.test.core.utils.PageFactory;

public class ThaiMenuPage extends BasePage {
	
	private static final By	orderOptionsSearch		= By.tagName("own-menu-card-details");
	private static final By	addToOrderButtonSearch	= By.tagName("button");
	
	@Override
	public boolean isLoaded() {
		getDriver().waitForPageLoaded();
		
		return getDriver().getCurrentUrl()
				.contains("menu");
	}
	
	@Override
	public void load() {
		BFLogger.logError("MyThaiStar menu page was not loaded.");
	}
	
	@Override
	public String pageTitle() {
		return "";
	}
	
	public ThaiSummaryPage clickFirstMenu() {
		WebElement firstOrderOption = getDriver().findElementDynamic(orderOptionsSearch);
		WebElement addToOrderButton = firstOrderOption.findElement(addToOrderButtonSearch);
		
		JavascriptExecutor js = (JavascriptExecutor) getDriver();
		js.executeScript("arguments[0].click()", addToOrderButton);
		
		return PageFactory.getPageInstance(ThaiSummaryPage.class);
	}
	
}
