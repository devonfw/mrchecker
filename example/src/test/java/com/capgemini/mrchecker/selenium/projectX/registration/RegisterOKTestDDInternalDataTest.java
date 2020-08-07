package com.capgemini.mrchecker.selenium.projectX.registration;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.stream.Stream;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import com.capgemini.mrchecker.core.groupTestCases.testSuites.tags.TestsSelenium;
import com.capgemini.mrchecker.selenium.pages.environment.PageTitlesEnum;
import com.capgemini.mrchecker.selenium.pages.projectX.registration.Hobby;
import com.capgemini.mrchecker.selenium.pages.projectX.registration.MaritalStatus;
import com.capgemini.mrchecker.selenium.pages.projectX.registration.RegistrationPage;
import com.capgemini.mrchecker.selenium.projectX.registration.utils.DataProviderInternal;
import com.capgemini.mrchecker.test.core.BaseTest;
import com.capgemini.mrchecker.test.core.utils.PageFactory;

@TestsSelenium
@Disabled("Registration site not not on the Web")
@Tag("RegisterOKTestDDInternalDataTest")
public class RegisterOKTestDDInternalDataTest extends BaseTest {
	
	private static RegistrationPage registrationPage;
	
	private static Stream<DataProviderInternal> data() {
		return Stream.of(
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
						.setConfirmPassword("xsw2#$_rewgwASD"));
	}
	
	@Override
	public void setUp() {
		registrationPage = PageFactory.getPageInstance(RegistrationPage.class);
	}
	
	@Override
	public void tearDown() {
		registrationPage.load();
	}
	
	@ParameterizedTest
	@MethodSource("data")
	public void registrationTest(DataProviderInternal dataProviderInternal) {
		assertEquals("Site title: " + registrationPage.getActualPageTitle(), registrationPage.getActualPageTitle(), PageTitlesEnum.REGISTRATION.toString());
		
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
		assertTrue("Registration succeed text visible: ", registrationPage.isRegistryErrorTextVisible());
	}
}
