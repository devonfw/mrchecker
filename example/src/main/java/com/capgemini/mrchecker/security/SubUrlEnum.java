package com.capgemini.mrchecker.security;

/**
 * This enumeration holds all sub-urls relevant for the security test suite.
 *
 * @author Marek Puchalski, Capgemini
 */
public enum SubUrlEnum {
	
	ROOT("/"),
	LOGIN("mythaistar/login"),
	IMG_DIR("/assets/images/"),
	REST_ROOT("/mythaistar/services/rest"),
	CURRENT_USER("/mythaistar/services/rest/security/v1/currentuser/"),
	ORDER_SEARCH("/mythaistar/services/rest/ordermanagement/v1/order/search"),
	DISH_SEARCH("/mythaistar/services/rest/dishmanagement/v1/dish/search");
	
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