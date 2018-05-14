package com.capgemini.ntc.selenium.projectY;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.capgemini.ntc.selenium.core.BasePage;
import com.capgemini.ntc.selenium.pages.projectY.BrokenImagePage;
import com.capgemini.ntc.selenium.pages.projectY.TheInternetPage;
import com.capgemini.ntc.test.core.BaseTest;
import com.capgemini.ntc.test.core.logger.BFLogger;

public class BrokenImagesTest extends BaseTest {
	
	private TheInternetPage theInternetPage;
	private final int CorrectHeight = 90;
	private final int CorrectWidth = 120;
	
	@Override
	public void setUp() {
		BFLogger.logInfo("Step1 - open Chrome browser");
		BFLogger.logInfo("Step2 - open web page http://the-internet.herokuapp.com/");
		theInternetPage = new TheInternetPage();
		assertTrue("The-internet page is not loaded", theInternetPage.isLoaded());
	}
	
	@Override
	public void tearDown() {
		BFLogger.logInfo("Step5 - navigate back to The-Internet page");
		BasePage.navigateBack();
	}
	
	@Test
	public void verifyIfImagesSizesAreCorrect() {
		BFLogger.logInfo("Step3 - click Broken Image link and open Broken Image page");
		BrokenImagePage brokenImagePage = theInternetPage.clickBrokenImageLink();
		for (int i = 0; i < 3; i++) {
			BFLogger.logInfo("Step4 - check sizes of image with index: " + i);
			assertTrue("Height of image with index " + i + " is incorrect", brokenImagePage.getImageHeight(i) == CorrectHeight);
			assertTrue("Width of image with index " + i + " is incorrect", brokenImagePage.getImageWidth(i) == CorrectWidth);
		}
	}
	
}
