package com.capgemini.mrchecker.webapi.example.controllers;

import static com.capgemini.mrchecker.webapi.example.env.GetEnvironmentParam.DEMO_QA_API_URL;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.capgemini.mrchecker.webapi.example.base.BaseEndpointWebAPI;
import com.capgemini.mrchecker.webapi.example.enums.Endpoints;
import com.capgemini.mrchecker.webapi.example.models.*;

import io.restassured.response.ExtractableResponse;

public class BookStoreEndpointsController extends BaseEndpointWebAPI {
	@Override
	public String getEndpoint() {
		return Endpoints.BOOKSTORE_CONTROLLER_ENDPOINT.getEndpoint();
	}
	
	public AllBooksModel getAllBooks() {
		AllBooksModel books = getFromHost(DEMO_QA_API_URL.getValue(), Endpoints.BOOKSTORE_GET_ALL_BOOKS_ENDPOINT.getEndpoint())
				.as(AllBooksModel.class);
		assertNotNull(books, "No response was returned");
		return books;
	}
	
	public BookModel getBookByISBN(String isbn) {
		BookModel bookDetails = getFromHost(DEMO_QA_API_URL.getValue(), Endpoints.BOOKSTORE_GET_BOOK_DETAILS_ENDPOINT.getEndpoint(isbn))
				.as(BookModel.class);
		assertNotNull(bookDetails, "No response was returned");
		return bookDetails;
	}
	
	public AddedListOfBooksModel postAddBookToUser(String userID, String isbn) {
		CollectionOfIsbnModel isbnCollection = new CollectionOfIsbnModel() {
			{
				setIsbn(isbn);
			}
		};
		
		AddListOfBooksModel booksList = new AddListOfBooksModel() {
			{
				setUserId(userID);
				setCollectionOfIsbns(
						new CollectionOfIsbnModel[] { isbnCollection });
			}
		};
		
		AddedListOfBooksModel response = postToHost(DEMO_QA_API_URL.getValue(), Endpoints.BOOKSTORE_ADD_LIST_OF_BOOKS_ENDPOINT.getEndpoint(), booksList, 201)
				.as(AddedListOfBooksModel.class);
		assertNotNull(response, "No response was returned");
		return response;
	}
	
	public void deleteBooksFromUserCollection(String userID) {
		ExtractableResponse response = deleteFromHost(DEMO_QA_API_URL.getValue(), Endpoints.BOOKSTORE_REMOVE_LIST_OF_BOOKS_FOR_USER_ENDPOINT.getEndpoint(userID), 204);
		assertNotNull(response, "No response was returned");
	}
}
