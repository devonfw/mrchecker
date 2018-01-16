package com.capgemini.ntc.selenium.pages.features.samples.theinternet;

import java.util.ArrayList;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.capgemini.ntc.selenium.core.BasePage;
import com.capgemini.ntc.selenium.jsoupHelper.JsoupHelper;
import com.capgemini.ntc.test.core.logger.BFLogger;

public class ChallengingDomPage extends BasePage {
	
	private final static By		tableArea				= By.cssSelector("div.large-10.columns > table");
	private final By			selectorAllCellsInRows	= By.cssSelector("tbody > tr");
	private By					selectorButtonToClick	= By.cssSelector("#content > div > div > div >div:nth-child(1) > a:nth-child(1)");
	private ArrayList<String>	valuesInTableBeforeClick;
	
	@Override
	public boolean isLoaded() {
		return getDriver().getCurrentUrl()
						.contains("challenging_dom");
	}
	
	@Override
	public void load() {
		BFLogger.logDebug("load()");
	}
	
	@Override
	public String pageTitle() {
		return "The Internet";
	}
	
	public void saveCellsValuesBeforeClick() {
		BFLogger.logDebug("saveCellsValuesBeforeClick()");
		valuesInTableBeforeClick = (ArrayList<String>) JsoupHelper.findTexts(getDriver().findElementDynamic(tableArea), selectorAllCellsInRows);
		BFLogger.logDebug("Cells Values saved");
		BFLogger.logDebug(valuesInTableBeforeClick.get(0));
	}
	
	public void clickFirstButton() {
		BFLogger.logDebug("clickFirstButton()");
		WebElement elementToClick = getDriver().findElementDynamic(selectorButtonToClick);
		elementToClick.click();
	}
	
	public boolean compareValuesInCells() {
		for (int i = 0; i < valuesInTableBeforeClick.size(); i++) {
			By selectorOfSingleRow = By.cssSelector("tbody > tr" + ":nth-child(" + (i + 1) + ")");
			String textInRow = getDriver().findElementDynamic(selectorOfSingleRow)
							.getText();
			BFLogger.logDebug("Row" + i + " from table:   " + textInRow);
			BFLogger.logDebug("Row" + i + " before click: " + valuesInTableBeforeClick.get(i));
			if (!(textInRow.equals(valuesInTableBeforeClick.get(i)))) {
				BFLogger.logDebug("Values in row " + (i) + " are changed");
				return false;
			}
		}
		BFLogger.logDebug("All values in table are correct after click");
		return true;
	}
}
