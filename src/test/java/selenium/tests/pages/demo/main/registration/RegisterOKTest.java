package com.example.selenium.tests.tests.pages.demo.main.registration;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.experimental.categories.Category;

import com.example.selenium.pages.enums.PageTitlesEnum;
import com.example.selenium.pages.features.registration.Hobby;
import com.example.selenium.pages.features.registration.MaritalStatus;
import com.example.selenium.pages.features.registration.RegistrationPage;
import com.example.selenium.tests.testSuites.testType.TestsFailed;
import com.example.selenium.tests.testSuites.testType.TestsSelenium;
import com.example.core.tests.TestUtils;
import com.example.core.tests.core.BaseTest;

@Category({ TestsSelenium.class, TestsFailed.class })
public class RegisterOKTest extends BaseTest {

	private static RegistrationPage registrationPage;

	private static String firstName = "John";
	private static String lastName = "Travolta";
	private static MaritalStatus maritalStatus = MaritalStatus.SINGLE;
	private static Hobby[] hobby = { Hobby.READING, Hobby.DANCE };
	private static String country = "Poland";
	private static String birthDate = "12-6-1986"; // mm-dd-yyyy
	private static String phoneNumber = "0077333444555";
	private static String username = "johnTravolta28";
	private static String email = "john.travolta28@test.me";
	private static String yourProfilePhotoPath = "com/example/demo/cucumber/features/registration/graph/ok-graph.gif";
	private static String aboutYourself = "It's me.";
	private static String password = "1w@e#r$W_12";
	private static String confirmPassword = password;

	@Override
	public void setUp() {
		registrationPage = new RegistrationPage();
	}

	@Override
	public void tearDown() {
		// TODO Auto-generated method stub
	}

	@Test
	public void Register_OK() {
		assertTrue("Site title: " + registrationPage.getActualPageTitle(),
				registrationPage.getActualPageTitle().equals(PageTitlesEnum.REGISTRATION.toString()));

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
		return;
	}

}
