package com.capgemini.mrchecker.selenium.myThaiStar;

import static org.junit.Assert.assertTrue;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import com.capgemini.mrchecker.selenium.pages.myThaiStar.BookTablePage;
import com.capgemini.mrchecker.selenium.pages.myThaiStar.ConfirmBookPage;
import com.capgemini.mrchecker.selenium.pages.utils.Utils;
import com.capgemini.mrchecker.test.core.BaseTest;

@Category(Tests0.class)
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
		
	}
	
	@Test
	public void Test_BookTableAndCheckConfirmation() {
		
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DAY_OF_YEAR, 1);
		
		String date = new SimpleDateFormat("MM/dd/yyyy hh:mm a", Locale.ENGLISH).format(calendar.getTime());
		String name = "Smith";
		String email = "smith@somemail.com";
		int guestsNumber = 3;
		String guests = "" + guestsNumber;
		
		ConfirmBookPage confirmBookPage = bookTablePage.enterBookingDataAndBookTable(date, name, email, guests);
		confirmBookPage.clickConfirmBookingButton();
		
		assertTrue("Test failed: Table not booked", bookTablePage.isConfirmationDialogDisplayed());
	}
	
	@Test
	public void Test_BookTableWithRandomValues() {
		String date, name, email;
		int guestsNumber;
		
		name = "client";
		email = Utils.getRandomEmail(name);
		date = Utils.getTomorrowDate("MM/dd/yyyy hh:mm a");
		guestsNumber = Utils.getRandom1toMax(8);
		String guests = "" + guestsNumber;
		
		ConfirmBookPage confirmBookPage = bookTablePage.enterBookingDataAndBookTable(date, name, email, guests);
		confirmBookPage.clickConfirmBookingButton();
		
		assertTrue("Test failed: Table not booked", bookTablePage.isConfirmationDialogDisplayed());
	}
}
