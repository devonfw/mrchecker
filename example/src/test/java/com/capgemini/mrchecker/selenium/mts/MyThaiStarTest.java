package com.capgemini.mrchecker.selenium.mts;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;

import java.util.HashMap;
import java.util.List;

import org.junit.Assert;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.aggregator.AggregateWith;
import org.junit.jupiter.params.provider.CsvFileSource;

import com.capgemini.mrchecker.common.mts.data.Reservation;
import com.capgemini.mrchecker.common.mts.data.User;
import com.capgemini.mrchecker.common.mts.utils.Utils;
import com.capgemini.mrchecker.selenium.mts.datadrivem.UserAggregator;
import com.capgemini.mrchecker.selenium.mts.pages.ThaiBookPage;
import com.capgemini.mrchecker.selenium.mts.pages.ThaiConfirmBookPage;
import com.capgemini.mrchecker.selenium.mts.pages.ThaiHomePage;
import com.capgemini.mrchecker.selenium.mts.pages.ThaiLoginPage;
import com.capgemini.mrchecker.selenium.mts.pages.ThaiMenuPage;
import com.capgemini.mrchecker.selenium.mts.pages.ThaiReservationsPage;
import com.capgemini.mrchecker.selenium.mts.pages.ThaiSummaryPage;
import com.capgemini.mrchecker.selenium.mts.pages.ThaiWaiterPage;
import com.capgemini.mrchecker.test.core.BaseTest;
import com.capgemini.mrchecker.test.core.utils.PageFactory;

@Tag("MTS_GUI")
public class MyThaiStarTest extends BaseTest {
	
	private final ThaiHomePage	myThaiStarHome	= PageFactory.getPageInstance(ThaiHomePage.class);
	private final ThaiLoginPage	loginPage		= PageFactory.getPageInstance(ThaiLoginPage.class);
	private final ThaiMenuPage	menuPage		= PageFactory.getPageInstance(ThaiMenuPage.class);
	
	@Override
	public void setUp() {
		myThaiStarHome.load();
		logOut();
	}
	
	@ParameterizedTest
	@CsvFileSource(resources = "/datadriven/test_users.csv", numLinesToSkip = 1)
	public void Test_loginAndLogOut(@AggregateWith(UserAggregator.class) User user) {
		login(user);
		logOut();
	}
	
	@Test
	public void Test_loginFake() {
		User fakeUser = new User("fakeUser", "fakePassword");
		myThaiStarHome.clickLogInButton();
		loginPage.enterCredentials(fakeUser.getUsername(), fakeUser.getPassword());
		assertThat("User " + fakeUser.getUsername() + " logged",
				myThaiStarHome.isUserLogged(fakeUser.getUsername()), is(equalTo(false)));
	}
	
	@CsvFileSource(resources = "/datadriven/test_users.csv", numLinesToSkip = 1)
	public void Test_bookTable(@AggregateWith(UserAggregator.class) User user) {
		String fakeEmail = Utils.getRandomEmail(user.getUsername());
		String date = Utils.getTomorrowDate();
		int guest = Utils.getRandom1toMax(8);
		Reservation reservation = new Reservation(date, user.getUsername(), fakeEmail, guest);
		User waiter = new User("waiter", "waiter");
		
		login(user);
		bookTable(reservation);
		logOut();
		login(waiter);
		verifyBooking(reservation);
		logOut();
	}
	
	@Test
	public void Test_orderMenu() {
		String bookingId = "CB_20170510_123502655Z";
		myThaiStarHome.clickMenuButton();
		ThaiSummaryPage summaryPage = menuPage.clickFirstMenu();
		summaryPage.orderMenu(bookingId);
	}
	
	private void login(User user) {
		myThaiStarHome.clickLogInButton();
		loginPage.enterCredentials(user.getUsername(), user.getPassword());
		assertThat("User " + user.getUsername() + " not logged",
				myThaiStarHome.isUserLogged(user.getUsername()), is(equalTo(true)));
	}
	
	private void logOut() {
		if (myThaiStarHome.isUserLogged()) {
			myThaiStarHome.clickLogOutButton();
		}
		assertThat("Some user logged", this.myThaiStarHome.isUserLogged(), is(equalTo(false)));
	}
	
	private void bookTable(Reservation reservation) {
		ThaiBookPage bookPage = myThaiStarHome.clickBookTable();
		ThaiConfirmBookPage confirmPage = bookPage.enterBookingData(reservation);
		confirmPage.confirmBookingData();
		bookPage.checkConfirmationDialog();
	}
	
	private void verifyBooking(Reservation reservation) {
		ThaiWaiterPage myWaiterPage = new ThaiWaiterPage();
		ThaiReservationsPage myReservationsPage = myWaiterPage.switchToReservations();
		HashMap<String, List<Reservation>> reservations = myReservationsPage.searchDatesByEmail(reservation.getEmail());
		Assert.assertTrue("Booking not found", reservations.containsKey(reservation.getDate()));
		List<Reservation> reservationsForDate = reservations.get(reservation.getDate());
		Assert.assertFalse("Booking not found", reservationsForDate.isEmpty());
	}
}
