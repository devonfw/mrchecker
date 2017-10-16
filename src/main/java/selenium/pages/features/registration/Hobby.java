package com.example.selenium.pages.features.registration;

public enum Hobby {
	DANCE("dance"),
	READING("reading"),
	CRICKET("cricket");

	private final String text;

	Hobby(String hobby){
		this.text = hobby;
	}

	public String toString(){
		return text;
	}
}
