package com.capgemini.mrchecker.selenium.example.page;

import com.capgemini.mrchecker.playwright.core.newDrivers.elementType.Button;
import com.capgemini.mrchecker.playwright.core.newDrivers.elementType.InputTextElement;
import com.capgemini.mrchecker.playwright.core.newDrivers.elementType.LabelElement;
import com.capgemini.mrchecker.selenium.example.base.BasePageGUI;
import com.capgemini.mrchecker.selenium.example.env.GetEnvironmentParam;
import io.qameta.allure.Step;

public class DemoQALoginPage extends BasePageGUI {
    private final String url = GetEnvironmentParam.DEMO_QA_LOGIN_URL.getValue();
    private final InputTextElement usernameInput = new InputTextElement("#userName");
    private final InputTextElement passwordInput = new InputTextElement("#password");
    private final Button loginButton = new Button("#login");
    private final LabelElement outputLabel = new LabelElement("#output");

    public void startPage() {
        loadPage(url);
    }

    @Step("Fill username: {username}")
    public void fillUsername(String username) {
        usernameInput.fillInputText(username);
    }

    @Step("Fill password")
    public void fillPassword(String password) {
        passwordInput.fillInputText(password);
    }

    @Step("Click login button")
    public void clickLoginButton() {
        loginButton.click();
    }

    public String getOutputText() {
        return outputLabel.getText();
    }

    public boolean isDisplayedUsernameInput() {
        return usernameInput.isVisible();
    }

    public boolean isDisplayedPasswordInput() {
        return passwordInput.isVisible();
    }

    public boolean isDisplayedLoginButton() {
        return loginButton.isVisible();
    }
}