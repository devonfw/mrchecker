package com.capgemini.mrchecker.selenium.mts.pages;

import java.util.Collection;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

import com.capgemini.mrchecker.common.mts.utils.Utils;
import com.capgemini.mrchecker.test.core.utils.PageFactory;

import io.qameta.allure.Step;

public class ThaiMenuPage extends MyThaiStarBasePage {
	
	private static final By	searchBoxSelector		= By.id("mat-input-1");
	private static final By	applyFiltersSelector	= By.xpath("//button[@class='mat-focus-indicator apply-filter mat-button mat-button-base mat-accent']");
	private static final By	clearFiltersSelector	= By.xpath("//button[@class='mat-focus-indicator clearFilter mat-button mat-button-base']");
	private static final By	menuItemsSelector		= By.xpath("//own-menu-card-details");
	
	private static final By	orderOptionsSearch		= By.tagName("own-menu-card-details");
	private static final By	addToOrderButtonSearch	= By.tagName("button");
	
	@Override
	protected By getDisplayableElementSelector() {
		return orderOptionsSearch;
	}
	
	@Override
	public String pageTitle() {
		return "";
	}
	
	@Step("Click first menu")
	public ThaiSummaryPage clickFirstMenu() {
		WebElement firstOrderOption = getDriver().findElementDynamic(orderOptionsSearch);
		WebElement addToOrderButton = firstOrderOption.findElement(addToOrderButtonSearch);
		
		JavascriptExecutor js = (JavascriptExecutor) getDriver();
		js.executeScript("arguments[0].click()", addToOrderButton);
		
		return PageFactory.getPageInstance(ThaiSummaryPage.class);
	}
	
	public void typeSearchPhrase(String searchPhrase) {
		Utils.sendKeysWithCheck(searchPhrase, searchBoxSelector, getDriver(), getWebDriverWait());
	}
	
	public void applyFilters() {
		getDriver().findElementDynamic(applyFiltersSelector)
				.click();
	}
	
	public void clearFilters() {
		getDriver().findElementDynamic(clearFiltersSelector)
				.click();
	}
	
	public Collection<WebElement> getMenuItems() {
		return getDriver().findElementDynamics(menuItemsSelector);
	}
	
}
