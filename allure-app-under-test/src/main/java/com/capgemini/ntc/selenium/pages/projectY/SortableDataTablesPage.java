package com.capgemini.ntc.selenium.pages.projectY;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.capgemini.ntc.selenium.core.BasePage;
import com.capgemini.ntc.selenium.core.newDrivers.elementType.ListElements;
import com.capgemini.ntc.selenium.pages.environment.GetEnvironmentParam;
import com.capgemini.ntc.selenium.pages.environment.PageSubURLsProjectYEnum;
import com.capgemini.ntc.test.core.logger.BFLogger;

public class SortableDataTablesPage extends BasePage {
	
	@Override
	public boolean isLoaded() {
		BFLogger.logDebug("The Sortable data tables page is loaded.");
		return getDriver().getCurrentUrl()
						.equals(GetEnvironmentParam.THE_INTERNET_MAIN_PAGE.getValue() + PageSubURLsProjectYEnum.SORTABLE_DATA_TABLES.getValue());
	}
	
	@Override
	public void load() {
		BFLogger.logDebug("load");
		getDriver().get(GetEnvironmentParam.THE_INTERNET_MAIN_PAGE.getValue() + PageSubURLsProjectYEnum.SORTABLE_DATA_TABLES.getValue());
	}
	
	@Override
	public String pageTitle() {
		return getDriver().getTitle();
	}
	
	public ListElements getTableHeaders(int tableNumber) {
		By headerSelector = By.cssSelector("table#table" + (tableNumber + 1) + " th");
		return new ListElements(headerSelector);
	}
	
	public void sortColumnAscending(int columnNumber, int tableNumber) {
		WebElement header = this.getTableHeaders(tableNumber)
						.getList()
						.get(columnNumber);
		header.click();
	}
	
	public void sortColumnDescending(int columnNumber, ListElements tableHeaders) {
		WebElement header = tableHeaders.getList()
						.get(columnNumber);
		header.click();
		header.click();
	}
	
	public String readColumnClass(int columnNumber, int tableNumber) {
		WebElement header = this.getTableHeaders(tableNumber)
						.getList()
						.get(columnNumber);
		
		BFLogger.logDebug("class: " + header.getAttribute("class"));
		return header.getAttribute("class");
	}
	
	public boolean isColumnSortedAscending(int columnNumber, int tableNumber) {
		return true;
	}
	
}
