package com.example.selenium.tests.tests.pages.demo.main.registration;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.runner.RunWith;

import com.example.selenium.pages.enums.PageTitlesEnum;
import com.example.selenium.pages.features.registration.Hobby;
import com.example.selenium.pages.features.registration.MaritalStatus;
import com.example.selenium.pages.features.registration.RegistrationPage;
import com.example.selenium.tests.testSuites.testType.TestsSelenium;
import com.example.selenium.tests.tests.pages.demo.main.registration.utils.DataProviderInternal;
import com.example.core.tests.core.BaseTest;
import com.example.core.tests.testRunners.ParallelParameterized;

import junitparams.Parameters;

@Category({ TestsSelenium.class })
@RunWith(ParallelParameterized.class)
public class RegisterOKTestDDInternalDataTest extends BaseTest {

	private static RegistrationPage registrationPage;

	private Object[] data() {
		return new Object[] {
				new DataProviderInternal()
						.setFirstName("John")
						.setLastName("Travola")
						.setMaritalStatus(MaritalStatus.SINGLE)
						.setHobby(Hobby.DANCE)
						.setCountry("Angola")
						.setBirthDate("6-12-1951")
						.setPhoneNumber("0048777888999")
						.setUsername("john5Travolta")
						.setEmail("john52travolta@test.me")
						.setYourProfilePhotoPath(
								"com/example/demo/cucumber/features/registration/graph/ok-graph.gif")
						.setAboutYourself("It's me.")
						.setPassword("xsw2#$_rewgwASD")
						.setConfirmPassword("xsw2#$_rewgwASD"),
				// next data
				new DataProviderInternal()
						.setFirstName("Johny")
						.setLastName("Walker")
						.setMaritalStatus(MaritalStatus.MARRIED)
						.setHobby(Hobby.READING)
						.setCountry("Ghana")
						.setBirthDate("2-8-1984")
						.setPhoneNumber("0048777888999")
						.setUsername("john5Smith")
						.setEmail("john5Smith@test.me")
						.setYourProfilePhotoPath(
								"com/example/demo/cucumber/features/registration/graph/ok-graph.gif")
						.setAboutYourself("It's me.")
						.setPassword("xsw2#$_rewgwASD")
						.setConfirmPassword("xsw2#$_rewgwASD") };
	}

	@Override
	public void setUp() {
		registrationPage = new RegistrationPage();
	}

	@Override
	public void tearDown() {
		registrationPage.load();
	}

	@Test
	@Parameters(method = "data")
	public void registrationTest(DataProviderInternal dataProviderInternal) {
		assertTrue("Site title: " + registrationPage.getActualPageTitle(),
				registrationPage.getActualPageTitle().equals(PageTitlesEnum.REGISTRATION.toString()));

		registrationPage.setFirstName(dataProviderInternal.getFirstName());
		registrationPage.setLastName(dataProviderInternal.getLastName());
		registrationPage.setMaritalStatus(dataProviderInternal.getMaritalStatus());
		registrationPage.setHobby(dataProviderInternal.getHobby());
		registrationPage.setCountry(dataProviderInternal.getCountry());
		registrationPage.setBirthDate(dataProviderInternal.getBirthDate());
		registrationPage.setPhoneNumber(dataProviderInternal.getPhoneNumber());
		registrationPage.setUsername(dataProviderInternal.getUsername());
		registrationPage.setEmail(dataProviderInternal.getEmail());
		registrationPage.setProfilePicture(dataProviderInternal.getYourProfilePhotoPath());
		registrationPage.setAboutYourself(dataProviderInternal.getAboutYourself());
		registrationPage.setPassword(dataProviderInternal.getPassword());
		registrationPage.setConfirmPassword(dataProviderInternal.getConfirmPassword());

		registrationPage.clickSubmit();
		assertTrue("Registration succeed text visible: ", registrationPage.isRegistrationSuceedTextVisible());
		return;
	}

}
