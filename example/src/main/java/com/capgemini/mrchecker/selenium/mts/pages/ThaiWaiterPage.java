package com.capgemini.mrchecker.selenium.mts.pages;

import org.openqa.selenium.By;

import com.capgemini.mrchecker.test.core.utils.PageFactory;

public class ThaiWaiterPage extends MyThaiStarBasePage {
	
	private static final By	selectorOrdersTabSearch			= By.xpath("//a[@routerlink='/orders']");
	private static final By	selectorReservationsTabSearch	= By.xpath("//a[@routerlink='/reservations']");
	
	@Override
	public boolean isLoaded() {
		getDriver().waitForPageLoaded();
		
		return getDriver().getCurrentUrl()
				.contains("orders");
	}
	
	@Override
	protected By getDisplayableElementSelector() {
		return null;
	}
	
	@Override
	public String pageTitle() {
		return "";
	}
	
	public ThaiOrdersPage switchToOrders() {
		getDriver().findElementDynamic(selectorOrdersTabSearch)
				.click();
		
		return PageFactory.getPageInstance(ThaiOrdersPage.class);
	}
	
	/**
	 * Seek for the reservations tab and click on it
	 *
	 * @return ThaiReservationsPage an object representing the reservations page
	 */
	public ThaiReservationsPage switchToReservations() {
		
		getDriver().findElementDynamic(selectorReservationsTabSearch)
				.click();
		
		return PageFactory.getPageInstance(ThaiReservationsPage.class);
	}
	
}
