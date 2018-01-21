package com.capgemini.ntc.selenium.pages.projectY;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.capgemini.ntc.selenium.core.BasePage;
import com.capgemini.ntc.selenium.core.newDrivers.elementType.Button;
import com.capgemini.ntc.selenium.pages.environment.GetEnvironmentParam;
import com.capgemini.ntc.selenium.pages.environment.PageSubURLsProjectYEnum;
import com.capgemini.ntc.test.core.logger.BFLogger;

public class ChallengingDomPage extends BasePage {
	
	private final By	selectorAllCellsInRows	= By.cssSelector("tbody > tr");
	private By			selectorFirstButton		= By.cssSelector(".large-2.columns > .button:nth-child(1)");
	
	@Override
	public boolean isLoaded() {
		BFLogger.logDebug("The ChallengingDOM page is loaded.");
		return getDriver().getCurrentUrl()
						.equals(GetEnvironmentParam.THE_INTERNET_MAIN_PAGE.getValue() + PageSubURLsProjectYEnum.CHALLENGING_DOM.getValue());
	}
	
	@Override
	public void load() {
		getDriver().get(GetEnvironmentParam.THE_INTERNET_MAIN_PAGE.getValue() + PageSubURLsProjectYEnum.CHALLENGING_DOM.getValue());
		getDriver().waitForPageLoaded();
	}
	
	@Override
	public String pageTitle() {
		return getDriver().getTitle();
	}
	
	public HashMap<String, ArrayList<String>> getTableValues() {
		List<WebElement> rows = getDriver().findElementDynamics(selectorAllCellsInRows);
		HashMap<String, ArrayList<String>> tableValues = new HashMap<String, ArrayList<String>>();
		
		rows.forEach(row -> {
			ArrayList<String> cellsValues = new ArrayList<>();
			List<WebElement> cells = row.findElements(By.cssSelector("td"));
			cells.forEach(cell -> {
				cellsValues.add(cell.getText());
			});
			tableValues.put("row " + rows.indexOf(row), cellsValues);
		});
		
		return tableValues;
	}
	
	public void clickFirstButton() {
		Button firstButton = getDriver().elementButton(selectorFirstButton);
		firstButton.click();
		getDriver().waitForPageLoaded();
	}
}
