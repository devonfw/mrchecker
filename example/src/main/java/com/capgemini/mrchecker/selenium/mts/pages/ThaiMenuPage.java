package com.capgemini.mrchecker.selenium.mts.pages;

import java.util.Collection;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

import com.capgemini.mrchecker.common.mts.utils.Utils;
import com.capgemini.mrchecker.test.core.utils.PageFactory;

import io.qameta.allure.Step;

public class ThaiMenuPage extends MyThaiStarBasePage {
	
	private static final By	selectorSearchBoxSelector		= By.id("mat-input-1");
	private static final By	selectorApplyFiltersSelector	= By.xpath("//button[@class='mat-focus-indicator apply-filter mat-button mat-button-base mat-accent']");
	private static final By	selectorClearFiltersSelector	= By.xpath("//button[@class='mat-focus-indicator clearFilter mat-button mat-button-base']");
	private static final By	selectorMenuItemsSelector		= By.xpath("//own-menu-card-details");
	
	private static final By	selectorOrderOptionsSearch		= By.tagName("own-menu-card-details");
	private static final By	selectorAddToOrderButtonSearch	= By.tagName("button");
	
	@Override
	protected By getDisplayableElementSelector() {
		return selectorOrderOptionsSearch;
	}
	
	@Override
	public String pageTitle() {
		return "";
	}
	
	@Step("Click first menu")
	public ThaiSummaryPage clickFirstMenu() {
		WebElement firstOrderOption = getDriver().findElementDynamic(selectorOrderOptionsSearch);
		WebElement addToOrderButton = firstOrderOption.findElement(selectorAddToOrderButtonSearch);
		
		JavascriptExecutor js = (JavascriptExecutor) getDriver();
		js.executeScript("arguments[0].click()", addToOrderButton);
		
		return PageFactory.getPageInstance(ThaiSummaryPage.class);
	}
	
	public void typeSearchPhrase(String searchPhrase) {
		Utils.sendKeysWithCheck(searchPhrase, selectorSearchBoxSelector, getDriver(), getWebDriverWait());
	}
	
	public void applyFilters() {
		getDriver().findElementDynamic(selectorApplyFiltersSelector)
				.click();
	}
	
	public void clearFilters() {
		getDriver().findElementDynamic(selectorClearFiltersSelector)
				.click();
	}
	
	public Collection<WebElement> getMenuItems() {
		return getDriver().findElementDynamics(selectorMenuItemsSelector);
	}
	
}
