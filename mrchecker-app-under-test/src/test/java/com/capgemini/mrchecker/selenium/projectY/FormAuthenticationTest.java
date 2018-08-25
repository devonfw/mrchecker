package com.capgemini.mrchecker.selenium.projectY;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.UUID;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import com.capgemini.mrchecker.core.groupTestCases.testSuites.tags.TestsLocal;
import com.capgemini.mrchecker.core.groupTestCases.testSuites.tags.TestsNONParallel;
import com.capgemini.mrchecker.selenium.pages.projectY.FormAuthenticationPage;
import com.capgemini.mrchecker.selenium.pages.projectY.TheInternetPage;

@Category({ TestsLocal.class, TestsNONParallel.class })
public class FormAuthenticationTest extends TheInternetBaseTest {
	
	private static FormAuthenticationPage formAuthenticationPage;
	
	private String	errorUsernameMessage	= "Your username is invalid!\n" + "×";
	private String	errorPasswordMessage	= "Your password is invalid!\n" + "×";
	private String	loginMessage			= "You logged into a secure area!\n" + "×";
	private String	logoutMessage			= "You logged out of the secure area!\n" + "×";
	private String	emptyUsername			= "";
	private String	emptyUserPassword		= "";
	private String	validUsername			= "tomsmith";
	private String	validPassword			= "SuperSecretPassword!";
	private String	randomUsername			= UUID.randomUUID()
					.toString();
	private String	randomUserPassword		= UUID.randomUUID()
					.toString();
	
	@BeforeClass
	public static void setUpBeforeClass() {
		logStep("Open the Url http://the-internet.herokuapp.com/");
		theInternetPage = new TheInternetPage();
		theInternetPage.load();
		
		logStep("Verify if Url http://the-internet.herokuapp.com/ is opened");
		assertTrue("Unable to load The Internet Page", theInternetPage.isLoaded());
	}
	
	@Override
	public void setUp() {
		logStep("Click subpage link");
		formAuthenticationPage = theInternetPage.clickFormAuthenticationLink();
		
		logStep("Verify if subpage is opened");
		assertTrue("The Internet subpage: FormAuthenticationPage was not open", formAuthenticationPage.isLoaded());
	}
	
	@Test
	public void shouldErrorMessageBeDisplayedWhenUserLogsWithEmptyData() {
		logStep("Log user with empty username and password");
		formAuthenticationPage.setUsername(emptyUsername)
						.setUserPassword(emptyUserPassword)
						.clickLoginButton();
		assertEquals("Unexpectedly user logged in with empty data", errorUsernameMessage,
						formAuthenticationPage.getLoginMessageText());
	}
	
	@Test
	public void shouldErrorMessageBeDisplayedWhenUserLogsWithEmptyUsernameAndValidPassword() {
		logStep("Log user with empty username and valid password");
		formAuthenticationPage.setUsername(emptyUsername)
						.setUserPassword(validPassword)
						.clickLoginButton();
		assertEquals("Unexpectedly user logged in with empty username", errorUsernameMessage,
						formAuthenticationPage.getLoginMessageText());
	}
	
	@Test
	public void shouldErrorMessageBeDisplayedWhenUserLogsWithValidUsernameAndEmptyPassword() {
		logStep("Log user with valid username and empty password");
		formAuthenticationPage.setUsername(validUsername)
						.setUserPassword(emptyUserPassword)
						.clickLoginButton();
		assertEquals("Unexpectedly user logged in with empty password", errorPasswordMessage,
						formAuthenticationPage.getLoginMessageText());
	}
	
	@Test
	public void shouldErrorMessageBeDisplayedWhenUserLogsWithInvalidUsernameAndInvalidPassword() {
		logStep("Log user with invalid username and invalid password");
		formAuthenticationPage.setUsername(randomUsername)
						.setUserPassword(randomUserPassword)
						.clickLoginButton();
		assertEquals("Unexpectedly user logged in with random credentials", errorUsernameMessage,
						formAuthenticationPage.getLoginMessageText());
	}
	
	@Test
	public void shouldUserLogInWithValidCredentials() {
		logStep("Log user with valid username and valid password");
		formAuthenticationPage.setUsername(validUsername)
						.setUserPassword(validPassword)
						.clickLoginButton();
		assertEquals("Unable to login user with valid credentials", loginMessage,
						formAuthenticationPage.getLoginMessageText());
		logStep("Log out user");
		formAuthenticationPage.clickLogoutButton();
	}
	
	@Test
	public void shouldUserLogOutAfterProperLogInAndClickLogoutButon() {
		logStep("Log user with valid username and valid password");
		formAuthenticationPage.setUsername(validUsername)
						.setUserPassword(validPassword)
						.clickLoginButton();
		assertEquals("Unable to login user with valid credentials", loginMessage,
						formAuthenticationPage.getLoginMessageText());
		logStep("Log out user");
		formAuthenticationPage.clickLogoutButton();
		assertEquals("User cannot log out after prper log in", logoutMessage,
						formAuthenticationPage.getLoginMessageText());
	}
	
	@Override
	public void tearDown() {
		logStep("Navigate back to The-Internet page");
		theInternetPage.load();
	}
}
