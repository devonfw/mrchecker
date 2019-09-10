package com.capgemini.mrchecker.selenium.tests.myThaiStar;

import static org.junit.Assert.assertTrue;

import java.awt.AWTException;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import com.capgemini.mrchecker.selenium.pages.myThaiStar.MenuPage;
import com.capgemini.mrchecker.test.core.BaseTest;

@Category(Tests1.class)
public class MenuFiltersTest extends BaseTest {
	
	private static MenuPage menuPage = new MenuPage();
	
	@BeforeClass
	public static void setUpBeforeClass() {
	}
	
	@AfterClass
	public static void tearDownAfterClass() {
		
	}
	
	@Override
	public void setUp() {
		menuPage.load();
		
	}
	
	@Override
	public void tearDown() {
		
	}
	
	@Test
	public void Test_Search() {
		menuPage.enterSearchInput("pierogi");
		menuPage.clickApplyFiltersButton();
		
		assertTrue("Test failed: Results incorrect", !menuPage.findElementInContainer("pHRP/2Q==\");"));
	}
	
	@Test
	public void Test_Sort() {
		menuPage.selectSortDropdown(0);
		menuPage.clickApplyFiltersButton();
		
		assertTrue("Test failed: Results incorrect", menuPage.findElementInContainer("VDcgn2f/Z\");"));
	}
	
	@Test
	public void Test_Checkboxes() {
		menuPage.setRiceCheckbox();
		menuPage.clickApplyFiltersButton();
		
		assertTrue("Test failed: Results incorrect", menuPage.findElementInContainer("Mzz6n//2Q==\");"));
	}
	
	@Test
	public void Test_Sliders() throws AWTException {
		menuPage.slide(5);
		menuPage.clickApplyFiltersButton();
		
		assertTrue("Test failed: Results incorrect", menuPage.findElementInContainer("5XggQv/9k=\");"));
	}
}
