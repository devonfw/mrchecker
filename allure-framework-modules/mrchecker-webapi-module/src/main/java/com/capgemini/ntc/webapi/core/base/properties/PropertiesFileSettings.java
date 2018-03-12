package com.capgemini.ntc.webapi.core.base.properties;

import com.google.inject.Inject;
import com.google.inject.name.Named;

public class PropertiesFileSettings {
	
	private String property_1 = "How are you"; // default value
	
	@Inject(optional = true)
	private void setProperty_1(@Named("name.property_1") String path) {
		this.property_1 = path;
		
	}
	
	public String getProperty_1() {
		return this.property_1;
	}
	
}
