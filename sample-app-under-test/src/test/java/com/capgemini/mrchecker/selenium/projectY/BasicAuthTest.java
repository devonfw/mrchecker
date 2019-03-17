package com.capgemini.mrchecker.selenium.projectY;

import static org.junit.Assert.assertEquals;

import java.awt.AWTException;

import org.junit.Test;
import org.junit.experimental.categories.Category;

import com.capgemini.mrchecker.core.groupTestCases.testSuites.tags.TestsLocal;
import com.capgemini.mrchecker.core.groupTestCases.testSuites.tags.TestsNONParallel;
import com.capgemini.mrchecker.selenium.pages.projectY.BasicAuthPage;

@Category({ TestsLocal.class, TestsNONParallel.class })
public class BasicAuthTest extends TheInternetBaseTest {
	
	private static BasicAuthPage basicAuthPage;
	
	private String	login		= "admin";
	private String	password	= "admin";
	private String	message		= "Congratulations! You must have the proper credentials.";
	
	@Test
	public void shouldUserLogInWithValidCredentials() throws InterruptedException, AWTException {
		basicAuthPage = shouldTheInternetPageBeOpened().clickBasicAuthLink();
		
		logStep("Enter login and password");
		basicAuthPage.enterLoginAndPassword(login, password);
		
		logStep("Verify if user logged in successfully");
		assertEquals("Unable to login user with valid credentials", message, basicAuthPage.getMessageValue());
	}
	
	@Test
	public void shouldUserLogInWithValidCredentialsSetInURL() {
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
