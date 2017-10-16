package com.capgemini.ntc.selenium.tests.tests.pages.demo.main.registration;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.runner.RunWith;

import com.capgemini.ntc.selenium.pages.enums.PageTitlesEnum;
import com.capgemini.ntc.selenium.pages.features.registration.RegistrationPage;
import com.capgemini.ntc.selenium.tests.testSuites.testType.TestsSelenium;
import com.capgemini.ntc.selenium.tests.tests.pages.demo.main.registration.utils.DataProviderExternalJsonFile;
import com.example.core.tests.core.BaseTest;
import com.example.core.tests.testRunners.ParallelParameterized;
import com.example.core.tests.utils.FormDataContainer;

import junitparams.Parameters;

@Category({ TestsSelenium.class })
@RunWith(ParallelParameterized.class)
public class RegisterOKTestDDExternalDataTest extends BaseTest {

	public RegistrationPage registrationPage;

	@Override
	public void setUp() {
		registrationPage = new RegistrationPage(getDriver());
	}

	@Override
	public void tearDown() {
		registrationPage.load();
	}

	@Test
	@Parameters(source = DataProviderExternalJsonFile.class)
	public void registrationTest(FormDataContainer data) throws InterruptedException {
		assertTrue("Site title: " + registrationPage.getActualPageTitle(),
				registrationPage.getActualPageTitle().equals(PageTitlesEnum.REGISTRATION.toString()));

		registrationPage.setFirstName(data.getFirstName());
		registrationPage.setLastName(data.getLastName());
		registrationPage.setMaritalStatus(data.getMaritalStatus());
		registrationPage.setHobby(data.getHobby());
		registrationPage.setCountry(data.getCountry());
		registrationPage.setBirthDate(data.getBirthDate());
		registrationPage.setPhoneNumber(data.getPhoneNumber());
		registrationPage.setUsername(data.getUsername());
		registrationPage.setEmail(data.getEmail());
		registrationPage.setProfilePicture(data.getYourProfilePhotoPath());
		registrationPage.setAboutYourself(data.getAboutYourself());
		registrationPage.setPassword(data.getPassword());
		registrationPage.setConfirmPassword(data.getConfirmPassword());

		registrationPage.clickSubmit();

		assertTrue("Registration succeed text visible: ", registrationPage.isRegistrationSuceedTextVisible());
		
		
		
		return;
	}
}
