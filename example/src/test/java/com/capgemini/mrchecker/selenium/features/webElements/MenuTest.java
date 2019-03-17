package com.capgemini.mrchecker.selenium.features.webElements;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;

import org.junit.AfterClass;
import org.junit.Test;
import org.openqa.selenium.By;

import com.capgemini.mrchecker.selenium.core.BasePage;
import com.capgemini.mrchecker.selenium.core.enums.PageSubURLsEnum;
import com.capgemini.mrchecker.selenium.core.newDrivers.elementType.MenuElement;
import com.capgemini.mrchecker.test.core.BaseTest;

/**
 * Created by LKURZAJ on 08.03.2017.
 */
public class MenuTest extends BaseTest {
	
	private final static By menuSelector = By.cssSelector("div#tabs-1 ul.top-level");
	private final static By menu2Selector = By.cssSelector("div#tabs-2 ul.top-level");
	private final static By menuChildsSelector = By.cssSelector("li");
	private final static By subMenuSelector = By.cssSelector("ul");
	private final static By subMenuChildsSelector = By.cssSelector("li");
	private final static By subMenuTabSelector = By.cssSelector("li[aria-controls='tabs-2']");
	private MenuElement menuElement;
	private MenuElement menu2Element;
	
	@AfterClass
	public static void tearDownAll() {
	}
	
	@Test
	public void testMenuByIndex() {
		menuElement = BasePage.getDriver()
				.elementMenu(MenuTest.menuSelector);
		menuElement.selectItemByIndex(0);
		assertEquals(this.getCurrentURL() + "#", menuElement.getItemLinkByIndex(0));
	}
	
	@Test
	public void testMenuByText() {
		menuElement = BasePage.getDriver()
				.elementMenu(MenuTest.menuSelector);
		menuElement.selectItemByText("About");
		assertEquals(this.getCurrentURL() + "#", menuElement.getItemLinkByText("About"));
	}
	
	@Test
	public void testMenuItemsCount() {
		menuElement = BasePage.getDriver()
				.elementMenu(MenuTest.menuSelector);
		assertEquals(5, menuElement.getItemsCount());
	}
	
	@Test
	public void testMenuItemsText() {
		menuElement = BasePage.getDriver()
				.elementMenu(MenuTest.menuSelector);
		assertEquals(Arrays.asList("Home", "About", "Contact", "FAQ", "News"), menuElement.getItemsTextList());
	}
	
	@Test
	public void testSelectSubMenuItemByText() {
		BasePage.getDriver()
				.elementButton(MenuTest.subMenuTabSelector)
				.click();
		menu2Element = BasePage.getDriver()
				.elementMenu(MenuTest.menu2Selector, MenuTest.menuChildsSelector,
						MenuTest.subMenuSelector);
		assertEquals(getCurrentURL() + "#", menu2Element.getSubMenuItemLinkByText("FAQ", "Sub Menu Item 3"));
	}
	
	@Test
	public void testSelectSubMenuItemByIndex() {
		BasePage.getDriver()
				.elementButton(MenuTest.subMenuTabSelector)
				.click();
		menu2Element = BasePage.getDriver()
				.elementMenu(MenuTest.menu2Selector, MenuTest.menuChildsSelector);
		menu2Element.selectSubMenuItemByIndex(0, 1);
	}
	
	@Test
	public void testLinkSubMenuItemByIndex() {
		BasePage.getDriver()
				.elementButton(MenuTest.subMenuTabSelector)
				.click();
		menu2Element = BasePage.getDriver()
				.elementMenu(MenuTest.menu2Selector, MenuTest.menuChildsSelector);
		assertEquals(getCurrentURL() + "#", menu2Element.getSubMenuItemLinkByIndex(0, 1));
	}
	
	@Test
	public void testLinkSubMenuItemByText() {
		BasePage.getDriver()
				.elementButton(MenuTest.subMenuTabSelector)
				.click();
		menu2Element = BasePage.getDriver()
				.elementMenu(MenuTest.menu2Selector, MenuTest.menuChildsSelector,
						MenuTest.subMenuSelector, MenuTest.subMenuChildsSelector);
		menu2Element.selectSubMenuItemByText("FAQ", "Sub Menu Item 3");
	}
	
	@Override
	public void setUp() {
		BasePage.getDriver()
				.get(PageSubURLsEnum.WWW_FONT_URL.subURL() + PageSubURLsEnum.MENU.subURL());
	}
	
	@Override
	public void tearDown() {
		
	}
	
	private String getCurrentURL() {
		return PageSubURLsEnum.WWW_FONT_URL.subURL() + PageSubURLsEnum.MENU.subURL();
	}
}
