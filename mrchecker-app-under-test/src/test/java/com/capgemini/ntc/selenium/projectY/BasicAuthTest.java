package com.capgemini.ntc.selenium.projectY;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.awt.AWTException;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import com.capgemini.ntc.core.groupTestCases.testSuites.tags.TestsLocal;
import com.capgemini.ntc.core.groupTestCases.testSuites.tags.TestsNONParallel;
import com.capgemini.ntc.selenium.pages.projectY.TheBasicAuthPage;
import com.capgemini.ntc.selenium.pages.projectY.TheInternetPage;
import com.capgemini.ntc.test.core.BaseTest;
import com.capgemini.ntc.test.core.logger.BFLogger;

@Category({ TestsLocal.class, TestsNONParallel.class })
public class BasicAuthTest extends BaseTest {
	
	private static TheInternetPage theInternetPage;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}
	
	@Override
	public void setUp() {
		BFLogger.logInfo("Open url http://the-internet.herokuapp.com/");
		theInternetPage = new TheInternetPage();
		BFLogger.logInfo("Check if page is loaded");
		assertTrue("The Internet Page isn't loaded", theInternetPage.isLoaded());
	}
	
	@Override
	public void tearDown() {
	}
	
	@Test
	public void shouldLogInWhenCredentialsCorrect() throws InterruptedException, AWTException {
		String login = "admin";
		String password = "admin";
		BFLogger.logInfo("Click on Basic Auth");
		TheBasicAuthPage theBasicAuthPage = theInternetPage.clickBasicAuthLink();
		BFLogger.logInfo("Enter login and password");
		theBasicAuthPage.enterLoginAndPassword(login, password);
		assertTrue("You are not on Basic Auth Page", theBasicAuthPage.isLoaded());
		BFLogger.logInfo("Check if user successfully logged in");
		String message = "Congratulations! You must have the proper credentials.";
		assertEquals("User isn't logged in", message, theBasicAuthPage.getMessageValue());
	}
	
	@Test
	public void shouldLogInWhenCredentialsInURL() {
		String login = "admin";
		String password = "admin";
		BFLogger.logInfo("Go to BasicAuthPage with login and password admin");
		TheBasicAuthPage theBasicAuthPage = new TheBasicAuthPage(true, login, password);
		BFLogger.logInfo("Check if user successfully logged in");
		String message = "Congratulations! You must have the proper credentials.";
		assertEquals("User isn't logged in", message, theBasicAuthPage.getMessageValue());
	}
}
