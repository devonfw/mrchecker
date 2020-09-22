package com.capgemini.mrchecker.selenium.mts.common.data;

public class User {
	private static final String	DEFAULT_USERNAME	= "waiter";
	private static final String	DEFAULT_PASSWORD	= "waiter";
	
	private final String	username;
	private final String	password;
	
	public User(String username, String password) {
		this.username = username;
		this.password = password;
	}
	
	public User(String username) {
		this.username = username;
		this.password = "fakepassword";
	}
	
	public static User defaultUser() {
		return new User(DEFAULT_USERNAME, DEFAULT_PASSWORD);
	}
	
	public static User waiterUser() {
		return defaultUser();
	}
	
	public String getUsername() {
		return username;
	}
	
	public String getPassword() {
		return password;
	}
	
	@Override
	public String toString() {
		return "User with username: " + username + " and password: " + password;
	}
}