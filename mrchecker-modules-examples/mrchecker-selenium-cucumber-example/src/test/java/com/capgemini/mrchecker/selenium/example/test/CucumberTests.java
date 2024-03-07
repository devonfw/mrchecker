package com.capgemini.mrchecker.selenium.example.test;

import org.junit.platform.suite.api.ConfigurationParameter;
import org.junit.platform.suite.api.IncludeEngines;
import org.junit.platform.suite.api.SelectClasspathResource;
import org.junit.platform.suite.api.Suite;

import static io.cucumber.junit.platform.engine.Constants.GLUE_PROPERTY_NAME;
import static io.cucumber.junit.platform.engine.Constants.PLUGIN_PROPERTY_NAME;

//Entry class to execute tests using JUnit and Cucumber engine
@Suite
@IncludeEngines("cucumber")
@SelectClasspathResource("features")
//Glue: you need to set paths to hooks and step definitions in your project
@ConfigurationParameter(key = GLUE_PROPERTY_NAME, value = "com.capgemini.mrchecker.selenium.example.test.stepdefs,com.capgemini.mrchecker.selenium.example.test.hooks")
//!!! ORDER OF THE PLUGINS IS VERY IMPORTANT !!!
//!!! ALLURE PLUGIN MUST BE LAST ONE SINCE IT WILL CLOSE THE TEST REPORT !!!
//Plugin: MrChecker EventListenerPlugin to properly handle driver closing after each test (scenario)
//Plugin: Allure plugin to handle proper report generation
@ConfigurationParameter(key = PLUGIN_PROPERTY_NAME, value = "com.capgemini.mrchecker.test.core.cucumber.EventListenerPlugin,io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm")
public class CucumberTests {
}