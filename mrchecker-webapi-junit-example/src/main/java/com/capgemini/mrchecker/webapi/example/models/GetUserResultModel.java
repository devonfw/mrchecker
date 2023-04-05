package com.capgemini.mrchecker.webapi.example.models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GetUserResultModel {
	private String		userId;
	private String		username;
	private BookModel[]	books;
}
