package com.capgemini.mrchecker.selenium.tests.myThaiStar;

import static org.junit.Assert.assertTrue;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import com.capgemini.mrchecker.selenium.pages.myThaiStar.BookTablePage;
import com.capgemini.mrchecker.selenium.utils.Utils;
import com.capgemini.mrchecker.test.core.BaseTest;

@Category(Tests1.class)
public class BookTableNegativeTest extends BaseTest {
	private static BookTablePage bookTablePage = new BookTablePage();
	
	@BeforeClass
	public static void setUpBeforeClass() {
		
	}
	
	@AfterClass
	public static void tearDownAfterClass() {
		
	}
	
	@Override
	public void setUp() {
		bookTablePage.load();
		
	}
	
	@Override
	public void tearDown() {
		
	}
	
	@Test
	public void Test_BookTableWithWrongDate() {
		String date, name, email;
		int guestsNumber;
		
		name = "client";
		email = Utils.getRandomEmail(name);
		date = getYesterdayDate("MM/dd/yyyy hh:mm a");
		guestsNumber = Utils.getRandom1toMax(8);
		String guests = "" + guestsNumber;
		
		bookTablePage.enterBookingData(date, name, email, guests);
		
		assertTrue("Test failed: Table booked", !bookTablePage.isBookTableButtonPresent());
	}
	
	@Deprecated
	public void Test_BookTableWithWrongName() {
		String date, name, email;
		int guestsNumber;
		
		name = "Words_Destroyer_98";
		email = Utils.getRandomEmail(name);
		date = Utils.getTomorrowDate("MM/dd/yyyy hh:mm a");
		guestsNumber = Utils.getRandom1toMax(8);
		String guests = "" + guestsNumber;
		
		bookTablePage.enterBookingData(date, name, email, guests);
		
		assertTrue("Test failed: Table booked", !bookTablePage.isBookTableButtonPresent());
	}
	
	@Test
	public void Test_BookTableWithWrongEmail() {
		String date, name, email;
		int guestsNumber;
		
		name = "client";
		email = "client@email";
		date = Utils.getTomorrowDate("MM/dd/yyyy hh:mm a");
		guestsNumber = Utils.getRandom1toMax(8);
		String guests = "" + guestsNumber;
		
		bookTablePage.enterBookingData(date, name, email, guests);
		
		assertTrue("Test failed: Table booked", !bookTablePage.isBookTableButtonPresent());
	}
	
	@Test
	public void Test_BookTableWithNegativeGuestNumber() {
		String date, name, email;
		int guestsNumber;
		
		name = "client";
		email = Utils.getRandomEmail(name);
		date = Utils.getTomorrowDate("MM/dd/yyyy hh:mm a");
		guestsNumber = -Utils.getRandom1toMax(8);
		String guests = "" + guestsNumber;
		
		bookTablePage.enterBookingData(date, name, email, guests);
		
		assertTrue("Test failed: Table booked", !bookTablePage.isBookTableButtonPresent());
	}
	
	@Deprecated
	public void Test_BookTableWithNonintegerGuestNumber() {
		String date, name, email;
		double guestsNumber;
		
		name = "client";
		email = Utils.getRandomEmail(name);
		date = Utils.getTomorrowDate("MM/dd/yyyy hh:mm a");
		guestsNumber = 3.14;
		String guests = "" + guestsNumber;
		
		bookTablePage.enterBookingData(date, name, email, guests);
		
		assertTrue("Test failed: Table booked", !bookTablePage.isBookTableButtonPresent());
	}
	
	private String getYesterdayDate(String format) {
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DAY_OF_YEAR, -1);
		
		return new SimpleDateFormat(format, Locale.ENGLISH).format(calendar.getTime());
	}
}
