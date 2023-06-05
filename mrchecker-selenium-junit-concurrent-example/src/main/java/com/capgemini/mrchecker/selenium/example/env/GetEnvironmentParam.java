package com.capgemini.mrchecker.selenium.example.env;

import com.capgemini.mrchecker.test.core.BaseTest;
import com.capgemini.mrchecker.test.core.exceptions.BFInputDataException;

import java.util.Objects;

public enum GetEnvironmentParam {
    //Reads the environment variable from /src/resources/environments/environment.csv
    // Name of enum must be in line with cell name in /src/resources/environments/environment.csv

    DEMO_QA_URL,
    DEMO_QA_LOGIN_URL,
    DEMO_QA_FORM_URL,
    DEMO_QA_LINKS_URL,
    EXAMPLE_USER_LOGIN,
    EXAMPLE_USER_PASSWORD;

    private final String value;

    @Override
    public String toString() {
        return getValue();
    }

    public String getValue() {
        if (null == this.value) {
            if (Objects.isNull(BaseTest.getEnvironmentService())) {
                throw new BFInputDataException("Environment Parameters class wasn't initialized properly");
            }
            this.value = BaseTest.getEnvironmentService().getValue(this.name()).trim();
        }
        return this.value;
    }
}