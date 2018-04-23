package com.capgemini.ntc.selenium.projectY;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.UUID;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import com.capgemini.ntc.core.groupTestCases.testSuites.tags.TestsLocal;
import com.capgemini.ntc.core.groupTestCases.testSuites.tags.TestsSelenium;
import com.capgemini.ntc.selenium.pages.projectY.FormAuthenticationPage;
import com.capgemini.ntc.selenium.pages.projectY.TheInternetPage;
import com.capgemini.ntc.test.core.BaseTest;
import com.capgemini.ntc.test.core.logger.BFLogger;

@Category({ TestsSelenium.class, TestsLocal.class })
public class FormAuthenticationTest extends BaseTest {
	
	private static TheInternetPage			theInternetPage;
	private static FormAuthenticationPage	formAuthenticationPage;
	private String							errorUsernameMessage	= "Your username is invalid!\n" + "×";
	private String							errorPasswordMessage	= "Your password is invalid!\n" + "×";
	private String							loginMessage			= "You logged into a secure area!\n" + "×";
	private String							logoutMessage			= "You logged out of the secure area!\n" + "×";
	private String							emptyUsername			= "";
	private String							emptyUserPassword		= "";
	private String							validUsername			= "tomsmith";
	private String							validPassword			= "SuperSecretPassword!";
	private String							randomUsername			= UUID.randomUUID()
					.toString();
	private String							randomUserPassword		= UUID.randomUUID()
					.toString();;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		BFLogger.logDebug("Step 1: Open the Url http://the-internet.herokuapp.com/");
		theInternetPage = new TheInternetPage();
		
		BFLogger.logDebug("Step 2: Verify if Url http://the-internet.herokuapp.com/ opens");
		assertTrue("The Internet Page was not open", theInternetPage.isLoaded());
		
		BFLogger.logDebug("Step 3: Click on the Form Authentication link");
		formAuthenticationPage = theInternetPage.clickFormAuthenticationLink();
		
		BFLogger.logDebug("Step 4: Verify if the Url http://the-internet.herokuapp.com/login");
		assertTrue("The Form Authentication Page was not open", formAuthenticationPage.isLoaded());
	}
	
	@Before
	public void setUp() {
		
	}
	
	@After
	public void tearDown() {
	}
	
	@Test
	public void loginWithEmptyUsernameAndPasswordTest() {
		BFLogger.logDebug("Step 5: Verify if the Url http://the-internet.herokuapp.com/login");
		formAuthenticationPage.setUsername(emptyUsername);
		formAuthenticationPage.setUserPassword(emptyUserPassword);
		formAuthenticationPage.clickLoginButton();
		assertEquals("The user can login with empty data", errorUsernameMessage,
						formAuthenticationPage.getLoginMessageText());
	}
	
	@Test
	public void loginWithEmptyUsernameAndValidPasswordTest() {
		formAuthenticationPage.setUsername(emptyUsername).setUserPassword(validPassword).clickLoginButton();
		assertEquals("The user can login with empty username", errorUsernameMessage,
						formAuthenticationPage.getLoginMessageText());
	}
	
	@Test
	public void loginWithValidUsernameAndEmptyPasswordTest() {
		formAuthenticationPage.setUsername(validUsername).setUserPassword(errorPasswordMessage).clickLoginButton();
		assertEquals("The user can login with empty password", errorPasswordMessage,
						formAuthenticationPage.getLoginMessageText());
	}
	
	@Test
	public void loginWithRandomUsernameAndRandomPasswordTest() {
		formAuthenticationPage.setUsername(randomUsername).setUserPassword(randomUserPassword).clickLoginButton();
		assertEquals("The user can login with random credentials", errorUsernameMessage,
						formAuthenticationPage.getLoginMessageText());
	}
	
	@Test
	public void loginWithValidCredentials() {
		formAuthenticationPage.setUsername(validUsername).setUserPassword(validPassword).clickLoginButton();
		assertEquals("The user can't login with valid credentials", loginMessage,
						formAuthenticationPage.getLoginMessageText());
		formAuthenticationPage.clickLogoutButton();
	}
	
	@Test
	public void logoutTest() {
		formAuthenticationPage.setUsername(validUsername).setUserPassword(validPassword).clickLoginButton();
		formAuthenticationPage.clickLogoutButton();
		assertEquals("The user can't logout", logoutMessage, formAuthenticationPage.getLoginMessageText());
	}
}
