package com.capgemini.mrchecker.selenium.environment;

public enum PageTitles {
	MAIN_PAGE("Demoqa | Just another WordPress site");
	
	private final String value;
	
	PageTitles(String value) {
		this.value = value;
	}
	
	@Override
	public String toString() {
		return value;
	}
}