package com.capgemini.mrchecker.webapi.example;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import com.capgemini.mrchecker.test.core.utils.PageFactory;
import com.capgemini.mrchecker.test.core.utils.StepLogger;
import com.capgemini.mrchecker.webapi.example.base.BaseTestWebAPI;
import com.capgemini.mrchecker.webapi.example.controllers.AccountEndpointsController;
import com.capgemini.mrchecker.webapi.example.controllers.BookStoreEndpointsController;
import com.capgemini.mrchecker.webapi.example.env.User;
import com.capgemini.mrchecker.webapi.example.models.*;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Story;
import io.qameta.allure.TmsLink;

public class BookStoreTests extends BaseTestWebAPI {
	private static final BookStoreEndpointsController	bookStoreEndpointsController	= PageFactory.getPageInstance(BookStoreEndpointsController.class);
	private static final AccountEndpointsController		accountEndpointsController		= PageFactory.getPageInstance(AccountEndpointsController.class);
	
	private static String userId;
	
	@Override
	public void tearDownTest() {
		StepLogger.step("This is example step inside tearDownTest()");
	}
	
	@Override
	public void setUpTest() {
		StepLogger.step("This is example step inside setUpTest()");
	}
	
	@AfterAll
	public static void cleanUpAfterAllTests() {
		bookStoreEndpointsController.deleteBooksFromUserCollection(userId);
	}
	
	// Allure annotations - will have impact on Allure report
	@TmsLink("Test Management System ID")
	@Epic("Epic name")
	@Story("Story name #1")
	@Description("Test case description")
	// JUnit annotations
	@Test
	@Tag("JUnit_Tag")
	@DisplayName("Example test - Demo QA Bookstore API #1")
	public void getAllBooksFromBookstore_test() {
		AllBooksModel booksList = bookStoreEndpointsController.getAllBooks();
		assertNotNull(booksList, "No book was returned");
		BookModel[] books = booksList.getBooks();
		assertTrue(books.length > 0, "No book was returned");
		StepLogger.info("There are " + books.length
				+ " books in the store");
		
	}
	
	// Allure annotations - will have impact on Allure report
	@TmsLink("Test Management System ID")
	@Epic("Epic name")
	@Story("Story name")
	@Description("Test case description")
	// JUnit annotations
	@Test
	@Tag("JUnit_Tag")
	@DisplayName("Example test - Demo QA Bookstore API #2")
	public void getBookDetailsFromBookstore_test() {
		String bookISBN = "9781449325862";
		String bookTitle = "Git Pocket Guide";
		String bookAuthor = "Richard E. Silverman";
		
		BookModel bookDetails = bookStoreEndpointsController.getBookByISBN(bookISBN);
		assertEquals(bookDetails.getIsbn(), bookISBN, "Incorrect ISBN");
		assertEquals(bookDetails.getTitle(), bookTitle, "Incorrect title");
		assertEquals(bookDetails.getAuthor(), bookAuthor, "Incorrect author");
		StepLogger.info("Book was found in Book store");
		
	}
	
	// Allure annotations - will have impact on Allure report
	@TmsLink("Test Management System ID")
	@Epic("Epic name")
	@Story("Story name")
	@Description("Test case description")
	// JUnit annotations
	@Test
	@Tag("JUnit_Tag")
	@DisplayName("Example test - Demo QA Bookstore API #3")
	public void addBookToUserCollection_test() {
		String bookISBN = "9781449325862";
		
		UserModel userDetails = accountEndpointsController.loginUser(User.EXAMPLE_USER);
		userId = userDetails.getUserId();
		AddedListOfBooksModel addedBooks = bookStoreEndpointsController.postAddBookToUser(userDetails.getUserId(), bookISBN);
		CollectionOfIsbnModel[] books = addedBooks.getBooks();
		assertNotNull(books, "No books details were returned");
		assertTrue(books.length > 0, "Book was not added to user's collection");
		assertEquals(books[0].getIsbn(), bookISBN, "Incorrect ISBN");
		StepLogger.info("Book with ISBN " + bookISBN + " was successfully added to collection");
	}
}
