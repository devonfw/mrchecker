package com.capgemini.framework.environment.csvEnv;

public enum GetEnvironmentParam {
	//Reads the environment variable from /src/resources/environments/environment.csv
	// Name of enum must be in line with cell name in /src/resources/environments/environment.csv
	
	DEMO_QA_URL,
	DEMO_QA_LOGIN_URL,
	DEMO_QA_USER_LOGIN,
	DEMO_QA_USER_PASSWORD,
	DEMO_QA_ADMIN_LOGIN,
	DEMO_QA_ADMIN_PASSWORD;
	
	private String value;
	
	public String getValue() {
		this.value = EnvironmentCsvReader.getValue(this.name())
				.trim();
		return this.value;
	}
	
	@Override
	public String toString() {
		return getValue();
	}
	
}