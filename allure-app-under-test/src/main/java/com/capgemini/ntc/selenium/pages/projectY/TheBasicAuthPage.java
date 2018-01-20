package com.capgemini.ntc.selenium.pages.projectY;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;

import org.openqa.selenium.By;

import com.capgemini.ntc.selenium.core.BasePage;
import com.capgemini.ntc.selenium.pages.environment.PageSubURLsProjectYEnum;
import com.capgemini.ntc.test.core.logger.BFLogger;

public class TheBasicAuthPage extends BasePage {
	
	private static final By selectorTextMessage = By.cssSelector("#content > div > p");
	
	public TheBasicAuthPage() {
		this(false, "", "");
	}
	
	public TheBasicAuthPage(boolean openByUrl, String login, String password) {
		if (openByUrl) {
			this.enterLoginAndPasswordByUrl(login, password);
		}
	}
	
	@Override
	public boolean isLoaded() {
		BFLogger.logDebug("is loaded: " + getDriver().getCurrentUrl());
		return true;
	}
	
	@Override
	public void load() {
		BFLogger.logDebug("load");
	}
	
	@Override
	public String pageTitle() {
		return getDriver().getTitle();
	}
	
	private void enterLoginAndPasswordByUrl(String login, String password) {
		getDriver().get("http://" + login + ":" + password + "@" + "the-internet.herokuapp.com/" + PageSubURLsProjectYEnum.BASIC_AUTH.getValue());
	}
	
	public void enterLoginAndPassword(String login, String password) throws AWTException, InterruptedException {
		Robot rb = new Robot();
		
		Thread.sleep(2000);
		
		StringSelection username = new StringSelection(login);
		Toolkit.getDefaultToolkit()
						.getSystemClipboard()
						.setContents(username, null);
		rb.keyPress(KeyEvent.VK_CONTROL);
		rb.keyPress(KeyEvent.VK_V);
		rb.keyRelease(KeyEvent.VK_V);
		rb.keyRelease(KeyEvent.VK_CONTROL);
		
		rb.keyPress(KeyEvent.VK_TAB);
		rb.keyRelease(KeyEvent.VK_TAB);
		Thread.sleep(2000);
		
		StringSelection pwd = new StringSelection(password);
		Toolkit.getDefaultToolkit()
						.getSystemClipboard()
						.setContents(pwd, null);
		rb.keyPress(KeyEvent.VK_CONTROL);
		rb.keyPress(KeyEvent.VK_V);
		rb.keyRelease(KeyEvent.VK_V);
		rb.keyRelease(KeyEvent.VK_CONTROL);
		
		rb.keyPress(KeyEvent.VK_ENTER);
		rb.keyRelease(KeyEvent.VK_ENTER);
		Thread.sleep(2000);
	}
	
	public String getMessageValue() {
		return getDriver().findElementDynamic(selectorTextMessage)
						.getText();
	}
}
