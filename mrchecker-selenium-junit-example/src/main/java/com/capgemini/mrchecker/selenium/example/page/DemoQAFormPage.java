package com.capgemini.mrchecker.selenium.example.page;

import com.capgemini.mrchecker.selenium.example.base.BasePageGUI;
import com.capgemini.mrchecker.selenium.example.env.GetEnvironmentParam;

public class DemoQAFormPage extends BasePageGUI {
    private final String url = GetEnvironmentParam.DEMO_QA_FORM_URL.getValue();

    public void startPage() {
        loadPage(url);
    }
}