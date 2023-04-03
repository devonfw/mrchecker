package com.capgemini.mrchecker.selenium.example.test.hooks;

import com.capgemini.mrchecker.test.core.cucumber.BaseHook;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;

//Cucumber @Before/@After annotations can't be located inside extended class
//You need to have them in the class inside the final project (not in MrChecker)
public class BaseHookGUI extends BaseHook {
    //Method executed before each test (scenario)
    //By default it executes setUp() method from BaseTestGUI
    //Integer minimum means it should be executed first even before other @Before methods
    //@Before runs in increment order, means value 0 would run first and 1 would be after 0.
    @Before(order = Integer.MIN_VALUE)
    public void setup(Scenario scenario) {
        super.setup(scenario);
    }

    //Method executed after each test (scenario)
    //By default it executes tearDown() method from BaseTestGUI
    //Integer minimum means it should be executed last even after other @After methods
    //@After runs in decrements order, means value 1 would run first and 0 would be after 1.
    @After(order = Integer.MIN_VALUE)
    public void tearDown(Scenario scenario) {
        super.tearDown(scenario);
    }
}