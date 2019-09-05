package com.capgemini.mrchecker.selenium.environment;

public enum PageTitlesEnumMyThaiStar {
	MAIN_PAGE("My Thai Star"),
	;
	
	private String value;
	
	private PageTitlesEnumMyThaiStar(String value) {
		this.value = value;
	}
	
	@Override
	public String toString() {
		return value;
	}
}
