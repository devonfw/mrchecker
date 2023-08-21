package com.capgemini.mrchecker.playwright.example.page;

import com.capgemini.mrchecker.playwright.example.base.BasePageGUI;
import com.capgemini.mrchecker.playwright.example.env.GetEnvironmentParam;

public class DemoQAFormPage extends BasePageGUI {
    private final String url = GetEnvironmentParam.DEMO_QA_FORM_URL.getValue();

    public void startPage() {
        loadPage(url);
    }
}