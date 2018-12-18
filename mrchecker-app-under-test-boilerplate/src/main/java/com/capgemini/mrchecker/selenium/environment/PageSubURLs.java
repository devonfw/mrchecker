package com.capgemini.mrchecker.selenium.environment;

public enum PageSubURLs {
	
	MAIN_PAGE(""),
	;
	
	/*
	 * Sub urls are used as real locations in Bank test environment
	 */
	private String subURL;
	
	private PageSubURLs(String subURL) {
		this.subURL = subURL;
	};
	
	private PageSubURLs() {
		
	}
	
	@Override
	public String toString() {
		return getValue();
	}
	
	public String getValue() {
		return subURL;
	}
	
}