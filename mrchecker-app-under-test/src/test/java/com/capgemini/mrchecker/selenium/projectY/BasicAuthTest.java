package com.capgemini.mrchecker.selenium.projectY;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.awt.AWTException;

import org.junit.Test;
import org.junit.experimental.categories.Category;

import com.capgemini.mrchecker.core.groupTestCases.testSuites.tags.TestsLocal;
import com.capgemini.mrchecker.core.groupTestCases.testSuites.tags.TestsNONParallel;
import com.capgemini.mrchecker.selenium.pages.projectY.BasicAuthPage;
import com.capgemini.mrchecker.selenium.pages.projectY.TheInternetPage;

@Category({ TestsLocal.class, TestsNONParallel.class })
public class BasicAuthTest extends TheInternetBaseTest<BasicAuthPage> {
	
	private static TheInternetPage<BasicAuthPage>	theInternetPage;
	private static BasicAuthPage					basicAuthPage;
	
	private String	login		= "admin";
	private String	password	= "admin";
	private String	message		= "Congratulations! You must have the proper credentials.";
	
	@Test
	public void shouldUserLogInWithValidCredentials() throws InterruptedException, AWTException {
		logStep("Open the Url http://the-internet.herokuapp.com/");
		basicAuthPage = new BasicAuthPage();
		theInternetPage = new TheInternetPage<>(basicAuthPage);
		theInternetPage.load();
		
		logStep("Verify if Url http://the-internet.herokuapp.com/ is opened");
		assertTrue("The Internet Page was not open", theInternetPage.isLoaded());
		
		logStep("Click Basic Auth link");
		basicAuthPage.clickPageLink();
		
		logStep("Enter login and password");
		basicAuthPage.enterLoginAndPassword(login, password);
		
		logStep("Verify if user logged in successfully");
		assertEquals("Unable to login user with valid credentials", message, basicAuthPage.getMessageValue());
	}
	
	@Test
	public void shouldUserLogInWithValidCredentialsSetInURL() {
		logStep("Open the Url http://the-internet.herokuapp.com/");
		theInternetPage = new TheInternetPage<>();
		theInternetPage.load();
		
		logStep("Verify if Url http://the-internet.herokuapp.com/ is opened");
		assertTrue("The Internet Page was not open", theInternetPage.isLoaded());
		
		logStep("Enter user's credentials into URL to log in");
		basicAuthPage = new BasicAuthPage(login, password);
		
		logStep("Verify if user logged in successfully");
		assertEquals("Unable to login user with valid credentials", message, basicAuthPage.getMessageValue());
	}
	
	@Override
	public void tearDown() {
		logStep("Navigate back to The-Internet page");
		theInternetPage.load();
	}
}
