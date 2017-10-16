package com.example.core.base.user;

/**
 * DataProviderInternal container for account which contain user. On page accounts are listed in account selector. It
 * should always contain basic information like name, number and type.
 * 
 * @author
 *
 */
public class AccountData {

	private String name;
	private String number;
	private String type;

	public AccountData(String type, String number) {
		this.type = type;
		this.number = number;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

}
