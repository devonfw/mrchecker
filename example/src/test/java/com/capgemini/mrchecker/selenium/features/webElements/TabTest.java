package com.capgemini.mrchecker.selenium.features.webElements;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.List;

import org.junit.AfterClass;
import org.junit.Test;
import org.openqa.selenium.By;

import com.capgemini.mrchecker.selenium.core.BasePage;
import com.capgemini.mrchecker.selenium.core.enums.PageSubURLsEnum;
import com.capgemini.mrchecker.selenium.core.newDrivers.elementType.TabElement;
import com.capgemini.mrchecker.test.core.BaseTest;

/**
 * Created by LKURZAJ on 06.03.2017.
 */
public class TabTest extends BaseTest {
	
	private final static By tabSelector = By.cssSelector("ul[role='tablist']");
	private TabElement tabObject;
	private final static List<String> possibleValues = Arrays.asList("Tab 1", "Tab 2", "Tab 3");
	private final static By tabChildsSelector = By.cssSelector("li");
	private final static List<String> listSelectedAttributes = Arrays.asList("ui-tabs-active ui-state-active");
	
	@AfterClass
	public static void tearDownAll() {
	}
	
	@Test
	public void testRadioButtonElementsCount() {
		// check if appropriate number of radio button elements is displayed
		assertEquals(3, tabObject.getItemsCount());
	}
	
	@Test
	public void testPossibleValues() {
		List<String> elementValues = this.tabObject.getTextList();
		for (int i = 0; i < TabTest.possibleValues.size(); i++) {
			assertEquals(TabTest.possibleValues.get(i), elementValues.get(i));
		}
	}
	
	@Test
	public void testSelection() {
		// select and check by index
		tabObject.selectItemByIndex(2);
		assertEquals(tabObject.getSelectedItemIndex(), 2);
		assertTrue(tabObject.isItemSelectedByIndex(2));
		
		// select and check by text
		tabObject.selectItemByText("Tab 1");
		assertEquals(tabObject.getSelectedItemText(), "Tab 1");
	}
	
	@Test
	public void testSelectionSpecifiedItem() {
		tabObject = BasePage.getDriver()
				.elementTab(TabTest.tabSelector, TabTest.tabChildsSelector,
						TabTest.listSelectedAttributes);
		tabObject.selectItemByIndex(1);
		assertEquals(tabObject.getSelectedItemIndex(), 1);
		assertTrue(tabObject.isItemSelectedByIndex(1));
	}
	
	@Override
	public void setUp() {
		BasePage.getDriver()
				.get(PageSubURLsEnum.WWW_FONT_URL.subURL() + PageSubURLsEnum.TABS.subURL());
		this.tabObject = BasePage.getDriver()
				.elementTab(TabTest.tabSelector);
	}
	
	@Override
	public void tearDown() {
	}
}
