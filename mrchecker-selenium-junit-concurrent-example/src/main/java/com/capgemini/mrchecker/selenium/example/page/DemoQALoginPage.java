package com.capgemini.mrchecker.selenium.example.page;

import com.capgemini.mrchecker.selenium.core.newDrivers.elementType.Button;
import com.capgemini.mrchecker.selenium.core.newDrivers.elementType.InputTextElement;
import com.capgemini.mrchecker.selenium.core.newDrivers.elementType.LabelElement;
import com.capgemini.mrchecker.selenium.example.base.BasePageGUI;
import com.capgemini.mrchecker.selenium.example.env.GetEnvironmentParam;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

public class DemoQALoginPage extends BasePageGUI {
    private final String url = GetEnvironmentParam.DEMO_QA_LOGIN_URL.getValue();
    private final InputTextElement usernameInput = new InputTextElement(By.id("userName"));
    private final InputTextElement passwordInput = new InputTextElement(By.id("password"));
    private final Button loginButton = new Button(By.id("login"));
    private final LabelElement outputLabel = new LabelElement(By.id("output"));

    public void startPage(){
        loadPage(url);
    }

    @Step("Fill username: {username}")
    public void fillUsername(String username) {
        usernameInput.setInputText(username);
    }

    @Step("Fill password")
    public void fillPassword(String password) {
        passwordInput.setInputText(password);
    }

    @Step("Click login button")
    public void clickLoginButton() {
        loginButton.click();
    }

    public String getOutputText() {
        return outputLabel.getText();
    }

    public boolean isDisplayedUsernameInput(){
        return usernameInput.isDisplayed();
    }

    public boolean isDisplayedPasswordInput(){
        return passwordInput.isDisplayed();
    }

    public boolean isDisplayedLoginButton(){
        return loginButton.isDisplayed();
    }
}