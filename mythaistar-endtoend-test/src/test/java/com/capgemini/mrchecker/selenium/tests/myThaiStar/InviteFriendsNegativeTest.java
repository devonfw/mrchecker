package com.capgemini.mrchecker.selenium.tests.myThaiStar;

import static org.junit.Assert.assertTrue;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.capgemini.mrchecker.selenium.pages.myThaiStar.BookTablePage;
import com.capgemini.mrchecker.selenium.utils.Utils;
import com.capgemini.mrchecker.test.core.BaseTest;

public class InviteFriendsNegativeTest extends BaseTest {
	
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
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		bookTablePage.clickInviteFriendsTab();
	}
	
	@Override
	public void tearDown() {
		
	}
	
	@Test
	public void Test_InviteFriendsWithWrongDate() {
		String date, name, guestName, email;
		int guestsNumber;
		
		name = "client";
		guestName = "guest";
		email = Utils.getRandomEmail(name);
		date = getYesterdayDate("MM/dd/yyyy hh:mm a");
		guestsNumber = 2;
		
		bookTablePage.enterTimeAndDateInputInvitation(date);
		bookTablePage.enterNameInputInvitation(name);
		bookTablePage.enterEmailInputInvitation(email);
		
		for (int i = 0; i < guestsNumber; i++) {
			bookTablePage.enterInvitationEmailInput(Utils.getRandomEmail(guestName));
		}
		
		bookTablePage.clickAcceptTermsCheckboxInvitation();
		
		assertTrue("Test failed: Friends invited", !bookTablePage.isInviteFriendsButtonPresent());
	}
	
	@Test
	public void Test_InviteFriendsWithWrongName() {
		String date, name, guestName, email;
		int guestsNumber;
		
		name = "Words_Destroyer_98";
		guestName = "guest";
		email = Utils.getRandomEmail(name);
		date = Utils.getTomorrowDate("MM/dd/yyyy hh:mm a");
		guestsNumber = 2;
		
		bookTablePage.enterTimeAndDateInputInvitation(date);
		bookTablePage.enterNameInputInvitation(name);
		bookTablePage.enterEmailInputInvitation(email);
		
		for (int i = 0; i < guestsNumber; i++) {
			bookTablePage.enterInvitationEmailInput(Utils.getRandomEmail(guestName));
		}
		
		bookTablePage.clickAcceptTermsCheckboxInvitation();
		
		assertTrue("Test failed: Friends invited", !bookTablePage.isInviteFriendsButtonPresent());
	}
	
	@Test
	public void Test_InviteFriendsWithWrongEmail() {
		String date, name, guestName, email;
		int guestsNumber;
		
		name = "client";
		guestName = "guest";
		email = "client@email";
		date = Utils.getTomorrowDate("MM/dd/yyyy hh:mm a");
		guestsNumber = 2;
		
		bookTablePage.enterTimeAndDateInputInvitation(date);
		bookTablePage.enterNameInputInvitation(name);
		bookTablePage.enterEmailInputInvitation(email);
		
		for (int i = 0; i < guestsNumber; i++) {
			bookTablePage.enterInvitationEmailInput(Utils.getRandomEmail(guestName));
		}
		
		bookTablePage.clickAcceptTermsCheckboxInvitation();
		
		assertTrue("Test failed: Friends invited", !bookTablePage.isInviteFriendsButtonPresent());
	}
	
	@Test
	public void Test_InviteFriendsWithZeroGuests() {
		String date, name, email;
		
		name = "client";
		email = Utils.getRandomEmail(name);
		date = Utils.getTomorrowDate("MM/dd/yyyy hh:mm a");
		
		bookTablePage.enterTimeAndDateInputInvitation(date);
		bookTablePage.enterNameInputInvitation(name);
		bookTablePage.enterEmailInputInvitation(email);
		
		bookTablePage.clickAcceptTermsCheckboxInvitation();
		
		assertTrue("Test failed: Friends invited", !bookTablePage.isInviteFriendsButtonPresent());
	}
	
	@Test
	public void Test_InviteFriendsWithWrongGuestEmail() {
		String date, name, guestEmail, email;
		
		name = "client";
		guestEmail = "guest@email";
		email = Utils.getRandomEmail(name);
		date = Utils.getTomorrowDate("MM/dd/yyyy hh:mm a");
		
		bookTablePage.enterTimeAndDateInputInvitation(date);
		bookTablePage.enterNameInputInvitation(name);
		bookTablePage.enterEmailInputInvitation(email);
		bookTablePage.enterInvitationEmailInput(guestEmail);
		
		bookTablePage.clickAcceptTermsCheckboxInvitation();
		
		assertTrue("Test failed: Friends invited", !bookTablePage.isInviteFriendsButtonPresent());
	}
	
	private String getYesterdayDate(String format) {
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DAY_OF_YEAR, -1);
		
		return new SimpleDateFormat(format, Locale.ENGLISH).format(calendar.getTime());
	}
}
