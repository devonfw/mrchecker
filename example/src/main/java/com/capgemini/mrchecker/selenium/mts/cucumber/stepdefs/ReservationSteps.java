package com.capgemini.mrchecker.selenium.mts.cucumber.stepdefs;

import static org.junit.Assert.assertFalse;

import com.capgemini.mrchecker.selenium.mts.pages.ThaiReservationsPage;
import com.capgemini.mrchecker.selenium.mts.pages.ThaiWaiterPage;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;

public class ReservationSteps {
	final private ThaiWaiterPage waiterPage = new ThaiWaiterPage();
	
	@Then("^I can see a reservation for \"([^\"]*)\"$")
	public void reservationExistsFor(String emailAddress) {
		ThaiReservationsPage thaiReservationsPage = this.waiterPage.switchToReservations();
		assertFalse(thaiReservationsPage.getReservationsForEmail(emailAddress)
				.isEmpty());
	}
	
	@Given("There is a reservation for {string}")
	public void thereIsAReservationFor(String email) {
		// TODO: implement DB insert
	}
}