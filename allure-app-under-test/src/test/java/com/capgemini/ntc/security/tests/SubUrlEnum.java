package com.capgemini.ntc.security.tests;

import com.capgemini.ntc.selenium.core.Url;

public enum SubUrlEnum implements Url {
	
	LOGIN("mythaistar/login"),
	IMG_DIR("/assets/images/"),
	REST_ROOT("/mythaistar/services/rest");
	
	private String subURL;
	
	private SubUrlEnum(String subURL) {
		this.subURL = subURL;
	};
	
	private SubUrlEnum() {
		
	}
	
	@Override
	public String toString() {
		return getAddress();
	}
	
	@Override
	public String getAddress() {
		return subURL;
	}
	
}