package com.capgemini.framework.enums;

import com.capgemini.framework.logger.AllureStepLogger;
import io.qameta.allure.Step;
import net.datafaker.Faker;

import java.time.format.DateTimeFormatter;


public class DemoQAStudent {
	

	
	private final String firstName;
	private final String lastName;
	private final String mail;
	private final Gender gender;
	private final String mobileNumber;
	private final String dateOfBirth;
	private final String subject;
	private       Hobbies  hobbies;
	private       String   picture;
	private final String   currentAddress;
	private final String   state;
	private final Ncr_City city;
	public String getFirstName() {
		return firstName;
	}
	public String getLastName() {
		return lastName;
	}
	
	public String getMail() {
		return mail;
	}
	
	public Gender getGender() {
		return gender;
	}
	
	public String getMobileNumber() {
		return mobileNumber;
	}
	
	public String getDateOfBirth() {
		return dateOfBirth;
	}
	
	public String getSubject() {
		return subject;
	}
	
	public Hobbies getHobbies() {
		return hobbies;
	}
	
	public String getPicture() {
		return picture;
	}
	
	public String getCurrentAddress() {
		return currentAddress;
	}
	
	public String getState() {
		return state;
	}
	
	public Ncr_City getCity() {
		return city;
	}
	

	
	public enum Gender {
		MALE,
		FEMALE,
		OTHER
	}
	
	public enum Ncr_City {
		Delhi,
		Gurgaon,
		Noida
	}
	public enum Hobbies {
		Sports,
		Reading,
		Music
	}
	public DemoQAStudent() {
		var faker = new Faker();
		firstName = faker.name()
				.firstName();
		lastName = faker.name()
				.lastName();
		mail = faker.internet()
				.emailAddress();
		gender = faker.options()
				.option(Gender.class);
		mobileNumber = faker.number().digits(10);
		
		dateOfBirth = faker.date()
				.birthday()
				.toLocalDateTime()
				.format(DateTimeFormatter.ofPattern("dd MMM yyyy"));
		subject = faker.team()
				.sport();
		hobbies = faker.options()
				.option(Hobbies.class);
		//todo picture =
		currentAddress = faker.address()
				.fullAddress();
		state = "NCR";
		city = faker.options()
				.option(Ncr_City.class);
	
	}
	

	@Step("Student Data")
	public void logStudent() {
		AllureStepLogger.step("firstName: " + firstName);
		AllureStepLogger.step("lastName: " + lastName);
		AllureStepLogger.step("mail: " + mail);
		AllureStepLogger.step("gender: " + gender);
		AllureStepLogger.step("mobileNumber: " + mobileNumber);
		AllureStepLogger.step("dateOfBirth: " + dateOfBirth);
		AllureStepLogger.step("subject: " + subject);
		AllureStepLogger.step("hobbies: " + hobbies);
		AllureStepLogger.step("currentAddress: " + currentAddress);
		AllureStepLogger.step("state: " + state);
		AllureStepLogger.step("city: " + city);
	};
}
