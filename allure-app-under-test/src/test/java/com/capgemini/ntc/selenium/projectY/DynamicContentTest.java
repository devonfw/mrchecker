package com.capgemini.ntc.selenium.projectY;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;

import com.capgemini.ntc.selenium.core.BasePage;
import com.capgemini.ntc.selenium.pages.projectY.DynamicContentPage;
import com.capgemini.ntc.selenium.pages.projectY.TheInternetPage;
import com.capgemini.ntc.test.core.BaseTest;
import com.capgemini.ntc.test.core.logger.BFLogger;

public class DynamicContentTest extends BaseTest {
	
	private TheInternetPage				theInternetPage;
	private static DynamicContentPage	dynamicContentPage;
	
	@Override
	public void setUp() {
		BFLogger.logInfo("Step1 - open Chrome browser");
		BFLogger.logInfo("Step2 - open web page http://the-internet.herokuapp.com/");
		theInternetPage = new TheInternetPage();
		assertTrue("The-internet page is not loaded", theInternetPage.isLoaded());
	}
	
	@Override
	public void tearDown() {
		BFLogger.logInfo("Step9 - navigate back to The-Internet page");
		BasePage.navigateBack();
	}
	
	@Test
	public void shouldHasDifferentImagesAndDesxriptions() {
		BFLogger.logInfo("Step3 - open 'Dynamic Content' link");
		dynamicContentPage = theInternetPage.clickDynamicContentPage();
		assertTrue("The Dynamic Content page is not loaded", dynamicContentPage.isLoaded());
		
		BFLogger.logInfo("Step4 - Read images and descriptions before refresh");
		List<String> descriptionListPrevious = dynamicContentPage.getDescriptions();
		List<String> imagesListPrevious = dynamicContentPage.getImages();
		
		BFLogger.logInfo("Step5 - Refres page");
		dynamicContentPage.refreshPage();
		assertTrue("The Dynamic Content page is not loaded", dynamicContentPage.isLoaded());
		
		BFLogger.logInfo("Step6 - Read images and descriptions after refresh");
		List<String> descriptionListActual = dynamicContentPage.getDescriptions();
		List<String> imagesListActual = dynamicContentPage.getImages();
		
		BFLogger.logInfo("Step7 - Compare descriptions");
		assertEquals("Number of descriptions is not equal", descriptionListActual.size(), descriptionListPrevious.size());
		
		for (int i = 0; i < descriptionListActual.size(); i++) {
			assertNotEquals("Descriptions should be different",
							descriptionListActual.get(i),
							descriptionListPrevious.get(i));
		}
		
		BFLogger.logInfo("Step8 - Compare images");
		assertEquals("Number of images is not equal", imagesListActual.size(), imagesListPrevious.size());
		
		for (int i = 0; i < imagesListActual.size(); i++) {
			assertNotEquals("Images should be different",
							imagesListPrevious.get(i),
							imagesListActual.get(i));
		}
	}
}
