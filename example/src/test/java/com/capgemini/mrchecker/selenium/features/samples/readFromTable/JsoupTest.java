package com.capgemini.mrchecker.selenium.features.samples.readFromTable;

import static org.junit.Assert.assertTrue;

import org.junit.BeforeClass;
import org.junit.Test;

import com.capgemini.mrchecker.selenium.pages.features.samples.readFromTable.EditableGridPage;
import com.capgemini.mrchecker.test.core.BaseTest;
import com.capgemini.mrchecker.test.core.logger.BFLogger;

public class JsoupTest extends BaseTest {
	
	private static EditableGridPage editableGridPage;
	
	@BeforeClass
	public static void setUpBeforeClass() {
		BFLogger.logDebug("OPEN BROWSER AND ENTER HTTP://WWW.EDITABLEGRID.NET/EN/");
		editableGridPage = new EditableGridPage();
		BFLogger.logDebug("VERIFY PAGE IS DISPLAYED");
		assertTrue("Page not opened", editableGridPage.isLoaded());
	}
	
	@Test
	public void Test1_exercise1_printAllCells() {
		BFLogger.logInfo("[TEST1] - Print all cells");
		editableGridPage.printAllCells();
	}
	
	@Override
	public void setUp() {
	}
	
	@Override
	public void tearDown() {
	}
	
}
