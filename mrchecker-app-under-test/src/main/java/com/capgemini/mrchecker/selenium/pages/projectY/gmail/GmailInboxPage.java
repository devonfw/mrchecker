package com.capgemini.mrchecker.selenium.pages.projectY.gmail;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.pagefactory.ByChained;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.capgemini.mrchecker.selenium.core.BasePage;
import com.capgemini.mrchecker.selenium.core.exceptions.BFElementNotFoundException;
import com.capgemini.mrchecker.selenium.jsoupHelper.JsoupHelper;
import com.capgemini.mrchecker.test.core.logger.BFLogger;

public class GmailInboxPage extends BasePage {
	
	private static final By	selectorSelectAllCheckbox	= By.cssSelector("div.J-J5-Ji.J-JN-M-I-Jm > span");
	private static final By	selectorDeleteButton		= By.cssSelector("div.ar9.T-I-J3.J-J5-Ji");
	private static final By	selectorEmailTable			= By.cssSelector("div.UI tbody");
	private static final By	selectorEmailRow			= By.cssSelector("tr.zA.zE");
	private static final By	selectorEmailCell			= By.cssSelector("td");
	
	private static final String unread = "unread";
	
	private List<WebElement> unreadEmails = new ArrayList<>();
	
	@Override
	public boolean isLoaded() {
		getDriver().waitForPageLoaded();
		return getDriver().getCurrentUrl()
						.equals("https://mail.google.com/mail/u/0/#inbox")
						&& pageTitle().contains("Inbox");
	}
	
	@Override
	public void load() {
		getDriver().get("https://mail.google.com/mail/u/0/#inbox");
	}
	
	@Override
	public String pageTitle() {
		return getDriver().getTitle();
	}
	
	/**
	 * Deletes all items from inbox.
	 * <p>
	 * If no email exists in inbox, then no action is performed.
	 * </p>
	 */
	public void deleteAllEmails() {
		if (getNumberOfEmails() > 0) {
			clickSelectAllCheckox();
			clickDeleteButton();
		}
	}
	
	private void clickSelectAllCheckox() {
		getDriver().elementButton(selectorSelectAllCheckbox)
						.click();
	}
	
	private void clickDeleteButton() {
		getDriver().elementButton(selectorDeleteButton)
						.click();
	}
	
	/**
	 * Returns the number of existing emails in inbox.
	 * 
	 * @return The number of emails.
	 */
	public int getNumberOfEmails() {
		return getEmailRows().size();
	}
	
	private List<WebElement> getEmailRows() {
		List<WebElement> emails = new ArrayList<>();
		try {
			emails = getDriver().findElementDynamics(new ByChained(selectorEmailTable, selectorEmailRow));
		} catch (BFElementNotFoundException e) {
			BFLogger.logDebug("No emails found in inbox: " + e.getMessage());
		}
		return emails;
	}
	
	/**
	 * Waits for given time in seconds until web element representing an email in inbox shows up.
	 * 
	 * @param waitTime
	 *            Given time in seconds.
	 */
	public void waitUntilEmailShowUp(int waitTime) {
		WebDriverWait wait = new WebDriverWait(getDriver(), waitTime);
		wait.until((Function<? super WebDriver, WebElement>) ExpectedConditions.visibilityOf(getDriver().findElementDynamic(selectorEmailRow)));
	}
	
	/**
	 * Verifies if any of elements being a part of email rows contain message: unread.
	 * 
	 * @return True if message: unread has been found.
	 */
	public boolean isAnyEmailUnread() {
		unreadEmails = new ArrayList<>();
		List<WebElement> rows = getEmailRows();
		for (WebElement row : rows) {
			List<String> matches = getAllTextFromMessage(row).stream()
							.filter(element -> element.contains(unread))
							.collect(Collectors.toList());
			if (matches.size() > 0)
				unreadEmails.add(row);
		}
		return !unreadEmails.isEmpty();
	}
	
	/**
	 * Verifies if unread emails became from given sender.
	 * 
	 * @param sender
	 *            Given sender.
	 * @return A list of emails from given sender.
	 */
	public List<WebElement> findEmailsForSender(String sender) {
		List<WebElement> sendersEmails = new ArrayList<>();
		for (WebElement row : unreadEmails) {
			List<String> matches = getAllTextFromMessage(row).stream()
							.filter(element -> element.contains(sender))
							.collect(Collectors.toList());
			if (matches.size() > 0)
				sendersEmails.add(row);
		}
		return sendersEmails;
	}
	
	public List<WebElement> findEmailsForTitle(List<WebElement> emails, String title) {
		List<WebElement> resultEmails = new ArrayList<>();
		for (WebElement row : emails) {
			List<String> matches = getAllTextFromMessage(row).stream()
							.filter(element -> element.contains(title))
							.collect(Collectors.toList());
			if (matches.size() > 0)
				resultEmails.add(row);
		}
		return resultEmails;
	}
	
	private List<String> getAllTextFromMessage(WebElement element) {
		List<String> cellValues = JsoupHelper.findTexts(element, selectorEmailCell);
		return cellValues;
	}
}
