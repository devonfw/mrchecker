package com.capgemini.mrchecker.selenium.myThaiStar;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.capgemini.mrchecker.selenium.pages.myThaiStar.BookTablePage;
import com.capgemini.mrchecker.selenium.pages.myThaiStar.ConfirmBookPage;
import com.capgemini.mrchecker.selenium.pages.utils.Utils;
import com.capgemini.mrchecker.test.core.BaseTest;

public class BookTableTest extends BaseTest {
	private static BookTablePage bookTablePage = new BookTablePage();
	
	@BeforeClass
	public static void setUpBeforeClass() {
		bookTablePage.load();
	}
	
	@AfterClass
	public static void tearDownAfterClass() {
		
	}
	
	@Override
	public void setUp() {
		if (!bookTablePage.isLoaded()) {
			bookTablePage.load();
		}
	}
	
	@Override
	public void tearDown() {
		bookTablePage.load();
	}
	
	@Test
	public void Test_BookTableWithProperData() {
		String date, name, email;
		int guestsNumber;
		
		name = "client";
		email = Utils.getRandomEmail(name);
		date = Utils.getDate("MM/dd/yyyy hh:mm a", Utils.getRandom1toMax(30));
		guestsNumber = Utils.getRandom1toMax(8);
		
		ConfirmBookPage confirmBookPage = bookTablePage.enterBookingDataAndBookTable(date, name, email, Integer.toString(guestsNumber));
		confirmBookPage.clickConfirmBookingButton();
		
		assertTrue("Table not booked after entering proper data", bookTablePage.isConfirmationDialogDisplayed());
	}
	
	@Test
	public void Test_BookTableWithWrongDate() {
		String date, name, email;
		int guestsNumber;
		
		name = "client";
		email = Utils.getRandomEmail(name);
		date = Utils.getDate("MM/dd/yyyy hh:mm a", -1);
		guestsNumber = Utils.getRandom1toMax(8);
		
		bookTablePage.enterBookingData(date, name, email, Integer.toString(guestsNumber));
		
		assertFalse("Book table button clickable after entering wrong date", bookTablePage.isBookTableButtonClickable());
	}
	
	@Test
	public void Test_BookTableWithWrongHour() {
		String date, name, email;
		int guestsNumber;
		
		name = "client";
		email = Utils.getRandomEmail(name);
		date = Utils.getDate("MM/dd/yyyy hh:mm a", 1);
		guestsNumber = Utils.getRandom1toMax(8);
		
		date = date.replaceFirst("[0-9]{2}:[0-9]{2}", "25:00");
		
		bookTablePage.enterBookingData(date, name, email, Integer.toString(guestsNumber));
		
		assertFalse("Book table button clickable after entering wrong hour", bookTablePage.isBookTableButtonClickable());
	}
	
	@Test
	public void Test_BookTableWithEmptyName() {
		String date, name, email;
		int guestsNumber;
		
		name = " ";
		email = Utils.getRandomEmail(name);
		date = Utils.getDate("MM/dd/yyyy hh:mm a", 1);
		guestsNumber = Utils.getRandom1toMax(8);
		
		bookTablePage.enterBookingData(date, name, email, Integer.toString(guestsNumber));
		
		assertFalse("Book table button clickable after entering empty name", bookTablePage.isBookTableButtonClickable());
	}
	
	@Test
	public void Test_BookTableWithWrongEmail() {
		String date, name, email;
		int guestsNumber;
		
		name = "client";
		email = "client@email";
		date = Utils.getDate("MM/dd/yyyy hh:mm a", 1);
		guestsNumber = Utils.getRandom1toMax(8);
		
		bookTablePage.enterBookingData(date, name, email, Integer.toString(guestsNumber));
		
		assertFalse("Book table button clickable after entering wrong email", bookTablePage.isBookTableButtonClickable());
	}
	
	@Test
	public void Test_BookTableWithTooManyGuests() {
		String date, name, email;
		int guestsNumber;
		
		name = "client";
		email = Utils.getRandomEmail(name);
		date = Utils.getDate("MM/dd/yyyy hh:mm a", 1);
		guestsNumber = Utils.getRandom1toMax(90) + 8;
		
		bookTablePage.enterBookingData(date, name, email, Integer.toString(guestsNumber));
		
		assertFalse("Book table button clickable after entering too big guests amount", bookTablePage.isBookTableButtonClickable());
	}
	
	@Test
	public void Test_BookTableWithZeroGuests() {
		String date, name, email;
		int guestsNumber;
		
		name = "client";
		email = Utils.getRandomEmail(name);
		date = Utils.getDate("MM/dd/yyyy hh:mm a", 1);
		guestsNumber = 0;
		
		bookTablePage.enterBookingData(date, name, email, Integer.toString(guestsNumber));
		
		assertFalse("Book table button clickable after entering zero guests amount", bookTablePage.isBookTableButtonClickable());
	}
	
	@Test
	public void Test_BookTableWithNegativeGuestNumber() {
		String date, name, email;
		int guestsNumber;
		
		name = "client";
		email = Utils.getRandomEmail(name);
		date = Utils.getDate("MM/dd/yyyy hh:mm a", 1);
		guestsNumber = Utils.getRandom1toMax(8) * (-1);
		
		bookTablePage.enterBookingData(date, name, email, Integer.toString(guestsNumber));
		
		assertFalse("Book table button clickable after entering negative guests amount", bookTablePage.isBookTableButtonClickable());
	}
	
	@Test
	public void Test_BookTableWithNonIntegerGuestNumber() {
		String date, name, email, guestsNumber;
		
		name = "client";
		email = Utils.getRandomEmail(name);
		date = Utils.getDate("MM/dd/yyyy hh:mm a", 1);
		guestsNumber = "3.14";
		
		bookTablePage.enterBookingData(date, name, email, guestsNumber);
		
		assertFalse("Book table button clickable after entering non integer guests amount", bookTablePage.isBookTableButtonClickable());
	}
	
	@Test
	public void Test_BookTableWithTermsUnaccepted() {
		String date, name, email;
		int guestsNumber;
		
		name = "client";
		email = Utils.getRandomEmail(name);
		date = Utils.getDate("MM/dd/yyyy hh:mm a", 1);
		guestsNumber = Utils.getRandom1toMax(8);
		
		bookTablePage.enterTimeAndDateInputBooking(date);
		bookTablePage.enterNameInputBooking(name);
		bookTablePage.enterEmailInputBooking(email);
		bookTablePage.enterGuestsNumberInput(Integer.toString(guestsNumber));
		
		assertFalse("Book table button clickable when accept terms checkbox unchecked", bookTablePage.isBookTableButtonClickable());
	}
	
}
