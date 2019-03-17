package com.capgemini.mrchecker.selenium.features.webElements;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.AfterClass;
import org.junit.Test;
import org.openqa.selenium.By;

import com.capgemini.mrchecker.selenium.core.BasePage;
import com.capgemini.mrchecker.selenium.core.enums.PageSubURLsEnum;
import com.capgemini.mrchecker.selenium.core.newDrivers.elementType.DropdownListElement;
import com.capgemini.mrchecker.test.core.BaseTest;

/**
 * Created by LKURZAJ on 06.03.2017.
 */
public class DropdownListTest extends BaseTest {
	
	private static final By dropdownSelector = By.cssSelector("select#dropdown_7");
	private DropdownListElement dropdownObject;
	
	@AfterClass
	public static void tearDownAll() {
	}
	
	@Test
	public void testPossibleOptionsNumber() {
		assertTrue(dropdownObject.isDisplayed());
		assertEquals(dropdownObject.getAmountOfPossibleValues(), 204);
	}
	
	@Test
	public void testSelectOptionByIndex() {
		dropdownObject.selectDropdownByIndex(0);
		assertTrue(dropdownObject.isDropdownElementSelectedByIndex(0));
	}
	
	@Test
	public void testSelectOptionByValue() {
		dropdownObject.selectDropdownByValue("Vietnam");
		assertTrue(dropdownObject.isDropdownElementSelectedByValue("Vietnam"));
	}
	
	@Test
	public void testSelectOptionByText() {
		dropdownObject.selectDropdownByVisibleText("Vietnam");
		assertEquals(dropdownObject.getFirstSelectedOptionText(), "Vietnam");
	}
	
	@Test
	public void testAllSelectedOptions() {
		dropdownObject.selectDropdownByIndex(5);
		assertEquals(dropdownObject.getAllSelectedOptionsText()
				.size(), 1);
	}
	
	@Override
	public void setUp() {
		BasePage.getDriver()
				.get(PageSubURLsEnum.WWW_FONT_URL.subURL() + PageSubURLsEnum.REGISTRATION.subURL());
		this.dropdownObject = BasePage.getDriver()
				.elementDropdownList(DropdownListTest.dropdownSelector);
	}
	
	@Override
	public void tearDown() {
		
	}
}
