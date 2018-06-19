package com.capgemini.mrchecker.selenium.projectY;

import static org.junit.Assert.assertEquals;

import java.util.Random;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import com.capgemini.mrchecker.core.groupTestCases.testSuites.tags.TestsChrome;
import com.capgemini.mrchecker.core.groupTestCases.testSuites.tags.TestsFirefox;
import com.capgemini.mrchecker.core.groupTestCases.testSuites.tags.TestsIE;
import com.capgemini.mrchecker.core.groupTestCases.testSuites.tags.TestsSelenium;
import com.capgemini.mrchecker.selenium.pages.projectY.HoversPage;

@Category({ TestsSelenium.class, TestsChrome.class, TestsFirefox.class, TestsIE.class })
public class HoversTest extends TheInternetBaseTest<HoversPage> {
	
	private static HoversPage	hoversPage;
	private final String		names[]	= { "name: user1", "name: user2", "name: user3" };
	
	@BeforeClass
	public static void setUpBeforeClass() {
		hoversPage = new HoversPage();
		shouldTheInternetSubpageBeOpened(hoversPage);
	}
	
	@Test
	public void shouldProperInformationBeDisplayedWhenMousePointerHoveredOverRandomElement() {
		logStep("Hover mouse pointer over random element");
		int randomIndex = new Random().nextInt(names.length);
		hoversPage.hoverOverAvatar(randomIndex);
		assertEquals("Picture's information is different than expected", names[randomIndex],
						hoversPage.getAvatarsInformation(randomIndex));
	}
	
}
