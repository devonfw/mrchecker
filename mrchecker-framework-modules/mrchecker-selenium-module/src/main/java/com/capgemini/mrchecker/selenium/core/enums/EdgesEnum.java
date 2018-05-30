package com.capgemini.mrchecker.selenium.core.enums;

public enum EdgesEnum {
	
	TOP("top"), BOTTOM("bottom"), LEFT("left"), RIGHT("right");
	
	private String value;
	
	private EdgesEnum(String value) {
		this.value = value;
	}
	
	public String toString() {
		return value;
	}
	
}
