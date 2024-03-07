package com.capgemini.mrchecker.webapi.example.models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddListOfBooksModel {
	private String					userId;
	private CollectionOfIsbnModel[]	collectionOfIsbns;
	
}
