package com.capgemini.mrchecker.selenium.example.data;

import java.util.HashMap;
import java.util.Map;

public class TestContext {
    private final Map<String, Object> data = new HashMap<>();

    public <T> T get(String name) {
        return (T) data.get(name);
    }

    public void set(String name, Object value) {
        data.put(name, value);
    }
}