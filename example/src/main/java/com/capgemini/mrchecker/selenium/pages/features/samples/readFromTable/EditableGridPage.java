package com.capgemini.mrchecker.selenium.pages.features.samples.readFromTable;

import java.util.List;

import org.openqa.selenium.By;

import com.capgemini.mrchecker.selenium.core.BasePage;
import com.capgemini.mrchecker.selenium.jsoupHelper.JsoupHelper;
import com.capgemini.mrchecker.test.core.logger.BFLogger;

public class EditableGridPage extends BasePage {
	
	private final static By	searchArea				= By.cssSelector("#tableid");
	private final By		selectorAllCellsInRows	= By.cssSelector("tr > td");
	
	@Override
	public boolean isLoaded() {
		return pageTitle().equals("EditableGrid, build powerful editable tables - What's EditableGrid ?");
	}
	
	@Override
	public void load() {
		getDriver().get("http://www.editablegrid.net/en/");
		getDriver().waitForPageLoaded();
		
	}
	
	@Override
	public String pageTitle() {
		return getDriver().getTitle();
	}
	
	public void printAllCells() {
		@SuppressWarnings("deprecation")
		List<String> cellsValues = JsoupHelper.findTexts(getDriver().findElement(searchArea), selectorAllCellsInRows);
		for (String cellValue : cellsValues) {
			BFLogger.logInfo(cellValue);
		}
	}
}
