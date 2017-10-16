package com.example.selenium.tests.cucumber.features.webelements.label.runners;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

import com.capgemini.ntc.test.core.tests.core.BaseTest;

/**
 * Created by LKURZAJ on 27.03.2017.
 */

@RunWith(Cucumber.class)
@CucumberOptions(strict = true,
                features = "src/test/java/com/example/selenium/tests/cucumber/features/webelements/label/gherkins/label.feature",
                glue = "com.example.selenium.tests.cucumber.features.webelements.label.stepdefs",
                plugin = "json:target/report/webelements/label.json")
public class LabelTest extends BaseTest{

    @Override
    public void setUp() {}

    @Override
    public void tearDown() {}
}
