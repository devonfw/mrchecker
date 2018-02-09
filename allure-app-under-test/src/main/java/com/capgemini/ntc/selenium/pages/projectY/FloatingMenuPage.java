package com.capgemini.ntc.selenium.pages.projectY;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

import com.capgemini.ntc.selenium.core.BasePage;

public class FloatingMenuPage extends BasePage {
	
	private final static String	URL					= "http://the-internet.herokuapp.com/floating_menu";
	private final static By		menuDivLocator		= By.id("menu");
	private final static By		homeLinkLocator		= By.partialLinkText("Home");
	private final static By		newsLinkLocator		= By.partialLinkText("News");
	private final static By		contactLinkLocator	= By.partialLinkText("Contact");
	private final static By		aboutLinkLocator	= By.partialLinkText("About");
	
	private static WebElement	menuDiv;
	private static WebElement	homeLink;
	private static WebElement	newsLink;
	private static WebElement	contactLink;
	private static WebElement	aboutLink;
	
	@Override
	public boolean isLoaded() {
		
		return false;
	}
	
	@Override
	public void load() {
		BasePage.getDriver()
				.get(URL);
		
		menuDiv = getDriver()
				.findElementQuietly(menuDivLocator);
		
		homeLink = getDriver().findElementQuietly(homeLinkLocator);
		newsLink = getDriver().findElementQuietly(newsLinkLocator);
		contactLink = getDriver().findElementQuietly(contactLinkLocator);
		aboutLink = getDriver().findElementQuietly(aboutLinkLocator);
	}
	
	public void scrollPageDown(int scrollValue) {
		JavascriptExecutor jse = (JavascriptExecutor) FloatingMenuPage.getDriver();
		jse.executeScript("window.scrollBy(0, " + scrollValue + ")", "");
	}
	
	public void scrollPageUp(int scrollValue) {
		JavascriptExecutor jse = (JavascriptExecutor) FloatingMenuPage.getDriver();
		jse.executeScript("window.scrollBy(0, -" + scrollValue + ")", "");
	}
	
	public void clickHomeLink() {
		homeLink.click();
	}
	
	public void clickNewsLink() {
		newsLink.click();
	}
	
	public void clickContactLink() {
		contactLink.click();
	}
	
	public void clickAboutLink() {
		aboutLink.click();
	}
	
	public boolean isMenuDisplayed() {
		return menuDiv.isDisplayed()
				&& homeLink.isDisplayed()
				&& newsLink.isDisplayed()
				&& contactLink.isDisplayed()
				&& aboutLink.isDisplayed();
	}
	
	public int getPageHeight() {
		return getDriver().manage()
				.window()
				.getSize()
				.getHeight();
	}
	
	@Override
	public String pageTitle() {
		// TASK Auto-generated method stub
		return null;
	}
	
}
