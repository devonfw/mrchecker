package com.capgemini.mrchecker.selenium.example.base;

import com.capgemini.mrchecker.selenium.core.utils.StepLogger;
import com.capgemini.mrchecker.test.core.BaseTest;

//You can use extended BaseTest class to configure setup and teardown methods
//Those methods will be used for each test (scenario)
public class BaseTestGUI extends BaseTest {
    @Override
    public void setUp() {
        StepLogger.step("Example of Allure step inside setUp()");
    }

    @Override
    public void tearDown() {
        StepLogger.step("Example of Allure step inside tearDown()");
    }
}