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
public class ButtonTest extends BaseTest {

	private static By buttonSubmit = By.cssSelector("button#submit");

	@AfterAll
	public static void tearDownAll() {
	}

	@Test
	public void test() {
		// check if element is displayed
		assertTrue(BasePage.getDriver()
				.elementButton(ButtonTest.buttonSubmit)
				.isDisplayed());

		// check if element type equals Button
		assertEquals("Button", BasePage.getDriver()
				.elementButton(ButtonTest.buttonSubmit)
				.getElementTypeName());

		// click on button and verify if url was changed
		BasePage.getDriver()
				.elementButton(ButtonTest.buttonSubmit)
				.click();
		assertTrue(BasePage.getDriver()
				.getCurrentUrl()
				.contains("&submit="));
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
