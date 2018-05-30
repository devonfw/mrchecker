package com.capgemini.mrchecker.selenium.projectY;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import org.junit.Before;
import org.junit.Test;

import com.capgemini.mrchecker.selenium.core.BasePage;
import com.capgemini.mrchecker.selenium.pages.projectY.SortableDataTablesPage;
import com.capgemini.mrchecker.selenium.pages.projectY.TheInternetPage;
import com.capgemini.mrchecker.test.core.BaseTest;
import com.capgemini.mrchecker.test.core.logger.BFLogger;

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
	public void clickSortableDataTablesPage() {
		BFLogger.logInfo("Step 3: Click Sortable data tables link");
		sortableDataTablesPage = theInternetPage.clickSortableDataTablesLink();
		assertTrue("Sortable data tables page is not loaded", sortableDataTablesPage.isLoaded());
	}
	
	@Test // TC1
	public void checkLastNameColumnOrderedAscendig() {
		int columnNumber = 0;
		int tableNumber = new Random().nextInt(2);
		
		BFLogger.logInfo("Step 4: Sorting column: Last Name");
		sortableDataTablesPage.sortColumnAscending(columnNumber, tableNumber);
		assertTrue("Header Last Name was not clicked",
				sortableDataTablesPage.readColumnClass(columnNumber, tableNumber)
						.contains("headerSortDown"));
		
		BFLogger.logInfo("Step 5: Checking order of column: Last Name");
		List<String> columnValues = sortableDataTablesPage.getColumnValues(columnNumber, tableNumber);
		List<String> expectedList = new ArrayList<String>(columnValues);
		Collections.sort(expectedList);
		BFLogger.logInfo("Expected list: " + expectedList + " Actual list: " + columnValues);
		assertTrue("Column Last Name column is not ordered ascending",
				columnValues.equals(expectedList));
	}
	
	@Test // TC2
	public void checkFirstNameColumnOrderedDescending() {
		int columnNumber = 1;
		int tableNumber = new Random().nextInt(2);
		
		BFLogger.logInfo("Step 4: Sorting column: First Name");
		sortableDataTablesPage.sortColumnDescending(columnNumber, tableNumber);
		assertTrue("Header First Name was not clicked",
				sortableDataTablesPage.readColumnClass(columnNumber, tableNumber)
						.contains("headerSortUp"));
		
		BFLogger.logInfo("Step 5: Checking order of column: First Name");
		List<String> columnValues = sortableDataTablesPage.getColumnValues(columnNumber, tableNumber);
		List<String> expectedList = new ArrayList<String>(columnValues);
		Collections.sort(expectedList);
		Collections.reverse(expectedList);
		BFLogger.logInfo("Expected list: " + expectedList + " Actual list: " + columnValues);
		assertTrue("Column First Name column is not ordered descending",
				columnValues.equals(expectedList));
	}
	
	@Override
	public void tearDown() {
		BFLogger.logInfo("Step6 - navigate back to The-Internet page");
		BasePage.navigateBack();
	}
	
}
