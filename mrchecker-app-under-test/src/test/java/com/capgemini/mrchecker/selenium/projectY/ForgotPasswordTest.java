package com.capgemini.mrchecker.selenium.projectY;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.openqa.selenium.WebElement;

import com.capgemini.mrchecker.selenium.pages.projectY.ForgotPasswordEmailSentPage;
import com.capgemini.mrchecker.selenium.pages.projectY.ForgotPasswordPage;
import com.capgemini.mrchecker.selenium.pages.projectY.TheInternetPage;
import com.capgemini.mrchecker.selenium.pages.projectY.gmail.GmailAboutPage;
import com.capgemini.mrchecker.selenium.pages.projectY.gmail.GmailInboxPage;
import com.capgemini.mrchecker.selenium.pages.projectY.gmail.GmailSignInPage;
import com.capgemini.mrchecker.selenium.pages.projectY.gmail.GmailWelcomePage;
import com.capgemini.mrchecker.test.core.BaseTest;
import com.capgemini.mrchecker.test.core.logger.BFLogger;

public class ForgotPasswordTest extends BaseTest {
	
	private static final int	WAIT_TIME	= 30;
	private static final String	SENDER		= "no-reply@the-internet";
	private static final String	TITLE		= "Forgot Password from the-internet";
	
	private static String	emailAddress	= "";
	private static String	emailPassword	= "";
	
	private static TheInternetPage				theInternetPage;
	private static ForgotPasswordPage			forgotPasswordPage;
	private static ForgotPasswordEmailSentPage	forgotPasswordEmailSentPage;
	private static GmailAboutPage				gmailAboutPage;
	private static GmailSignInPage				gmailSignInPage;
	private static GmailWelcomePage				gmailWelcomePage;
	private static GmailInboxPage				gmailInboxPage;
	
	@BeforeClass
	public static void setUpBeforeClass() {
		BFLogger.logInfo("Step 1: Open the Url: https://www.google.com/gmail/about/#");
		gmailAboutPage = new GmailAboutPage();
		
		BFLogger.logInfo("Step 2: Verify if Url: https://www.google.com/gmail/about/# is opened");
		assertTrue("Unable to open the 'Google About' page", gmailAboutPage.isLoaded());
		
		BFLogger.logInfo("Step 3: Click 'Sign in' button");
		gmailSignInPage = gmailAboutPage.clickSignInButton();
		
		BFLogger.logInfo("Step 4: Verify if Gmail 'Sign in' page is opened");
		assertTrue("Unable to open Gmail 'Sign in' page", gmailSignInPage.isLoaded());
		
		BFLogger.logInfo("Step 5: Enter email address and press 'Next' button");
		gmailWelcomePage = gmailSignInPage.enterEmailAddress(emailAddress)
						.clickNextButton();
		
		BFLogger.logInfo("Step 6: Verify if 'Welcome' page is opened");
		assertTrue("Unable to open 'Welcome' page", gmailWelcomePage.isLoaded());
		
		BFLogger.logInfo("Step 7: Enter password and press 'Next' button");
		gmailInboxPage = gmailWelcomePage.enterPassword(emailPassword)
						.clickNextButton();
		
		BFLogger.logInfo("Step 8: Verify if 'Inbox' page is opened");
		assertTrue("Unable to open 'Inbox' page", gmailInboxPage.isLoaded());
		
		BFLogger.logInfo("Step 9: Clear inbox for email address: " + emailAddress);
		gmailInboxPage.deleteAllEmails();
		
		BFLogger.logInfo("Step 10: Verify if all messages were removed");
		assertEquals("Unable to delete messages from inbox", gmailInboxPage.getNumberOfEmails(), 0);
		
		BFLogger.logInfo("Step 11: Open the Url http://the-internet.herokuapp.com/");
		theInternetPage = new TheInternetPage();
		theInternetPage.load();
		
		BFLogger.logInfo("Step 12: Verify if Url http://the-internet.herokuapp.com/ is opened");
		assertTrue("Unable to open 'The Internet' page", theInternetPage.isLoaded());
		
		BFLogger.logInfo("Step 13: Click 'Forgot Password' link");
		forgotPasswordPage = theInternetPage.clickForgotPasswordLink();
		
		BFLogger.logInfo("Step 14: Verify if 'Forgot Password' page is opened");
		assertTrue("Unable to open 'Forgot Password' page", forgotPasswordPage.isLoaded());
	}
	
	@Ignore
	@Test
	public void verifyIfForgotPasswordEmailIsDisplayedCorrectly() {
		BFLogger.logInfo("Step 14: Verify if email text input is visible");
		assertTrue("Unable to find email text input element", forgotPasswordPage.isEmailInputVisible());
		
		BFLogger.logInfo("Step 15: Insert email address and click 'Retrieve password' button");
		forgotPasswordEmailSentPage = forgotPasswordPage.enterEmailAddress(emailAddress)
						.clickRetrievePasswordButton();
		
		BFLogger.logInfo("Step 16: Verify if 'Forgot password - email sent' page is opened");
		assertTrue("Unable to open 'Forgot password - email sent' page", forgotPasswordEmailSentPage.isLoaded());
		
		BFLogger.logInfo("Step 17: Verify if sending email was succesful");
		assertTrue("Sending an email failed", forgotPasswordEmailSentPage.isEmailSentSuccessfully());
		
		BFLogger.logInfo("Step 18: Open Gmail 'Inbox' page");
		gmailInboxPage.load();
		
		BFLogger.logInfo("Step 19: Verify if 'Inbox' page is opened");
		assertTrue("Unable to open 'Inbox' page", gmailInboxPage.isLoaded());
		
		BFLogger.logInfo("Step 20: Wait until email show up");
		gmailInboxPage.waitUntilEmailShowUp(WAIT_TIME);
		
		BFLogger.logInfo("Step 21: Verify if new email showed up");
		assertTrue("Unable to find any email in inbox", gmailInboxPage.getNumberOfEmails() > 0);
		
		BFLogger.logInfo("Step 22: Verify if there is new unread email in inbox");
		assertTrue("Unable to find any unread email", gmailInboxPage.isAnyEmailUnread());
		
		BFLogger.logInfo("Step 23: Verify if there is new email from proper sender: " + SENDER);
		List<WebElement> emails = gmailInboxPage.findEmailsForSender(SENDER);
		assertFalse("Unable to find any email with proper sender", emails.isEmpty());
		
		BFLogger.logInfo("Step 24: Verify if there is new email with proper title: " + TITLE);
		assertFalse("Unable to find any email from proper sender with proper title", gmailInboxPage.findEmailsForTitle(emails, TITLE)
						.isEmpty());
		
	}
	
	@Override
	public void setUp() {
		
	}
	
	@Override
	public void tearDown() {
		
	}
	
}
