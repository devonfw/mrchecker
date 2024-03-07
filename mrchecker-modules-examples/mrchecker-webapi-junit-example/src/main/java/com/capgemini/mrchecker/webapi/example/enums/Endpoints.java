package com.capgemini.mrchecker.webapi.example.enums;

import java.util.Formatter;

public enum Endpoints {
	// Based on swagger https://demoqa.com/swagger/
	
	ACCOUNT_CONTROLLER_ENDPOINT("/Account/v1"),
	ACCOUNT_AUTHORIZED_ENDPOINT("/Account/v1/Authorized"),
	ACCOUNT_GENERATE_TOKEN_ENDPOINT("/Account/v1/GenerateToken"),
	ACCOUNT_ADD_USER_ENDPOINT("/Account/v1/User"),
	ACCOUNT_DELETE_USER_ENDPOINT("/Account/v1/User/%s"),
	ACCOUNT_GET_USER_ENDPOINT("/Account/v1/User/%s"),
	ACCOUNT_LOGIN_ENDPOINT("/Account/v1/Login"),
	BOOKSTORE_CONTROLLER_ENDPOINT("/BookStore/v1"),
	BOOKSTORE_GET_ALL_BOOKS_ENDPOINT("/BookStore/v1/Books"),
	BOOKSTORE_ADD_LIST_OF_BOOKS_ENDPOINT("/BookStore/v1/Books"),
	BOOKSTORE_REMOVE_LIST_OF_BOOKS_FOR_USER_ENDPOINT("/BookStore/v1/Books?UserId=%s"),
	BOOKSTORE_GET_BOOK_DETAILS_ENDPOINT("/BookStore/v1/Book?ISBN=%s"),
	BOOKSTORE_REMOVE_BOOK_ENDPOINT("/BookStore/v1/Book"),
	BOOKSTORE_UPDATE_BOOKS_LIST_ENDPOINT("/BookStore/v1/Books/%s");
	
	private final String endpoint;
	
	Endpoints(String endpoint) {
		this.endpoint = endpoint;
	}
	
	@Override
	public String toString() {
		return endpoint != null ? endpoint : super.toString();
	}
	
	public String getEndpoint(String... parameters) {
		StringBuilder sb = new StringBuilder();
		try (Formatter formatter = new Formatter(sb)) {
			return formatter.format(endpoint, parameters)
					.toString();
		}
	}
}
