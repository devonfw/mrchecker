package com.capgemini.mrchecker.selenium.myThaiStar;

import static org.junit.Assert.assertTrue;

import javax.annotation.concurrent.NotThreadSafe;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import com.capgemini.mrchecker.core.groupTestCases.testSuites.tags.TestsNONParallel;
import com.capgemini.mrchecker.selenium.pages.myThaiStar.BookTablePage;
import com.capgemini.mrchecker.selenium.pages.myThaiStar.ConfirmInvitationPage;
import com.capgemini.mrchecker.selenium.pages.utils.Utils;
import com.capgemini.mrchecker.test.core.BaseTest;

@NotThreadSafe
@Category({ Tests1.class, TestsNONParallel.class })
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
		date = Utils.getTomorrowDate("MM/dd/yyyy hh:mm a");
		guestsNumber = 3;
		
		bookTablePage.clickInviteFriendsTab();
		bookTablePage.enterTimeAndDateInputInvitation(date);
		bookTablePage.enterNameInputInvitation(name);
		bookTablePage.enterEmailInputInvitation(email);
		
		for (int i = 0; i < guestsNumber; i++) {
			bookTablePage.enterInvitationEmailInput(Utils.getRandomEmail(guestName));
		}
		
		bookTablePage.clickAcceptTermsCheckboxInvitation();
		bookTablePage.clickInviteFriendsButton();
		
		ConfirmInvitationPage confirmInvitationPage = new ConfirmInvitationPage();
		confirmInvitationPage.clickConfirmBookingButton();
		
		assertTrue("Test failed: Friends not invited", bookTablePage.isConfirmationDialogDisplayed());
	}
}
