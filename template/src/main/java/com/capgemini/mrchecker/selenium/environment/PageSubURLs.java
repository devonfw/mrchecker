package com.capgemini.mrchecker.selenium.environment;

public enum PageSubURLs {
	MAIN_PAGE("");
	
	/*
	 * Sub urls are used as real locations in test environment
	 */
	private final String subURL;
	
	PageSubURLs(String subURL) {
		this.subURL = subURL;
	}
	
	@Override
	public String toString() {
		return getValue();
	}
	
	public String getValue() {
		return subURL;
	}
}