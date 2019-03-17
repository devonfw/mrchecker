package com.capgemini.mrchecker.selenium.projectY;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import com.capgemini.mrchecker.core.groupTestCases.testSuites.tags.TestsChrome;
import com.capgemini.mrchecker.core.groupTestCases.testSuites.tags.TestsFirefox;
import com.capgemini.mrchecker.core.groupTestCases.testSuites.tags.TestsIE;
import com.capgemini.mrchecker.core.groupTestCases.testSuites.tags.TestsSelenium;
import com.capgemini.mrchecker.selenium.pages.projectY.ChallengingDomPage;

@Category({ TestsSelenium.class, TestsChrome.class, TestsFirefox.class, TestsIE.class })
public class ChallengingDomTest extends TheInternetBaseTest {
	
	private static ChallengingDomPage challengingDomPage;
	
	@BeforeClass
	public static void setUpBeforeClass() {
		challengingDomPage = shouldTheInternetPageBeOpened().clickChallengingDomLink();
		
		logStep("Verify if Challenging Dom page is opened");
		assertTrue("Unable to open Challenging Dom page", challengingDomPage.isLoaded());
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
