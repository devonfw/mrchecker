package com.capgemini.ntc.security;

/**
 * This enumeration holds all sub-urls relevant for the security test suite.
 *
 * @author Marek Puchalski, Capgemini
 */
public enum SubUrlEnum {
	
	LOGIN("mythaistar/login"),
	IMG_DIR("/assets/images/"),
	REST_ROOT("/mythaistar/services/rest");
	
	private String subURL;
	
	private SubUrlEnum(String subURL) {
		this.subURL = subURL;
	};
	
	@Override
	public String toString() {
		return getValue();
	}
	
	public String getValue() {
		return subURL;
	}
	
}