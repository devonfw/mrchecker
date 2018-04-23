package com.capgemini.ntc.selenium.pages.projectY;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.capgemini.ntc.selenium.core.BasePage;
import com.capgemini.ntc.selenium.core.newDrivers.elementType.ListElements;
import com.capgemini.ntc.selenium.jsoupHelper.JsoupHelper;
import com.capgemini.ntc.selenium.pages.environment.GetEnvironmentParam;
import com.capgemini.ntc.selenium.pages.environment.PageSubURLsProjectYEnum;
import com.capgemini.ntc.test.core.logger.BFLogger;

public class SortableDataTablesPage extends BasePage {
	
	private static final By	selectorTable	= By.cssSelector("table.tablesorter");
	private static final By	selectorHeader	= By.cssSelector("th");
	
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
	
	public void sortColumnAscending(int columnNumber, int tableNumber) {
		WebElement header = this.getTableHeaders(columnNumber, tableNumber);
		String className = header.getAttribute("class");
		if (className.contains("headerSortUp") || !className.contains("headerSortDown")) {
			header.click();
		}
	}
	
	public void sortColumnDescending(int columnNumber, int tableNumber) {
		WebElement header = this.getTableHeaders(columnNumber, tableNumber);
		String className = header.getAttribute("class");
		if (!className.contains("headerSortUp")) {
			header.click();
			if (!className.contains("headerSortDown")) {
				header.click();
			}
		}
	}
	
	public List<String> getColumnValues(int columnNumber, int tableNumber) {
		WebElement table = getTable(tableNumber);
		return JsoupHelper.findTexts(table, By.cssSelector("tr > td:nth-child(" + (columnNumber + 1) + ")"));
	}
	
	public String readColumnClass(int columnNumber, int tableNumber) {
		return this.getTableHeaders(columnNumber, tableNumber)
				.getAttribute("class");
	}
	
	private WebElement getTable(int tableNumber) {
		return new ListElements(selectorTable).getList()
				.get(tableNumber);
	}
	
	private WebElement getTableHeaders(int columnNumber, int tableNumber) {
		return getTable(tableNumber).findElements(selectorHeader)
				.get(columnNumber);
	}
	
}
