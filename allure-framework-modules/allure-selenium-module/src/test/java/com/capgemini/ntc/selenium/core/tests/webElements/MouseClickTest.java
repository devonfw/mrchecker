package com.capgemini.ntc.selenium.core.tests.webElements;

import static org.junit.Assert.assertTrue;
import static org.openqa.selenium.By.id;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;

import org.junit.AfterClass;
import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.capgemini.ntc.selenium.core.BasePage;
import com.capgemini.ntc.selenium.core.enums.PageSubURLsEnum;
import com.capgemini.ntc.test.core.BaseTest;
import com.capgemini.ntc.test.core.logger.BFLogger;

/**
 * Created by PRZWOJTKOW on 17.01.2018.
 */
public class MouseClickTest extends BaseTest {
	
	private static final By seletorHotSpotArea = id("hot-spot");
	
	@Test
	public void testShouldRightClickOnElement() {
		// given
		String expectedAlertText = "You selected a context menu";
		
		// when
		BasePage.getDriver()
						.mouseRightClick(seletorHotSpotArea);
		chooseTheInternetOptionFromContextMenu();
		
		// then
		assertTrue("Alert text is not valid", isAlertTextValid(expectedAlertText));
		
	}
	
	public void chooseTheInternetOptionFromContextMenu() {
		Robot robot;
		try {
			robot = new Robot();
			robot.keyPress(KeyEvent.VK_DOWN);
			robot.keyRelease(KeyEvent.VK_DOWN);
			robot.keyPress(KeyEvent.VK_DOWN);
			robot.keyRelease(KeyEvent.VK_DOWN);
			robot.keyPress(KeyEvent.VK_DOWN);
			robot.keyRelease(KeyEvent.VK_DOWN);
			robot.keyPress(KeyEvent.VK_DOWN);
			robot.keyRelease(KeyEvent.VK_DOWN);
			robot.keyPress(KeyEvent.VK_DOWN);
			robot.keyRelease(KeyEvent.VK_DOWN);
			robot.keyPress(KeyEvent.VK_ENTER);
			robot.keyRelease(KeyEvent.VK_ENTER);
		} catch (AWTException e) {
			BFLogger.logError("Unable to connect with remote keyboard");
			e.printStackTrace();
		}
	}
	
	public boolean isAlertTextValid(String expectedAlertText) {
		WebDriverWait wait = new WebDriverWait(BasePage.getDriver(), 5);
		wait.until(ExpectedConditions.alertIsPresent());
		
		Alert alert = BasePage.getDriver()
						.switchTo()
						.alert();
		
		return alert.getText()
						.equals(expectedAlertText) ? true : false;
	}
	
	@Override
	public void setUp() {
		BasePage.getDriver()
						.get(PageSubURLsEnum.HEROKUAPP.subURL() + PageSubURLsEnum.CONTEXT_MENU.subURL());
		
		BasePage.getDriver()
						.manage()
						.window()
						.maximize();
	}
	
	@Override
	public void tearDown() {
		BasePage.getDriver()
						.switchTo()
						.alert()
						.accept();
	}
	
	@AfterClass
	public static void tearDownAll() {
		BasePage.getDriver()
						.close();
	}
	
}
