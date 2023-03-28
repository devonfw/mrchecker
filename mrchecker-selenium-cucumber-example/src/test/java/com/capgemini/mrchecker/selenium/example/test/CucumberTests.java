package com.capgemini.mrchecker.selenium.example.test;

import org.junit.platform.suite.api.ConfigurationParameter;
import org.junit.platform.suite.api.IncludeEngines;
import org.junit.platform.suite.api.SelectClasspathResource;
import org.junit.platform.suite.api.Suite;

import static io.cucumber.junit.platform.engine.Constants.GLUE_PROPERTY_NAME;
import static io.cucumber.junit.platform.engine.Constants.PLUGIN_PROPERTY_NAME;

//Entry class to execute tests using JUnit and Cucumber engine
//Glue: you need to set paths to hooks and step definitions in your project
//Plugin: Allure plugin to handle proper report generation
//Plugin: MrChecker EventListenerPlugin to properly handle driver closing after each test (scenario)
@Suite
@IncludeEngines("cucumber")
@SelectClasspathResource("features")
@ConfigurationParameter(key = GLUE_PROPERTY_NAME, value = "com.capgemini.mrchecker.selenium.example.test.stepdefs,com.capgemini.mrchecker.selenium.example.test.hooks")
@ConfigurationParameter(key = PLUGIN_PROPERTY_NAME, value = "io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm, com.capgemini.mrchecker.test.core.cucumber.EventListenerPlugin")
public class CucumberTests {
}