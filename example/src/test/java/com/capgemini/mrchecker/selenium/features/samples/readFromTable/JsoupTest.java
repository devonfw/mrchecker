package com.capgemini.mrchecker.selenium.features.samples.readFromTable;

import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.capgemini.mrchecker.selenium.pages.features.samples.readFromTable.EditableGridPage;
import com.capgemini.mrchecker.test.core.logger.BFLogger;
import com.capgemini.mrchecker.test.core.utils.PageFactory;

public class JsoupTest {
	
	private static EditableGridPage editableGridPage;
	
	@BeforeAll
	public static void setUpBeforeClass() {
		BFLogger.logDebug("OPEN BROWSER AND ENTER HTTP://WWW.EDITABLEGRID.NET/EN/");
		editableGridPage = PageFactory.getPageInstance(EditableGridPage.class);
		BFLogger.logDebug("VERIFY PAGE IS DISPLAYED");
		assertTrue("Page not opened", editableGridPage.isLoaded());
	}
	
	@Test
	public void Test1_exercise1_printAllCells() {
		BFLogger.logInfo("[TEST1] - Print all cells");
		editableGridPage.printAllCells();
	}
}
