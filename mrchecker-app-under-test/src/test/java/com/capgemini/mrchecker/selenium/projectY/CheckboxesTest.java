package com.capgemini.mrchecker.selenium.projectY;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.capgemini.mrchecker.selenium.pages.projectY.CheckboxesPage;
import com.capgemini.mrchecker.selenium.pages.projectY.TheInternetPage;
import com.capgemini.mrchecker.test.core.BaseTest;
import com.capgemini.mrchecker.test.core.logger.BFLogger;

public class CheckboxesTest extends BaseTest {
	
	private static TheInternetPage theInternetPage;
	private static CheckboxesPage checkboxesPage;
	
	@BeforeClass
	public static void setUpBeforeClass() {
		BFLogger.logInfo("Step 1: Open the Url http://the-internet.herokuapp.com/");
		theInternetPage = new TheInternetPage();
		BFLogger.logInfo("Step 2: Verify if Url http://the-internet.herokuapp.com/ opens");
		assertTrue("The Internet Page was not open", theInternetPage.isLoaded());
		BFLogger.logInfo("Step 3: Click Checkboxes link");
		checkboxesPage = theInternetPage.clickCheckboxesLink();
		BFLogger.logInfo("Step 4: Verify if Checkboxes Page opens");
		assertTrue("The Checkboxes Page was not open", checkboxesPage.isLoaded());
		BFLogger.logInfo("Step 5: Verify if checkboxes elements are visible");
		assertTrue("Checkboxes were not visible", checkboxesPage.isElementCheckboxesVisible());
		
	}
	
	@AfterClass
	public static void tearDownAfterClass() {
	}
	
	@Before
	public void setUp() {
		
	}
	
	@After
	public void tearDown() {
	}
	
	@Test // TC1
	public void thickCheckboxTest() {
		BFLogger.logInfo("Step 6: Verify if first checkbox is not selected");
		assertFalse("The checkbox is selected", checkboxesPage.isCheckboxSelectedBefore(0));
		BFLogger.logInfo("Step 7: Tick first checkbox");
		checkboxesPage.thickCheckbox(0);
		BFLogger.logInfo("Step 8: Verify if first checkbox is selected");
		assertTrue("The checkbox is not selected", checkboxesPage.isCheckboxSelectedAfter(0));
	}
	
	@Test // TC2
	public void unthickCheckboxTest() {
		BFLogger.logInfo("Step 9: Verify if second checkbox is selected");
		assertTrue("The checkbox is not selected", checkboxesPage.isCheckboxSelectedBefore(1));
		BFLogger.logInfo("Step 10: Tick second checkbox");
		checkboxesPage.unthickCheckbox(1);
		BFLogger.logInfo("Step 11: Verify if second checkbox is not selected");
		assertFalse("The checkbox is selected", checkboxesPage.isCheckboxSelectedAfter(1));
	}
	
}
