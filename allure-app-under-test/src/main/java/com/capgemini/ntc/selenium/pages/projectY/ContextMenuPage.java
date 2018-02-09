package com.capgemini.ntc.selenium.pages.projectY;

import static org.openqa.selenium.By.id;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.capgemini.ntc.selenium.core.BasePage;
import com.capgemini.ntc.selenium.core.base.environment.GetEnvironmentParam;
import com.capgemini.ntc.selenium.pages.environment.PageSubURLsProjectYEnum;
import com.capgemini.ntc.test.core.logger.BFLogger;

public class ContextMenuPage extends BasePage {
	
	private static final String	validPageTitle		= "The Internet";
	private static final By		seletorHotSpotArea	= id("hot-spot");
	private static final String	expectedAlertText	= "You selected a context menu";
	private int					timeoutInSec		= 5;
	
	@Override
	public boolean isLoaded() {
		return getDriver().getTitle()
						.equals(validPageTitle);
	}
	
	@Override
	public void load() {
		getDriver().get(GetEnvironmentParam.THE_INTERNET_MAIN_PAGE.getValue() + PageSubURLsProjectYEnum.CONTEXT_MENU.getValue());
		getDriver().waitForPageLoaded();
	}
	
	@Override
	public String pageTitle() {
		return pageTitle();
	}
	
	public void rightClickOnHotSpotArea() {
		getDriver().mouseRightClick(seletorHotSpotArea);
	}
	
	public void clickOnAgreeAtAlert() {
		getDriver()
						.switchTo()
						.alert()
						.accept();
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
	
	public boolean isAlertTextValid() {
		WebDriverWait wait = new WebDriverWait(BasePage.getDriver(), timeoutInSec);
		wait.until(ExpectedConditions.alertIsPresent());
		
		Alert alert = BasePage.getDriver()
						.switchTo()
						.alert();
		
		return alert.getText()
				.equals(expectedAlertText);
	}
	
}
