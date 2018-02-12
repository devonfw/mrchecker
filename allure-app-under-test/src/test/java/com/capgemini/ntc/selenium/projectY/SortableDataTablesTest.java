package com.capgemini.ntc.selenium.projectY;

import static org.junit.Assert.assertTrue;

import java.util.Random;

import org.junit.Before;
import org.junit.Test;

import com.capgemini.ntc.selenium.core.BasePage;
import com.capgemini.ntc.selenium.pages.projectY.SortableDataTablesPage;
import com.capgemini.ntc.selenium.pages.projectY.TheInternetPage;
import com.capgemini.ntc.test.core.BaseTest;
import com.capgemini.ntc.test.core.logger.BFLogger;

public class SortableDataTablesTest extends BaseTest {
	
	private TheInternetPage					theInternetPage;
	private static SortableDataTablesPage	sortableDataTablesPage;
	
	@Override
	public void setUp() {
		BFLogger.logInfo("Step1 - open Chrome browser");
		BFLogger.logInfo("Step2 - open web page http://the-internet.herokuapp.com/");
		theInternetPage = new TheInternetPage();
		assertTrue("The-internet page is not loaded", theInternetPage.isLoaded());
	}
	
	@Before
	public void clickJavaScriptAlertPage() {
		BFLogger.logInfo("Step 3: Click Sortable data tables link");
		sortableDataTablesPage = theInternetPage.clickSortableDataTablesLink();
		assertTrue("Sortable data tables page is not loaded", sortableDataTablesPage.isLoaded());
	}
	
	@Test // TC1
	public void checkTableOrderedAscendig() {
		int randomTableNumber = new Random().nextInt(2);
		int randomColumnNumber = new Random().nextInt(sortableDataTablesPage.getTableHeaders(randomTableNumber)
						.getSize());
		
		BFLogger.logInfo("Step 4: Sorting column: " + randomColumnNumber + " in table" + randomTableNumber);
		sortableDataTablesPage.sortColumnAscending(randomColumnNumber, randomTableNumber);
		assertTrue("Column class does not contain headerSortDown",
						sortableDataTablesPage.readColumnClass(randomColumnNumber, randomTableNumber)
										.contains("headerSortDown"));
		
		BFLogger.logInfo("Step 5: Checking order of column: " + randomColumnNumber + " in table" + randomTableNumber);
		assertTrue("column: " + randomColumnNumber + " in table" + randomTableNumber + " is not ordered ascending",
						sortableDataTablesPage.isColumnSortedAscending(randomColumnNumber, randomTableNumber));
		
	}
	
	// @Test // TC2
	// public void checkTableOrderedDescending() {
	//
	// }
	
	@Override
	public void tearDown() {
		BFLogger.logInfo("Step6 - navigate back to The-Internet page");
		BasePage.navigateBack();
	}
	
}
