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
import com.capgemini.mrchecker.selenium.pages.projectY.DynamicContentPage;

@Category({ TestsSelenium.class, TestsChrome.class, TestsFirefox.class, TestsIE.class })
public class DynamicContentTest extends TheInternetBaseTest {
	
	private static DynamicContentPage dynamicContentPage;
	
	@BeforeClass
	public static void setUpBeforeClass() {
		dynamicContentPage = shouldTheInternetPageBeOpened().clickDynamicContentLink();
		
		logStep("Verify if Dynamic Content page is opened");
		assertTrue("Unable to open Dynamic Content page", dynamicContentPage.isLoaded());
	}
	
	@Test
	public void shouldImagesAndDescriptionsDifferAfterRefresh() {
		
		logStep("Read images and descriptions before refresh");
		List<String> descriptionsBeforeRefresh = dynamicContentPage.getDescriptions();
		List<String> imagesBeforeRefresh = dynamicContentPage.getImageLinks();
		
		logStep("Refres page");
		dynamicContentPage.refreshPage();
		assertTrue("The Dynamic Content page hasn't been refreshed", dynamicContentPage.isLoaded());
		
		logStep("Read images and descriptions after refresh");
		List<String> descriptionsAfterRefresh = dynamicContentPage.getDescriptions();
		List<String> imagesAfterRefresh = dynamicContentPage.getImageLinks();
		
		logStep("Verify if descriptions are different after refresh");
		assertEquals("Different number of descriptions before and after refresh",
						descriptionsAfterRefresh.size(), descriptionsBeforeRefresh.size());
		
		boolean diversity = false;
		for (int i = 0; i < descriptionsAfterRefresh.size(); i++) {
			if (!descriptionsAfterRefresh.get(i)
							.equals(descriptionsBeforeRefresh.get(i))) {
				diversity = true;
				break;
			}
		}
		assertTrue("There are no differences between descriptions before and after refresh", diversity);
		
		logStep("Verify if images are different after refresh");
		assertEquals("Different number of descriptions before and after refresh",
						imagesAfterRefresh.size(), imagesBeforeRefresh.size());
		
		diversity = false;
		for (int i = 0; i < imagesAfterRefresh.size(); i++) {
			if (!imagesAfterRefresh.get(i)
							.equals(imagesBeforeRefresh.get(i))) {
				diversity = true;
				break;
			}
		}
		assertTrue("There are no differences between images before and after refresh", diversity);
	}
}
