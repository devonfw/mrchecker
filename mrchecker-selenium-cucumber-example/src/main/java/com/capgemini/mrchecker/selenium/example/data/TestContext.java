package com.capgemini.mrchecker.selenium.example.data;

import java.util.HashMap;
import java.util.Map;

//Thanks to cucumber-picocontainer dependency we are able to create context
//This object is shared between step definition classes objects during the test (scenario) execution
//Each test (scenario) has its own context object
public class TestContext {
    //You can prepare any data structure (variables, methods) you want
    private final Map<String, Object> data = new HashMap<>();

    public <T> T get(String name) {
        return (T) data.get(name);
    }

    public void set(String name, Object value) {
        data.put(name, value);
    }
}