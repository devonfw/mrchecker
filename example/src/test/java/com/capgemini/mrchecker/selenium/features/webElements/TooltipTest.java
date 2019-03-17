package com.capgemini.mrchecker.selenium.features.webElements;

import static org.junit.Assert.assertTrue;

import org.junit.AfterClass;
import org.junit.Test;
import org.openqa.selenium.By;

import com.capgemini.mrchecker.selenium.core.BasePage;
import com.capgemini.mrchecker.selenium.core.enums.PageSubURLsEnum;
import com.capgemini.mrchecker.selenium.core.newDrivers.elementType.TooltipElement;
import com.capgemini.mrchecker.test.core.BaseTest;

/**
 * Created by LKURZAJ on 08.03.2017.
 */
public class TooltipTest extends BaseTest {
	
	private final static By tooltipSelector = By.cssSelector("div.ui-tooltip");
	private final static By inputTextSelector = By.cssSelector("input[id='age']");
	TooltipElement tooltipElement;
	
	@AfterClass
	public static void tearDownAll() {
	}
	
	@Test
	public void test() {
		// hover mouse on input text element
		BasePage.getAction()
				.moveToElement(BasePage.getDriver()
						.findElementDynamic(TooltipTest.inputTextSelector))
				.perform();
		this.tooltipElement = BasePage.getDriver()
				.elementTooltip(TooltipTest.tooltipSelector);
		
		// check if tooltip is displayed
		assertTrue(this.tooltipElement.isDisplayed());
		
		// check if tooltip text contains appropriate expression
		assertTrue(this.tooltipElement.isTextContains("We ask for your age"));
	}
	
	@Override
	public void setUp() {
		BasePage.getDriver()
				.get(PageSubURLsEnum.WWW_FONT_URL.subURL() + PageSubURLsEnum.TOOLTIP.subURL());
	}
	
	@Override
	public void tearDown() {
	}
}
