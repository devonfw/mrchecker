package com.capgemini.mrchecker.selenium.projectY;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.openqa.selenium.WebElement;

import com.capgemini.mrchecker.core.groupTestCases.testSuites.tags.TestsLocal;
import com.capgemini.mrchecker.core.groupTestCases.testSuites.tags.TestsNONParallel;
import com.capgemini.mrchecker.selenium.pages.projectY.ForgotPasswordEmailSentPage;
import com.capgemini.mrchecker.selenium.pages.projectY.ForgotPasswordPage;
import com.capgemini.mrchecker.selenium.pages.projectY.gmail.GmailAboutPage;
import com.capgemini.mrchecker.selenium.pages.projectY.gmail.GmailInboxPage;
import com.capgemini.mrchecker.selenium.pages.projectY.gmail.GmailSignInPage;
import com.capgemini.mrchecker.selenium.pages.projectY.gmail.GmailWelcomePage;

@Category({ TestsLocal.class, TestsNONParallel.class })
public class ForgotPasswordTest extends TheInternetBaseTest {
	
	private static final int	WAIT_TIME	= 30;
	private static final String	SENDER		= "no-reply@the-internet";
	private static final String	TITLE		= "Forgot Password from the-internet";
	
	private static String	emailAddress	= "";
	private static String	emailPassword	= "";
	
	private static ForgotPasswordPage			forgotPasswordPage;
	private static ForgotPasswordEmailSentPage	forgotPasswordEmailSentPage;
	private static GmailAboutPage				gmailAboutPage;
	private static GmailSignInPage				gmailSignInPage;
	private static GmailWelcomePage				gmailWelcomePage;
	private static GmailInboxPage				gmailInboxPage;
	
	@BeforeClass
	public static void setUpBeforeClass() {
		logStep("Open the Url: https://www.google.com/gmail/about/#");
		gmailAboutPage = new GmailAboutPage();
		
		logStep("Verify if Url: https://www.google.com/gmail/about/# is opened");
		assertTrue("Unable to open the 'Google About' page", gmailAboutPage.isLoaded());
		
		logStep("Click 'Sign in' button");
		gmailSignInPage = gmailAboutPage.clickSignInButton();
		
		logStep("Verify if Gmail 'Sign in' page is opened");
		assertTrue("Unable to open Gmail 'Sign in' page", gmailSignInPage.isLoaded());
		
		logStep("Enter email address and press 'Next' button");
		gmailWelcomePage = gmailSignInPage.enterEmailAddress(emailAddress)
						.clickNextButton();
		
		logStep("Verify if 'Welcome' page is opened");
		assertTrue("Unable to open 'Welcome' page", gmailWelcomePage.isLoaded());
		
		logStep("Enter password and press 'Next' button");
		gmailInboxPage = gmailWelcomePage.enterPassword(emailPassword)
						.clickNextButton();
		
		logStep("Verify if 'Inbox' page is opened");
		assertTrue("Unable to open 'Inbox' page", gmailInboxPage.isLoaded());
		
		logStep("Clear inbox for email address: " + emailAddress);
		gmailInboxPage.deleteAllEmails();
		
		logStep("Verify if all messages were removed");
		assertEquals("Unable to delete messages from inbox", gmailInboxPage.getNumberOfEmails(), 0);
		
		forgotPasswordPage = shouldTheInternetPageBeOpened().clickForgotPasswordLink();
		
		logStep("Verify if Forgot Password page is opened");
		assertTrue("Unable to open Forgot Password page", forgotPasswordPage.isLoaded());
	}
	
	@Ignore
	@Test
	public void verifyIfForgotPasswordEmailIsDisplayedCorrectly() {
		logStep("Verify if email text input is visible");
		assertTrue("Unable to find email text input element", forgotPasswordPage.isEmailInputVisible());
		
		logStep("Insert email address and click 'Retrieve password' button");
		forgotPasswordEmailSentPage = forgotPasswordPage.enterEmailAddress(emailAddress)
						.clickRetrievePasswordButton();
		
		logStep("Verify if 'Forgot password - email sent' page is opened");
		assertTrue("Unable to open 'Forgot password - email sent' page", forgotPasswordEmailSentPage.isLoaded());
		
		logStep("Verify if sending email was succesful");
		assertTrue("Sending an email failed", forgotPasswordEmailSentPage.isEmailSentSuccessfully());
		
		logStep("Open Gmail 'Inbox' page");
		gmailInboxPage.load();
		
		logStep("Verify if 'Inbox' page is opened");
		assertTrue("Unable to open 'Inbox' page", gmailInboxPage.isLoaded());
		
		logStep("Wait until email show up");
		gmailInboxPage.waitUntilEmailShowUp(WAIT_TIME);
		
		logStep("Verify if new email showed up");
		assertTrue("Unable to find any email in inbox", gmailInboxPage.getNumberOfEmails() > 0);
		
		logStep("Verify if there is new unread email in inbox");
		assertTrue("Unable to find any unread email", gmailInboxPage.isAnyEmailUnread());
		
		logStep("Verify if there is new email from proper sender: " + SENDER);
		List<WebElement> emails = gmailInboxPage.findEmailsForSender(SENDER);
		assertFalse("Unable to find any email with proper sender", emails.isEmpty());
		
		logStep("Verify if there is new email with proper title: " + TITLE);
		assertFalse("Unable to find any email from proper sender with proper title", gmailInboxPage.findEmailsForTitle(emails, TITLE)
						.isEmpty());
		
	}
	
}
