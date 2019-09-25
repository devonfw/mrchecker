package com.capgemini.mrchecker.selenium.tests.myThaiStar;

import static org.junit.Assert.assertTrue;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.capgemini.mrchecker.selenium.pages.myThaiStar.BookTablePage;
import com.capgemini.mrchecker.selenium.pages.myThaiStar.ConfirmInvitationPage;
import com.capgemini.mrchecker.selenium.pages.myThaiStar.InviteFriendsPage;
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
		int guestsNumber;
		
		name = "client";
		guestName = "guest";
		email = Utils.getRandomEmail(name);
		date = Utils.getDate("MM/dd/yyyy hh:mm a", 1);
		guestsNumber = Utils.getRandom1toMax(8);
		
		InviteFriendsPage inviteFriendsPage = bookTablePage.clickInviteFriendsTab();
		inviteFriendsPage.enterTimeAndDateInputInvitation(date);
		inviteFriendsPage.enterNameInputInvitation(name);
		inviteFriendsPage.enterEmailInputInvitation(email);
		
		for (int i = 0; i < guestsNumber; i++) {
			inviteFriendsPage.enterInvitationEmailInput(Utils.getRandomEmail(guestName));
		}
		
		inviteFriendsPage.clickAcceptTermsCheckboxInvitation();
		inviteFriendsPage.clickInviteFriendsButton();
		
		ConfirmInvitationPage confirmInvitationPage = new ConfirmInvitationPage();
		confirmInvitationPage.clickConfirmBookingButton();
		
		assertTrue("Test failed: Friends not invited", inviteFriendsPage.isConfirmationDialogDisplayed());
	}
}
