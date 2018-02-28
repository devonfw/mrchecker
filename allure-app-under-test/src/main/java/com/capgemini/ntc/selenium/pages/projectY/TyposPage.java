package com.capgemini.ntc.selenium.pages.projectY;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

import com.capgemini.ntc.selenium.core.BasePage;
import com.capgemini.ntc.selenium.pages.environment.GetEnvironmentParam;
import com.capgemini.ntc.selenium.pages.environment.PageSubURLsProjectYEnum;
import com.capgemini.ntc.test.core.logger.BFLogger;

public class TyposPage extends BasePage {
	
	private final static String	URL				= GetEnvironmentParam.THE_INTERNET_MAIN_PAGE.getValue()
			+ PageSubURLsProjectYEnum.TYPOS.getValue();
	private final static By		headerLocator	= By.xpath("//div[@class='example']/h3");
	private final static By		linesLocator	= By.xpath("//div[@class='example']/p");
	
	private static WebElement		header;
	private static List<WebElement>	lines;
	
	@Override
	public void load() {
		getDriver().get(URL);
		BFLogger.logInfo("Page " + URL + " is loaded = " + isLoaded());
		
		findElements();
	}
	
	public void findElements() {
		header = getDriver().findElementQuietly(headerLocator); // Element [By.xpath://*[contains(text(),'LEFT')]]
		lines = getDriver().findElementDynamics(linesLocator);
	}
	
	public String getHeaderText() {
		return header.getText();
	}
	
	public String getTextLine(int index) {
		return (index < lines.size()) ? lines.get(index)
				.getText() : "";
	}
	
	public void reload() {
		((JavascriptExecutor) getDriver()).executeScript("window.location.reload()");
		getDriver().waitForPageLoaded();
	}
	
	@Override
	public boolean isLoaded() {
		boolean loadCompleted = ((JavascriptExecutor) getDriver()).executeScript("return document.readyState")
				.equals("complete");
		
		return loadCompleted && isUrlAndPageTitleAsCurrentPage(URL);
	}
	
	@Override
	public String pageTitle() {
		return getDriver().getTitle();
	}
	
}
