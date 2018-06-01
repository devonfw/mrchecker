package com.capgemini.mrchecker.selenium.projectY;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.capgemini.mrchecker.selenium.core.BasePage;
import com.capgemini.mrchecker.selenium.pages.projectY.DropdownPage;
import com.capgemini.mrchecker.selenium.pages.projectY.TheInternetPage;
import com.capgemini.mrchecker.test.core.BaseTest;
import com.capgemini.mrchecker.test.core.logger.BFLogger;

public class DropdownTest extends BaseTest {
	
	private TheInternetPage theInternetPage;
	private DropdownPage dropdownPage;
	private static final String correctValueOneOnDropdownList = "Option 1";
	private static final String correctValueTwoOnDropdownList = "Option 2";
	
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
	public void shouldDisplayCorrectTextOnDropdownWhenChooseValueOne() {
		BFLogger.logInfo("Step3 - open 'dropdown' link");
		dropdownPage = theInternetPage.clickDropdownLink();
		dropdownPage.setValueOnDropdownList(1);
		String textOnChoosedValueOfDropdown = dropdownPage.getTextFromDropdownList();
		assertTrue("Choosed value displays incorrect text", textOnChoosedValueOfDropdown.equals(correctValueOneOnDropdownList));
	}
	
	@Test
	public void shouldDisplayCorrectTextOnDropdownWhenChooseValueTwo() {
		BFLogger.logInfo("Step3 - open 'dropdown' link");
		dropdownPage = theInternetPage.clickDropdownLink();
		dropdownPage.setValueOnDropdownList(2);
		String textOnChoosedValueOfDropdown = dropdownPage.getTextFromDropdownList();
		assertTrue("Choosed value displays incorrect text", textOnChoosedValueOfDropdown.equals(correctValueTwoOnDropdownList));
	}
}
