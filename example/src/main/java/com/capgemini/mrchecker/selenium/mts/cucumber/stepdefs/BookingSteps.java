package com.capgemini.mrchecker.selenium.mts.cucumber.stepdefs;

import static org.junit.Assert.assertTrue;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.junit.Assert;

import com.capgemini.mrchecker.selenium.mts.pages.ThaiBookPage;
import com.capgemini.mrchecker.selenium.mts.pages.ThaiConfirmBookPage;
import com.capgemini.mrchecker.test.core.utils.PageFactory;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.qameta.allure.Step;

public class BookingSteps {
	private final ThaiBookPage bookingPage = PageFactory.getPageInstance(ThaiBookPage.class);
	
	@Step("I enter valid booking data")
	@When("^I enter valid booking data$")
	public void enterValidBookingData() {
		enterValidBookingForPersons(2);
	}
	
	@Step("I do not accept the terms")
	@When("^I do not accept the terms$")
	public void doNotAcceptTerms() {
		bookingPage.acceptTerms(false);
	}
	
	@Step("I accept the terms")
	@When("^I accept the terms$")
	public void acceptTerms() {
		bookingPage.acceptTerms(true);
	}
	
	@Step("Booking a table is not possible")
	@Then("^Booking a table is not possible$")
	public void bookingNotPossible() {
		Assert.assertFalse(bookingPage.isBookingEnabled());
	}
	
	@Step("I enter valid booking information for a table for {noOfPersons} person(s)")
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
	
	@Step("I confirm the booking")
	@When("^I confirm the booking$")
	public void confirmBooking() {
		ThaiConfirmBookPage confirmBookPage = bookingPage.clickBookTable();
		confirmBookPage.confirmBookingData();
	}
	
	@Step("The table is successfully booked")
	@Then("^The table is successfully booked$")
	public void tableSuccessfullyBooked() {
		assertTrue(bookingPage.isSuccessMessageShown());
	}
	
	@Step("I change {attribute} to \"{value}\"")
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