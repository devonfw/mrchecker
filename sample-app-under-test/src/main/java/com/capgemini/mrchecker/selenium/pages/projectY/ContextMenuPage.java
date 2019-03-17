package com.capgemini.mrchecker.selenium.pages.projectY;

import static org.openqa.selenium.By.id;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.capgemini.mrchecker.selenium.core.BasePage;
import com.capgemini.mrchecker.selenium.core.base.environment.GetEnvironmentParam;
import com.capgemini.mrchecker.selenium.pages.environment.PageSubURLsProjectYEnum;
import com.capgemini.mrchecker.test.core.logger.BFLogger;

public class ContextMenuPage extends BasePage {
	
	private static final By hotSpotAreaseletor = id("hot-spot");
	
	@Override
	public boolean isLoaded() {
		getDriver().waitForPageLoaded();
		return getDriver().getCurrentUrl()
						.contains(PageSubURLsProjectYEnum.CONTEXT_MENU.getValue());
	}
	
	@Override
	public void load() {
		BFLogger.logDebug("Load 'Context Menu' page.");
		getDriver().get(GetEnvironmentParam.THE_INTERNET_MAIN_PAGE.getValue() + PageSubURLsProjectYEnum.CONTEXT_MENU.getValue());
		getDriver().waitForPageLoaded();
	}
	
	@Override
	public String pageTitle() {
		return getActualPageTitle();
	}
	
	/**
	 * Performs right mouse click over the hot spot area.
	 */
	public void rightClickHotSpotArea() {
		getDriver().mouseRightClick(hotSpotAreaseletor);
	}
	
	/**
	 * Confirms alert to close it.
	 */
	public void clickAlertsOkButton() {
		getDriver().switchTo()
						.alert()
						.accept();
	}
	
	/**
	 * Chooses 'the-internet' option from context menu using <b>Robot</b> class.
	 * 
	 * @see java.awt.Robot
	 */
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
	
	/**
	 * Verifies if text displayed by alert is equal to expected one.
	 * 
	 * @return true if displayed and expected texts are equal
	 */
	public boolean isAlertTextValid(String expectedAlertText) {
		int timeout = 5;
		
		WebDriverWait wait = new WebDriverWait(getDriver(), timeout);
		wait.until(ExpectedConditions.alertIsPresent());
		
		Alert alert = getDriver().switchTo()
						.alert();
		
		return alert.getText()
						.equals(expectedAlertText);
	}
	
}
