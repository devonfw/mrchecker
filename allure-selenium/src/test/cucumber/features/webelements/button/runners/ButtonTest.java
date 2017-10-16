package com.example.selenium.tests.cucumber.features.webelements.button.runners;

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
@CucumberOptions( strict = true,
                features = "src/test/java/com/example/selenium/tests/cucumber/features/webelements/button/gherkins/button.feature",
                glue = "com.example.selenium.tests.cucumber.features.webelements.button.stepdefs",
                plugin = "json:target/report/webelements/button.json")
public class ButtonTest extends BaseTest {

    @Override
    public void setUp() {
        BasePage.getDriver().get(PageSubURLsEnum.TOOLS_QA.subURL() + PageSubURLsEnum.AUTOMATION_PRACTICE_FORM.subURL());
    }

    @Override
    public void tearDown() {}
}
