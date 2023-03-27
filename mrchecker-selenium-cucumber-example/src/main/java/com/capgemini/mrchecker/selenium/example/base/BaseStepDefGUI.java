package com.capgemini.mrchecker.selenium.example.base;

import com.capgemini.mrchecker.selenium.example.data.TestContext;

public abstract class BaseStepDefGUI {
    private final TestContext testContext;

    public BaseStepDefGUI(TestContext testContext) {
        this.testContext = testContext;
    }

    public TestContext getTestContext() {
        return this.testContext;
    }
}