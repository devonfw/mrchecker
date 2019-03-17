package com.capgemini.mrchecker.selenium.projectX.registration.utils;

import com.capgemini.mrchecker.selenium.core.utils.TestUtils;
import com.capgemini.mrchecker.selenium.pages.projectX.registration.Hobby;
import com.capgemini.mrchecker.selenium.pages.projectX.registration.MaritalStatus;

public class FormDataContainer {
	
	private String firstName;
	private String lastName;
	private MaritalStatus maritalStatus;
	private Hobby hobby;
	private String country;
	private String birthDate;
	private String phoneNumber;
	private String username;
	private String email;
	private String yourProfilePhotoPath;
	private String aboutYourself;
	private String password;
	private String confirmPassword;
	
	public FormDataContainer(String firstName, String lastName, String maritalStatus, String hobby, String country,
			String birthDate, String phoneNumber, String username, String email, String yourProfilePhotoPath,
			String aboutYourself, String password, String confirmPassword) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.maritalStatus = MaritalStatus.valueOf(maritalStatus);
		this.hobby = Hobby.valueOf(hobby);
		this.country = country;
		this.birthDate = birthDate;
		this.phoneNumber = phoneNumber;
		this.username = username;
		this.email = email;
		this.yourProfilePhotoPath = yourProfilePhotoPath;
		this.aboutYourself = aboutYourself;
		this.password = password;
		this.confirmPassword = confirmPassword;
	}
	
	public String getFirstName() {
		return this.firstName;
	}
	
	public String getLastName() {
		return this.lastName;
	}
	
	public MaritalStatus getMaritalStatus() {
		return this.maritalStatus;
	}
	
	public Hobby getHobby() {
		return this.hobby;
	}
	
	public String getCountry() {
		return this.country;
	}
	
	public String getBirthDate() {
		return this.birthDate;
	}
	
	public String getPhoneNumber() {
		return this.phoneNumber;
	}
	
	public String getUsername() {
		return this.username;
	}
	
	public String getEmail() {
		return this.email;
	}
	
	public String getYourProfilePhotoPath() {
		this.yourProfilePhotoPath = TestUtils.getAbsolutePathFor(yourProfilePhotoPath);
		return this.yourProfilePhotoPath;
	}
	
	public String getAboutYourself() {
		
		return this.aboutYourself;
	}
	
	public String getPassword() {
		return this.password;
	}
	
	public String getConfirmPassword() {
		return this.confirmPassword;
	}
}
