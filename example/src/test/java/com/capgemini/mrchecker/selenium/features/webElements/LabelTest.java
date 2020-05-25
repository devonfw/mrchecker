package com.capgemini.mrchecker.selenium.features.webElements;

import com.capgemini.mrchecker.selenium.core.BasePage;
import com.capgemini.mrchecker.selenium.core.enums.PageSubURLsEnum;
import com.capgemini.mrchecker.test.core.BaseTest;
import org.junit.AfterClass;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;

/**
 * Created by LKURZAJ on 03.03.2017.
 */
@Disabled("page not existent on the Web; no MrChecker Test")
public class LabelTest extends BaseTest {
	private static By text1Label = By.cssSelector("span.bcd");

	@AfterAll
	public static void tearDownAll() {
	}

	@Test
	public void test() {
		// check if label is displayed
		assertTrue(BasePage.getDriver()
				.elementLabel(LabelTest.text1Label)
				.isDisplayed());

		// check if label has properly text
		assertEquals("Text1", BasePage.getDriver()
				.elementLabel(LabelTest.text1Label)
				.getText());

		// check if label has properly class field
		assertEquals("bcd", BasePage.getDriver()
				.elementLabel(LabelTest.text1Label)
				.getClassName());
	}

	@Override
	public void setUp() {
		BasePage.getDriver()
				.get(PageSubURLsEnum.TOOLS_QA.subURL() + PageSubURLsEnum.AUTOMATION_PRACTICE_FORM.subURL());
		return;
	}

	@Override
	public void tearDown() {
		// TODO Auto-generated method stub
	}
}
