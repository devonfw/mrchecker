package com.capgemini.framework.enums;

import com.capgemini.framework.environment.csvEnv.GetEnvironmentParam;

public enum User {
	USER_ADMIN(GetEnvironmentParam.DEMO_QA_ADMIN_LOGIN.getValue(), GetEnvironmentParam.DEMO_QA_ADMIN_PASSWORD.getValue()),
	USER_ADMIN_QA(GetEnvironmentParam.DEMO_QA_USER_LOGIN.getValue(), GetEnvironmentParam.DEMO_QA_USER_PASSWORD.getValue());
	
	private final String password;
	private final String login;
	
	User(String login, String password) {
		this.login = login;
		this.password = password;
	}
	
	public String getLogin() {
		return login;
	}
	
	public String getPassword() {
		return this.password;
	}
	
	@Override
	public String toString() {
		return login;
	}
	
}