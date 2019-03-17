package com.capgemini.mrchecker.selenium.projectX.registration.utils;

import com.capgemini.mrchecker.selenium.core.utils.TestUtils;
import com.capgemini.mrchecker.selenium.pages.projectX.registration.Hobby;
import com.capgemini.mrchecker.selenium.pages.projectX.registration.MaritalStatus;

public class DataProviderInternal {
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
	
	public String getFirstName() {
		return firstName;
	}
	
	public DataProviderInternal setFirstName(String firstName) {
		this.firstName = firstName;
		return this;
	}
	
	public String getLastName() {
		return lastName;
	}
	
	public DataProviderInternal setLastName(String lastName) {
		this.lastName = lastName;
		return this;
	}
	
	public MaritalStatus getMaritalStatus() {
		return maritalStatus;
	}
	
	public DataProviderInternal setMaritalStatus(MaritalStatus maritalStatus) {
		this.maritalStatus = maritalStatus;
		return this;
	}
	
	public Hobby getHobby() {
		return hobby;
	}
	
	public DataProviderInternal setHobby(Hobby hobby) {
		this.hobby = hobby;
		return this;
	}
	
	public String getCountry() {
		return country;
	}
	
	public DataProviderInternal setCountry(String country) {
		this.country = country;
		return this;
	}
	
	public String getBirthDate() {
		return birthDate;
	}
	
	public DataProviderInternal setBirthDate(String birthDate) {
		this.birthDate = birthDate;
		return this;
	}
	
	public String getPhoneNumber() {
		return phoneNumber;
	}
	
	public DataProviderInternal setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
		return this;
	}
	
	public String getUsername() {
		return username;
	}
	
	public DataProviderInternal setUsername(String username) {
		this.username = username;
		return this;
	}
	
	public String getEmail() {
		return email;
	}
	
	public DataProviderInternal setEmail(String email) {
		this.email = email;
		return this;
	}
	
	public String getYourProfilePhotoPath() {
		return yourProfilePhotoPath;
	}
	
	public DataProviderInternal setYourProfilePhotoPath(String yourProfilePhotoPath) {
		this.yourProfilePhotoPath = TestUtils.getAbsolutePathFor(yourProfilePhotoPath);
		return this;
	}
	
	public String getAboutYourself() {
		return aboutYourself;
	}
	
	public DataProviderInternal setAboutYourself(String aboutYourself) {
		this.aboutYourself = aboutYourself;
		return this;
	}
	
	public String getPassword() {
		return password;
	}
	
	public DataProviderInternal setPassword(String password) {
		this.password = password;
		return this;
	}
	
	public String getConfirmPassword() {
		return confirmPassword;
	}
	
	public DataProviderInternal setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
		return this;
	}
	
	@Override
	public String toString() {
		return "firstName ((" + getFirstName() + "), lastName ((" + getLastName() + "), maritalStatus (("
				+ getMaritalStatus() + "), hobby (" + getHobby() + "), country (" + getCountry() + "), birthDate ("
				+ getBirthDate() + "), phoneNumber (" + getPhoneNumber() + "), username (" + getUsername()
				+ "), email (" + getEmail() + "), yourProfilePicture (" + getYourProfilePhotoPath()
				+ "), aboutYourself (" + getAboutYourself() + "), password (" + getPassword() + "), confirmPassword ("
				+ getConfirmPassword();
	}
}