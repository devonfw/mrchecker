package com.capgemini.ntc.selenium.cucumber.features.registration.stepdefs;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import com.capgemini.ntc.test.core.unit.TestUtils;
import com.example.selenium.pages.enums.PageTitlesEnum;
import com.example.selenium.pages.features.registration.Hobby;
import com.example.selenium.pages.features.registration.MaritalStatus;
import com.example.selenium.pages.features.registration.RegistrationPage;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

/**
 * Created by LKURZAJ on 10.03.2017.
 */
public class RegistrationStepDefs {

    private RegistrationPage registrationPage = new RegistrationPage();
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

    @Given("I'm on the registration page as unlogged user")
    public void goToRegistrationPage(){
        registrationPage.load();
        assertTrue("Site title: " + registrationPage.getActualPageTitle(),
                registrationPage.getActualPageTitle().equals(PageTitlesEnum.REGISTRATION.toString()));
    }

    @When("I fill registration form with valid random data")
    public void fillAllRegistrationFields(){
        registrationPage.setFirstName(RegistrationStepDefs.firstName);
        registrationPage.setLastName(RegistrationStepDefs.lastName);
        registrationPage.setMaritalStatus(RegistrationStepDefs.maritalStatus);
        registrationPage.setHobby(RegistrationStepDefs.hobby);
        registrationPage.setCountry(RegistrationStepDefs.country);
        registrationPage.setBirthDate(RegistrationStepDefs.birthDate);
        registrationPage.setPhoneNumber(RegistrationStepDefs.phoneNumber);
        registrationPage.setUsername(RegistrationStepDefs.username);
        registrationPage.setEmail(RegistrationStepDefs.email);
        registrationPage.setProfilePicture(TestUtils.getAbsolutePathFor(RegistrationStepDefs.yourProfilePhotoPath));
        registrationPage.setAboutYourself(RegistrationStepDefs.aboutYourself);
        registrationPage.setPassword(RegistrationStepDefs.password);
        registrationPage.setConfirmPassword(RegistrationStepDefs.confirmPassword);

        registrationPage.clickSubmit();
    }

    @Then("I should see an error message: (.+)")
    public void checkComment(String expectedErrorMessage){
        assertEquals(expectedErrorMessage, registrationPage.getRegistryErrorText());
    }
}
