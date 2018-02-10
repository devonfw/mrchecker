package com.capgemini.ntc.selenium.pages.projectY;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.capgemini.ntc.selenium.core.BasePage;
import com.capgemini.ntc.test.core.logger.BFLogger;

public class FloatingMenuPage extends BasePage {
	
	private final static String	URL					= "http://the-internet.herokuapp.com/floating_menu";
	private final static By		menuDivLocator		= By.id("menu");
	private final static By		homeLinkLocator		= By.partialLinkText("Home");
	private final static By		newsLinkLocator		= By.partialLinkText("News");
	private final static By		contactLinkLocator	= By.partialLinkText("Contact");
	private final static By		aboutLinkLocator	= By.partialLinkText("About");
	private final static By		paragraphLocator	= By.xpath("//div[@class='scroll large-10 columns large-centered']/p");
	private final static By		githubLinkLocator	= By.xpath("//div[@class='row'][2]/a/img");
	
	private static WebElement		menuDiv;
	private static WebElement		homeLink;
	private static WebElement		newsLink;
	private static WebElement		contactLink;
	private static WebElement		aboutLink;
	private static List<WebElement>	paragraphs;
	private static WebElement		githubLink;
	
	@Override
	public void load() {
		getDriver().get(URL);
		BFLogger.logInfo("" + isLoaded());
		
		menuDiv = getDriver().findElementQuietly(menuDivLocator);
		homeLink = getDriver().findElementQuietly(homeLinkLocator);
		newsLink = getDriver().findElementQuietly(newsLinkLocator);
		contactLink = getDriver().findElementQuietly(contactLinkLocator);
		aboutLink = getDriver().findElementQuietly(aboutLinkLocator);
		paragraphs = getDriver().findElements(paragraphLocator);
		githubLink = getDriver().findElementQuietly(githubLinkLocator);
		BFLogger.logInfo("" + isLoaded());
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
	
	public void clickGithubLink() {
		githubLink.click();
		
		WebDriverWait waitForGithub = new WebDriverWait(getDriver(), 25);
		waitForGithub.until(webDriver -> ((JavascriptExecutor) webDriver).executeScript("return document.readyState")
				.equals("complete"));
	}
	
	public boolean isMenuDisplayed() {
		return menuDiv.isDisplayed()
				&& homeLink.isDisplayed()
				&& newsLink.isDisplayed()
				&& contactLink.isDisplayed()
				&& aboutLink.isDisplayed();
	}
	
	public boolean isPageTextDisplayed() {
		boolean displayed = !paragraphs.isEmpty();
		
		for (WebElement para : paragraphs) {
			displayed = displayed && para.isDisplayed();
		}
		
		return displayed;
	}
	
	public int getParagraphsCount() {
		return paragraphs.size();
	}
	
	public int getPageHeight() {
		return getDriver().manage()
				.window()
				.getSize()
				.getHeight();
	}
	
	@Override
	public String pageTitle() {
		return getDriver().getTitle();
	}
	
	@Override
	public boolean isLoaded() {
		boolean loadCompleted = ((JavascriptExecutor) getDriver()).executeScript("return document.readyState")
				.equals("complete");
		boolean addressOk = getDriver().getCurrentUrl()
				.equals(URL);
		
		return loadCompleted && addressOk;
	}
	
}
