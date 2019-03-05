package com.devonfw.mrchecker.tests.cucumber;

import org.junit.runner.RunWith;

import cucumber.api.junit.Cucumber;
import cucumber.api.java8.En;

@RunWith(Cucumber.class)
public class MainCucumberTest implements En {

  public MainCucumberTest() {
    Given("I want to write a step with precondition", () -> {
      // Write code here that turns the phrase above into concrete actions
    });

    Given("some other precondition", () -> {
        // Write code here that turns the phrase above into concrete actions
    });

    When("I complete action", () -> {
        // Write code here that turns the phrase above into concrete actions
    });

    When("some other action", () -> {
        // Write code here that turns the phrase above into concrete actions
    });

    When("yet another action", () -> {
        // Write code here that turns the phrase above into concrete actions
    });

    Then("I validate the outcomes", () -> {
        // Write code here that turns the phrase above into concrete actions
    });

    Then("check more outcomes", () -> {
        // Write code here that turns the phrase above into concrete actions
    });

    Given("I want to write a step with name{int}", (Integer int1) -> {
        // Write code here that turns the phrase above into concrete actions
    });

    When("I check for the {int} in step", (Integer int1) -> {
        // Write code here that turns the phrase above into concrete actions
    });

    Then("I verify the success in step", () -> {
        // Write code here that turns the phrase above into concrete actions
    });

    Then("I verify the Fail in step", () -> {
        // Write code here that turns the phrase above into concrete actions
    });
  }
}
