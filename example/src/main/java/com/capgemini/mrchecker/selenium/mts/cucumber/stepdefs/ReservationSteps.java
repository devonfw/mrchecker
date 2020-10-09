package com.capgemini.mrchecker.selenium.mts.cucumber.stepdefs;

import static org.junit.Assert.assertFalse;

import com.capgemini.mrchecker.selenium.mts.pages.ThaiReservationsPage;
import com.capgemini.mrchecker.selenium.mts.pages.ThaiWaiterPage;
import com.capgemini.mrchecker.test.core.utils.PageFactory;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.qameta.allure.Step;

public class ReservationSteps {
	final private ThaiWaiterPage waiterPage = PageFactory.getPageInstance(ThaiWaiterPage.class);
	
	@Step("I can see a reservation for {emailAddress}")
	@Then("^I can see a reservation for \"([^\"]*)\"$")
	public void reservationExistsFor(String emailAddress) {
		ThaiReservationsPage thaiReservationsPage = this.waiterPage.switchToReservations();
		assertFalse(thaiReservationsPage.getReservationsForEmail(emailAddress)
				.isEmpty());
	}
	
	@Step("There is a reservation for {email}")
	@Given("There is a reservation for {string}")
	public void thereIsAReservationFor(String email) {
		// TODO: implement DB insert
	}
}