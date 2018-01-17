package com.capgemini.ntc.selenium.core.tests.webElements;

import static org.openqa.selenium.By.id;

import org.junit.AfterClass;
import org.junit.Test;
import org.openqa.selenium.By;

import com.capgemini.ntc.selenium.core.BasePage;
import com.capgemini.ntc.selenium.core.enums.PageSubURLsEnum;
import com.capgemini.ntc.test.core.BaseTest;

/**
 * Created by PRZWOJTKOW on 17.01.2018.
 */
public class MouseClickTest extends BaseTest {
	
	private static final By seletorHotSpotArea = id("hot-spot");
	
	@Test
	public void testShouldRightClickOnElement() {
		BasePage.getDriver()
						.mouseRightClick(seletorHotSpotArea);
	}
	
	@Override
	public void setUp() {
		BasePage.getDriver()
						.get(PageSubURLsEnum.HEROKUAPP.subURL() + PageSubURLsEnum.CONTEXT_MENU.subURL());
		
	}
	
	@Override
	public void tearDown() {
		// TASK Auto-generated method stub
		
	}
	
	@AfterClass
	public static void tearDownAll() {
		BasePage.getDriver()
						.close();
	}
	
}
