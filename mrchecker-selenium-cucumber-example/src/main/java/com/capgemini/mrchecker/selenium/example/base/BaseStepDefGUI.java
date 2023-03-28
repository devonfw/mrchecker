package com.capgemini.mrchecker.selenium.example.base;

import com.capgemini.mrchecker.selenium.example.data.TestContext;

//Thanks to cucumber-picocontainer dependency we are able to create context
//This object is shared between step definition classes objects during the test (scenario) execution
//Each test (scenario) has its own context object
public abstract class BaseStepDefGUI {
    private final TestContext testContext;

    public BaseStepDefGUI(TestContext testContext) {
        this.testContext = testContext;
    }

    public TestContext getTestContext() {
        return this.testContext;
    }
}