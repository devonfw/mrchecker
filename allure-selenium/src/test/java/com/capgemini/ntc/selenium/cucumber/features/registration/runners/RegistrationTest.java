package com.example.selenium.tests.cucumber.features.registration.runners;

import org.junit.runner.RunWith;

import com.capgemini.ntc.test.core.tests.core.BaseTest;

import cucumber.api.junit.Cucumber;
import cucumber.api.CucumberOptions;
/**
 * Created by LKURZAJ on 09.03.2017.
 */
@RunWith(Cucumber.class)
@CucumberOptions(strict = true,
            features = "src/test/java/com/example/selenium/tests/cucumber/features/registration/gherkins/registration.feature",
			glue = "com.example.selenium.tests.cucumber.features.registration.stepdefs",
            plugin = "json:target/report/registration.json")
public class RegistrationTest extends BaseTest {

    @Override
    public void setUp() {

    }

    @Override
    public void tearDown() {}
}
