package com.capgemini.mrchecker.selenium.mts.cucumber.stepdefs;

import static org.junit.Assert.assertTrue;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.junit.Assert;

import com.capgemini.mrchecker.selenium.mts.pages.ThaiBookPage;
import com.capgemini.mrchecker.selenium.mts.pages.ThaiConfirmBookPage;
import com.capgemini.mrchecker.selenium.mts.pages.ThaiHomePage;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class BookingSteps {
	private final ThaiHomePage	homePage	= new ThaiHomePage();
	private ThaiBookPage		bookingPage;
	
	@Given("^the booking section has been opened$")
	public void bookingHasBeenOpened() {
		bookingPage = this.homePage.clickBookTable();
	}
	
	@When("^I enter valid booking data$")
	public void enterValidBookingData() {
		enterValidBookingForPersons(2);
	}
	
	@When("^I do not accept the terms$")
	public void doNotAcceptTerms() {
		bookingPage.acceptTerms(false);
	}
	
	@When("^I accept the terms$")
	public void acceptTerms() {
		bookingPage.acceptTerms(true);
	}
	
	@Then("^Booking a table is not possible$")
	public void bookingNotPossible() {
		Assert.assertFalse(bookingPage.isBookingEnabled());
	}
	
	@When("^I enter valid booking information for a table for (\\d+) persons$")
	public void enterValidBookingForPersons(int noOfPersons) {
		String dateTomorrow = LocalDate.now()
				.plusDays(1)
				.format(DateTimeFormatter.ofPattern("MM/dd/yyyy"));
		bookingPage.enterTimeAndDate(dateTomorrow + ", 08:00 PM");
		bookingPage.enterEmail("donald@mytest.de");
		bookingPage.enterName("Donald D. Tester");
		bookingPage.enterGuests(noOfPersons);
	}
	
	@When("^I confirm the booking$")
	public void confirmBooking() {
		ThaiConfirmBookPage confirmBookPage = bookingPage.clickBookTable();
		confirmBookPage.confirmBookingData();
	}
	
	@Then("^The table is successfully booked$")
	public void tableSuccessfullyBooked() {
		assertTrue(bookingPage.isSuccessMessageShown());
	}
	
	@When("^I change (email|name|persons) to (.*)$")
	public void changeEmail(String attribute, String value) {
		switch (attribute) {
			case "email":
				bookingPage.enterEmail(value);
				break;
			case "name":
				bookingPage.enterName(value);
				break;
			case "persons":
				bookingPage.enterGuests(value);
				break;
			default:
				throw new IllegalArgumentException("Unknown attribute " + attribute);
		}
	}
}