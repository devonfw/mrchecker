package com.capgemini.ntc.selenium.tests.samples.readFromTable;

import java.util.List;

import org.junit.Test;
import org.openqa.selenium.By;

import com.capgemini.ntc.selenium.core.BasePage;
import com.capgemini.ntc.test.core.BaseTest;
import com.capgemini.ntc.test.core.jsoupHelper.JsoupHelper;
import com.capgemini.ntc.test.core.logger.BFLogger;

public class JsoupTableSearch extends BaseTest {
	
	private final By searchArea = By.cssSelector("#tableid");
	private final By firstcolumForAllRows = By.cssSelector("tr  > td");
	private long timePassed;
	
	@Override
	public void setUp() {
		BasePage.getDriver().get("http://editablegrid.net/en/");
		BasePage.getDriver().waitForPageLoaded();
	}

	@Override
	public void tearDown() {
	}
	
	@Test
	public void firstTest(){
		startTimeMeasure();
		List<String> valuesNewMethod = JsoupHelper.findTexts(BasePage.getDriver().findElement(searchArea),firstcolumForAllRows);
		
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
