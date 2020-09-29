package com.capgemini.mrchecker.webapi.mts.cucumber.stepdefs;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.capgemini.mrchecker.webapi.mts.common.Context;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;

public class ApiCommonStepdefs {
	private final Context context = Context.getInstance();
	
	@Then("The response code is {string}")
	public void theResponseCodeIs(String httpCode) {
		assertThat(context.getResponseCode(), is(equalTo(Integer.parseInt(httpCode))));
	}
	
	@And("The {string} header matches {string}")
	public void theHeaderMatches(String header, String regex) {
		String headerValue = context.getResponseHeaderValue(header);
		Matcher matcher = Pattern.compile(regex)
				.matcher(headerValue);
		
		assertThat(matcher.matches(), is(equalTo(true)));
	}
	
	@And("The body item {string} is {string}")
	public void theBodyItemIs(String key, String expected) throws Exception {
		String actual = context.getBodyKeyValue(key);
		
		assertThat(actual, is(equalTo(expected)));
	}
}
