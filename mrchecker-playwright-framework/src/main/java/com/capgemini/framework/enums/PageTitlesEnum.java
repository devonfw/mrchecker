package com.capgemini.framework.enums;

public enum PageTitlesEnum {
	LOGIN("Login | todo"),
	HOME("Home | todo");
	
	private final String val;
	
	PageTitlesEnum(String value) {
		this.val = value;
	}
	
	@Override
	public String toString() {
		return getValue();
	}
	
	public String getValue() {
		return val;
	}
}