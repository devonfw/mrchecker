package com.capgemini.mrchecker.selenium.projectY;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import com.capgemini.mrchecker.core.groupTestCases.testSuites.tags.TestsChrome;
import com.capgemini.mrchecker.core.groupTestCases.testSuites.tags.TestsFirefox;
import com.capgemini.mrchecker.core.groupTestCases.testSuites.tags.TestsIE;
import com.capgemini.mrchecker.core.groupTestCases.testSuites.tags.TestsSelenium;
import com.capgemini.mrchecker.selenium.pages.projectY.DropdownPage;

@Category({ TestsSelenium.class, TestsChrome.class, TestsFirefox.class, TestsIE.class })
public class DropdownTest extends TheInternetBaseTest {
	
	private static DropdownPage dropdownPage;
	
	private static final String	expectedFirstOptionValue	= "Option 1";
	private static final String	expectedSecondOptionValue	= "Option 2";
	
	@BeforeClass
	public static void setUpBeforeClass() {
		dropdownPage = shouldTheInternetPageBeOpened().clickDropdownLink();
		
		logStep("Verify if Dropdown page is opened");
		assertTrue("Unable to open Dropdown page", dropdownPage.isLoaded());
	}
	
	@Test
	public void shouldGetExpectedDropdownTextOptionAfterSelection() {
		
		logStep("Select first drodown option");
		dropdownPage.selectDropdownValueByIndex(1);
		
		logStep("Verify if selected option text is equal to expected one");
		assertEquals("Selected value is different than expected", expectedFirstOptionValue, dropdownPage.getSelectedDropdownValue());
		
		logStep("Select first drodown option");
		dropdownPage.selectDropdownValueByIndex(2);
		
		logStep("Verify if selected option text is equal to expected one");
		assertEquals("Selected value is different than expected", expectedSecondOptionValue, dropdownPage.getSelectedDropdownValue());
	}
	
}
