package com.capgemini.mrchecker.selenium.projectX.registration;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.runner.RunWith;

import com.capgemini.mrchecker.core.groupTestCases.testSuites.tags.TestsSelenium;
import com.capgemini.mrchecker.selenium.pages.environment.PageTitlesEnum;
import com.capgemini.mrchecker.selenium.pages.projectX.registration.RegistrationPage;
import com.capgemini.mrchecker.selenium.projectX.registration.utils.DataProviderExternalJsonFile;
import com.capgemini.mrchecker.selenium.projectX.registration.utils.FormDataContainer;
import com.capgemini.mrchecker.test.core.BaseTest;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;

@Category({ TestsSelenium.class })
@RunWith(JUnitParamsRunner.class)
public class RegisterOKTestDDExternalDataTest extends BaseTest {
	
	public RegistrationPage registrationPage;
	
	@Override
	public void setUp() {
		registrationPage = new RegistrationPage();
	}
	
	@Override
	public void tearDown() {
		registrationPage.load();
	}
	
	@Test
	@Parameters(source = DataProviderExternalJsonFile.class)
	public void registrationTest(FormDataContainer data) throws InterruptedException {
		assertTrue("Site title: " + registrationPage.getActualPageTitle(),
				registrationPage.getActualPageTitle()
						.equals(PageTitlesEnum.REGISTRATION.toString()));
		
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
		
		assertTrue("Registration succeed text visible: ", registrationPage.isRegistryErrorTextVisible());
		
		return;
	}
}
