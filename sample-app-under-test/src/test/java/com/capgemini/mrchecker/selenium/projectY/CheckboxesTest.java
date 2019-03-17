package com.capgemini.mrchecker.selenium.projectY;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.experimental.categories.Category;

import com.capgemini.mrchecker.core.groupTestCases.testSuites.tags.TestsChrome;
import com.capgemini.mrchecker.core.groupTestCases.testSuites.tags.TestsFirefox;
import com.capgemini.mrchecker.core.groupTestCases.testSuites.tags.TestsIE;
import com.capgemini.mrchecker.core.groupTestCases.testSuites.tags.TestsSelenium;
import com.capgemini.mrchecker.selenium.pages.projectY.CheckboxesPage;

@Category({ TestsSelenium.class, TestsChrome.class, TestsFirefox.class, TestsIE.class })
public class CheckboxesTest extends TheInternetBaseTest {
	
	private static CheckboxesPage checkboxesPage;
	
	@Override
	public void setUp() {
		checkboxesPage = shouldTheInternetPageBeOpened().clickCheckboxesLink();
		
		logStep("Verify if Checkboxes page is opened");
		assertTrue("Unable to open Checkboxes page", checkboxesPage.isLoaded());
	}
	
	@Test
	public void shouldCheckboxBeSelectedAfterClick() {
		
		logStep("Verify if first checkbox is not selected");
		assertFalse("The checkbox is selected", checkboxesPage.isCheckboxSelected(0));
		
		logStep("Select first checkbox");
		checkboxesPage.selectCheckbox(0);
		
		logStep("Verify if first checkbox is selected");
		assertTrue("The checkbox is not selected", checkboxesPage.isCheckboxSelected(0));
	}
	
	@Test
	public void shouldCheckboxBeUnselectedAfterClick() {
		
		logStep("Verify if second checkbox is selected");
		assertTrue("The checkbox is not selected", checkboxesPage.isCheckboxSelected(1));
		
		logStep("Select second checkbox");
		checkboxesPage.selectCheckbox(1);
		
		logStep("Verify if second checkbox is not selected");
		assertFalse("The checkbox is selected", checkboxesPage.isCheckboxSelected(1));
	}
	
}
