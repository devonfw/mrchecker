package com.capgemini.mrchecker.selenium.projectY;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;

import com.capgemini.mrchecker.selenium.pages.projectY.ChallengingDomPage;

public class ChallengingDomTest extends TheInternetBaseTest<ChallengingDomPage> {
	
	private static ChallengingDomPage challengingDomPage;
	
	@BeforeClass
	public static void setUpBeforeClass() {
		challengingDomPage = new ChallengingDomPage();
		shouldTheInternetSubpageBeOpened(challengingDomPage);
	}
	
	@Test
	public void shouldValuesInTableCellsStayUnchangedAfterClick() {
		
		logStep("Get table values (before click any button)");
		List<String> tableValuesBeforeClick = challengingDomPage.getTableValues();
		
		logStep("Click first button");
		challengingDomPage.clickFirstButton();
		
		logStep("Get table values (after click first button)");
		List<String> tableValuesAfterClick = challengingDomPage.getTableValues();
		
		logStep("Verify equality of table values before and after click");
		assertEquals("Values from table cells were changed after click", tableValuesBeforeClick, tableValuesAfterClick);
	}
	
}
