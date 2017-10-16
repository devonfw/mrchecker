package com.example.selenium.tests.cucumber.features.webelements.checkbox.runners;

import com.example.selenium.core.BasePage;
import com.example.selenium.pages.enums.PageSubURLsEnum;
import com.example.core.tests.core.BaseTest;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

/**
 * Created by LKURZAJ on 20.03.2017.
 */
@RunWith(Cucumber.class)
@CucumberOptions(strict = true,
        features = "src/test/java/com/example/selenium/tests/cucumber/features/webelements/checkbox/gherkins/checkboxByValue.feature",
        glue = "com.example.selenium.tests.cucumber.features.webelements.checkbox.stepdefs",
        plugin = "json:target/report/webelements/checkbox/checkboxByValue.json")
public class CheckBoxByValueTest extends BaseTest {

    @Override
    public void setUp() {
        BasePage.getDriver().get(PageSubURLsEnum.WWW_FONT_URL.subURL() + PageSubURLsEnum.REGISTRATION.subURL());
    }

    @Override
    public void tearDown() {}


}
