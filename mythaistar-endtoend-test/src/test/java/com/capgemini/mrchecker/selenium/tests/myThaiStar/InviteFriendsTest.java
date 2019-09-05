package com.capgemini.mrchecker.selenium.tests.myThaiStar;

import static org.junit.Assert.assertTrue;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.capgemini.mrchecker.selenium.pages.myThaiStar.BookTablePage;
import com.capgemini.mrchecker.selenium.pages.myThaiStar.ConfirmInvitationPage;
import com.capgemini.mrchecker.selenium.utils.Utils;
import com.capgemini.mrchecker.test.core.BaseTest;

public class InviteFriendsTest extends BaseTest {
	
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
	public void Test_InviteFriendsAndCheckConfirmation() {
		String date, name, guestName, email;
		
		name = "client";
		guestName = "guest";
		email = Utils.getRandomEmail(name);
		date = Utils.getTomorrowDate("MM/dd/yyyy hh:mm a");
		
		bookTablePage.clickInviteFriendsTab();
		bookTablePage.enterTimeAndDateInputInvitation(date);
		bookTablePage.enterNameInputInvitation(name);
		bookTablePage.enterEmailInputInvitation(email);
		bookTablePage.enterInvitationEmailInput(Utils.getRandomEmail(guestName));
		
		bookTablePage.clickAcceptTermsCheckboxInvitation();
		bookTablePage.clickInviteFriendsButton();
		
		ConfirmInvitationPage confirmInvitationPage = new ConfirmInvitationPage();
		confirmInvitationPage.clickConfirmBookingButton();
		
		assertTrue("Test failed: Friends not invited", bookTablePage.isConfirmationDialogDisplayed());
	}
}
