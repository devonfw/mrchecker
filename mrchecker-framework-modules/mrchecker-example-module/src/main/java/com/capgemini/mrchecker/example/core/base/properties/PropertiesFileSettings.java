package com.capgemini.mrchecker.example.core.base.properties;

import com.google.inject.Inject;
import com.google.inject.name.Named;

public class PropertiesFileSettings {
	
	private String property_1 = "How are you"; // default value
	private String property_2 = "What is your today success"; // default value
	
	@Inject(optional = true)
	private void setProperty_1(@Named("name.property_1") String path) {
		this.property_1 = path;
		
	}
	
	public String getProperty_1() {
		return this.property_1;
	}
	
	@Inject(optional = true)
	private void setProperty_2(@Named("name.property_2") String path) {
		this.property_2 = path;
		
	}
	
	public String getProperty_2() {
		return this.property_2;
	}
	
}
