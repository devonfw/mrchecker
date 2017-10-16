package com.capgemini.ntc.selenium.tests.cucumber.features.webelements.inputtext.runners;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

import com.example.core.tests.core.BaseTest;

/**
 * Created by LKURZAJ on 23.03.2017.
 */
@RunWith(Cucumber.class)
@CucumberOptions(strict = true,
                features= "src/test/java/com/example/selenium/tests/cucumber/features/webelements/inputtext/gherkins/inputText.feature",
                glue = "com.capgemini.ntc.selenium.tests.cucumber.features.webelements.inputtext.stepdefs",
                plugin = "json:target/report/webelements/inputText.json")
public class InputTextTest extends BaseTest {

    @Override
    public void setUp() {}

    @Override
    public void tearDown() {}
}
