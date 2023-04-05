package com.capgemini.mrchecker.webapi.example.env;

public enum User {
	EXAMPLE_USER(GetEnvironmentParam.EXAMPLE_API_USERNAME, GetEnvironmentParam.EXAMPLE_API_PASSWORD, "Test user");
	
	private String username, userPassword, userFullName;
	
	User(GetEnvironmentParam username, GetEnvironmentParam password, String userFullName) {
		this.username = username.getValue();
		this.userPassword = password.getValue();
		this.userFullName = userFullName;
	}
	
	public String getUsername() {
		return username;
	}
	
	public String getPassword() {
		return userPassword;
	}
	
	public String getUserFullName() {
		return this.userFullName;
	}
	
}