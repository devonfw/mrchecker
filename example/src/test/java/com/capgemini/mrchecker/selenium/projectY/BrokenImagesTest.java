package com.capgemini.mrchecker.selenium.projectY;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.capgemini.mrchecker.core.groupTestCases.testSuites.tags.TestsChrome;
import com.capgemini.mrchecker.core.groupTestCases.testSuites.tags.TestsFirefox;
import com.capgemini.mrchecker.core.groupTestCases.testSuites.tags.TestsIE;
import com.capgemini.mrchecker.core.groupTestCases.testSuites.tags.TestsSelenium;
import com.capgemini.mrchecker.selenium.pages.projectY.BrokenImagePage;

@TestsSelenium
@TestsChrome
@TestsFirefox
@TestsIE
public class BrokenImagesTest extends TheInternetBaseTest {
	
	private static BrokenImagePage brokenImagePage;
	
	private final int	expectedHeight	= 90;
	private final int	expectedWidth	= 120;
	
	@BeforeAll
	public static void setUpBeforeClass() {
		brokenImagePage = shouldTheInternetPageBeOpened().clickBrokenImageLink();
		
		logStep("Verify if Broken Image page is opened");
		assertTrue("Unable to open Broken Image page", brokenImagePage.isLoaded());
	}
	
	@Test
	public void shouldImageSizesBeEqualToExpected() {
		
		for (int i = 0; i < 3; i++) {
			logStep("Verify size of image with index: " + i);
			assertEquals("Height of image with index: " + i + " is incorrect", expectedHeight, brokenImagePage.getImageHeight(i));
			assertEquals("Width of image with index: " + i + " is incorrect", expectedWidth, brokenImagePage.getImageWidth(i));
		}
	}
	
}
