package com.capgemini.mrchecker.selenium.mts;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(
		features = "src/test/java/com/capgemini/mrchecker/cucumber/features/selenium/mts",
		tags = "not @ignored",
		glue = { "com.capgemini.mrchecker.cucumber.hooks", "com.capgemini.mrchecker.selenium.mts.cucumber.stepdefs" },
		plugin = { "pretty", "html:target/cucumber-reports/cucumber-report.html", "json:target/cucumber.json" })
public class RunCucumberTests {
}
