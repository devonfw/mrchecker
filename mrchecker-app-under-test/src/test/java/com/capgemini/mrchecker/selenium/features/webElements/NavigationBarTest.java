package com.capgemini.mrchecker.selenium.features.webElements;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;

import org.junit.AfterClass;
import org.junit.Test;
import org.openqa.selenium.By;

import com.capgemini.mrchecker.selenium.core.BasePage;
import com.capgemini.mrchecker.selenium.core.enums.PageSubURLsEnum;
import com.capgemini.mrchecker.selenium.core.newDrivers.elementType.NavigationBarElement;
import com.capgemini.mrchecker.test.core.BaseTest;

/**
 * Created by LKURZAJ on 07.03.2017.
 */
public class NavigationBarTest extends BaseTest {
	
	private final static By navigationBarSelector = By.cssSelector("ol#breadcrumbs");
	private final static By childsSelector = By.cssSelector("li");
	private NavigationBarElement navigationBarElement;
	
	@AfterClass
	public static void tearDownAll() {
	}
	
	@Test
	public void testGets() {
		assertEquals("Home", this.navigationBarElement.getFirstItemText());
		assertEquals("Tabs", this.navigationBarElement.getActiveItemText());
	}
	
	@Test
	public void testClickByIndex() {
		this.navigationBarElement.clickItemByIndex(1);
		assertEquals("Tabs", this.navigationBarElement.getActiveItemText());
	}
	
	@Test
	public void testClickByText() {
		this.navigationBarElement.clickItemByText("Home");
		assertEquals("Home", this.navigationBarElement.getActiveItemText());
	}
	
	@Test
	public void testClickActiveItem() {
		String url = BasePage.getDriver()
				.getCurrentUrl();
		this.navigationBarElement.clickActiveItem();
		assertEquals(url, BasePage.getDriver()
				.getCurrentUrl());
	}
	
	@Test
	public void testClickFirstItem() {
		this.navigationBarElement.clickFirstItem();
		assertEquals(1, this.navigationBarElement.getDepth());
	}
	
	@Test
	public void testConstructor() {
		NavigationBarElement navBarElem = BasePage.getDriver()
				.elementNavigationBar(NavigationBarTest.navigationBarSelector, NavigationBarTest.childsSelector);
		assertEquals(Arrays.asList("Home", "Tabs"), navBarElem.getItemsTextList());
	}
	
	@Override
	public void setUp() {
		BasePage.getDriver()
				.get(PageSubURLsEnum.WWW_FONT_URL.subURL() + PageSubURLsEnum.TABS.subURL());
		this.navigationBarElement = BasePage.getDriver()
				.elementNavigationBar(NavigationBarTest.navigationBarSelector);
	}
	
	@Override
	public void tearDown() {
		
	}
}
