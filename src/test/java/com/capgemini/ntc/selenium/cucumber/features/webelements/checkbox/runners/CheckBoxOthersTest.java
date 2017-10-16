package com.capgemini.ntc.selenium.tests.cucumber.features.webelements.checkbox.runners;

import com.capgemini.ntc.selenium.core.BasePage;
import com.capgemini.ntc.selenium.pages.enums.PageSubURLsEnum;
import com.example.core.tests.core.BaseTest;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

/**
 * Created by LKURZAJ on 20.03.2017.
 */
@RunWith(Cucumber.class)
@CucumberOptions(strict = true,
        features = "src/test/java/com/example/selenium/tests/cucumber/features/webelements/checkbox/gherkins/checkboxOthers.feature",
        glue = "com.capgemini.ntc.selenium.tests.cucumber.features.webelements.checkbox.stepdefs",
        plugin = "json:target/report/webelements/checkbox/checkboxOthers.json")
public class CheckBoxOthersTest extends BaseTest {

    @Override
    public void setUp() {
        BasePage.getDriver().get(PageSubURLsEnum.WWW_FONT_URL.subURL() + PageSubURLsEnum.REGISTRATION.subURL());
    }

    @Override
    public void tearDown() {}


}
