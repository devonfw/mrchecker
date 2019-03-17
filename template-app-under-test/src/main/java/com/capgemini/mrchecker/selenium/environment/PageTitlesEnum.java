package com.capgemini.mrchecker.selenium.environment;

public enum PageTitlesEnum {
	MAIN_PAGE("Demoqa | Just another WordPress site"),
	;
	
	private String value;
	
	private PageTitlesEnum(String value) {
		this.value = value;
	}
	
	@Override
	public String toString() {
		return value;
	}
}