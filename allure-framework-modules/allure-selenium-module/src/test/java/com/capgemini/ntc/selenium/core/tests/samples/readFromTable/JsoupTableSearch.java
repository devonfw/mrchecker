package com.capgemini.ntc.selenium.core.tests.samples.readFromTable;

import java.util.List;

import org.junit.Ignore;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.capgemini.ntc.test.core.jsoupHelper.JsoupHelper;
import com.capgemini.ntc.test.core.logger.BFLogger;
import com.capgemini.ntc.test.core.tests.core.BaseTest;

public class JsoupTableSearch extends BaseTest {
	
	private final By searchArea = By.cssSelector("#tableid");
	private final By firstcolumForAllRows = By.cssSelector("tr  > td");
	private long timePassed;
	
	@Override
	public void setUp() {
		getDriver().get("http://editablegrid.net/en/");
		getDriver().waitForPageLoaded();
	}

	@Override
	public void tearDown() {
	}
	
	@Test
	public void firstTest(){
		startTimeMeasure();
		List<String> valuesNewMethod = JsoupHelper.findTexts(getDriver().findElement(searchArea),firstcolumForAllRows);
		
		for(String test : valuesNewMethod){
			BFLogger.logInfo(test);
		}
		stopTimeMeasure();
		
	}
	
	

	private void startTimeMeasure() {
		timePassed = System.currentTimeMillis();
	}

	private void stopTimeMeasure() {
		BFLogger.logInfo("Fetching duration: " + (System.currentTimeMillis() - timePassed));
	}
	
	
}
