package com.capgemini.mrchecker.selenium.projectX.registration;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import com.capgemini.mrchecker.core.groupTestCases.testSuites.tags.TestsFailed;
import com.capgemini.mrchecker.core.groupTestCases.testSuites.tags.TestsSelenium;
import com.capgemini.mrchecker.selenium.core.utils.TestUtils;
import com.capgemini.mrchecker.selenium.pages.environment.PageTitlesEnum;
import com.capgemini.mrchecker.selenium.pages.projectX.registration.Hobby;
import com.capgemini.mrchecker.selenium.pages.projectX.registration.MaritalStatus;
import com.capgemini.mrchecker.selenium.pages.projectX.registration.RegistrationPage;
import com.capgemini.mrchecker.test.core.BaseTest;
import com.capgemini.mrchecker.test.core.utils.PageFactory;

@TestsSelenium
@TestsFailed
@Disabled("Registration site not not on the Web")
@Tag("RegisterOKTest")
public class RegisterOKTest extends BaseTest {
	
	private static RegistrationPage registrationPage;
	
	private static String			firstName				= "John";
	private static String			lastName				= "Travolta";
	private static MaritalStatus	maritalStatus			= MaritalStatus.SINGLE;
	private static Hobby[]			hobby					= { Hobby.READING, Hobby.DANCE };
	private static String			country					= "Poland";
	private static String			birthDate				= "12-6-1986";															// mm-dd-yyyy
	private static String			phoneNumber				= "0077333444555";
	private static String			username				= "johnTravolta28";
	private static String			email					= "john.travolta28@test.me";
	private static String			yourProfilePhotoPath	= "com/example/demo/cucumber/features/registration/graph/ok-graph.gif";
	private static String			aboutYourself			= "It's me.";
	private static String			password				= "1w@e#r$W_12";
	private static String			confirmPassword			= password;
	
	@Override
	public void setUp() {
		registrationPage = PageFactory.getPageInstance(RegistrationPage.class);
	}
	
	@Test
	public void Register_OK() {
		assertEquals("Site title: " + registrationPage.getActualPageTitle(), registrationPage.getActualPageTitle(), PageTitlesEnum.REGISTRATION.toString());
		
		registrationPage.setFirstName(firstName);
		registrationPage.setLastName(lastName);
		registrationPage.setMaritalStatus(maritalStatus);
		registrationPage.setHobby(hobby);
		registrationPage.setCountry(country);
		registrationPage.setBirthDate(birthDate);
		registrationPage.setPhoneNumber(phoneNumber);
		registrationPage.setUsername(username);
		registrationPage.setEmail(email);
		registrationPage.setProfilePicture(TestUtils.getAbsolutePathFor(yourProfilePhotoPath));
		registrationPage.setAboutYourself(aboutYourself);
		registrationPage.setPassword(password);
		registrationPage.setConfirmPassword(confirmPassword);
		
		registrationPage.clickSubmit();
		
		assertTrue("Username already exists", registrationPage.isRegistryErrorTextVisible());
	}
}
