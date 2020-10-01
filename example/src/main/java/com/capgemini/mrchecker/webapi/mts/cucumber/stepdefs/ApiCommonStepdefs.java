package com.capgemini.mrchecker.webapi.mts.cucumber.stepdefs;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.capgemini.mrchecker.webapi.mts.common.Context;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.qameta.allure.Step;

public class ApiCommonStepdefs {
	private final Context context = Context.getInstance();
	
	@Step("Checking the response code to be {httpCode}")
	@Then("The response code is {string}")
	public void theResponseCodeIs(String httpCode) {
		assertThat(context.getResponseCode(), is(equalTo(Integer.parseInt(httpCode))));
	}
	
	@Step("Checking a header {header} to match {regex}")
	@And("The {string} header matches {string}")
	public void theHeaderMatches(String header, String regex) {
		String headerValue = context.getResponseHeaderValue(header);
		Matcher matcher = Pattern.compile(regex)
				.matcher(headerValue);
		
		assertThat(matcher.matches(), is(equalTo(true)));
	}
	
	@Step("Checking a body item {key} to be of value {expected}")
	@And("The body item {string} is {string}")
	public void theBodyItemIs(String key, String expected) throws Exception {
		String actual = context.getBodyKeyValue(key);
		
		assertThat(actual, is(equalTo(expected)));
	}
}
