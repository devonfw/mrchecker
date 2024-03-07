package com.capgemini.framework.assertions;

import com.jayway.jsonpath.JsonPath;
import com.microsoft.playwright.APIResponse;
import io.qameta.allure.Step;
import org.assertj.core.api.AbstractAssert;
import org.assertj.core.api.Assertions;

import java.util.ArrayList;

public class ResponseAssert<SELF extends ResponseAssert<SELF>> extends AbstractAssert<SELF, APIResponse> {

    protected ResponseAssert(APIResponse actual, Class<?> selfType) {
        super(actual, selfType);
    }

    public static ResponseAssert<?> assertThat(APIResponse response) {
        return new ResponseAssert<>(response, ResponseAssert.class);
    }

    @Step("Assert that status code of response is {expectedStatusCode}")
    public SELF hasStatusCode(int expectedStatusCode) {
        Assertions.assertThat(actual.status())
                .as("Assert that status code of response is %s", expectedStatusCode)
                .isEqualTo(expectedStatusCode);
        return myself;
    }

    @Step("Assert that JSON in response contains text: {text}")
    public SELF returnedJsonContainsText(String text) {
        Assertions.assertThat(actual.text())
                .as("Assert that JSON in response contains text: " + text)
                .contains(text);
        return myself;
    }

    @Step("Assert that returned JSON has correct value '{expectedValue}' on path '{path}'")
    public SELF returnedJsonHasCorrectValueInListOfElements(String path, String expectedValue) {
        var jsonContext = JsonPath.parse(actual.text());
        var jsonpathCreatorName = new ArrayList<String>(jsonContext.read(path));

        Assertions.assertThat(jsonpathCreatorName)
                .as("Assert that returned JSON has correct value of parameter '%s'", path)
                .contains(expectedValue);
        return myself;
    }
}
