package com.capgemini.mrchecker.webapi.example.models;

import lombok.Getter;

@Getter
public class UserModel {
	private String	userId;
	private String	username;
	private String	password;
	private String	token;
	private String	expires;
	private String	created_date;
	private Boolean	isActive;
}
