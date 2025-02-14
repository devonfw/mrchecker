package com.capgemini.infrastructure;

import org.apache.commons.lang3.StringUtils;
import org.eclipse.microprofile.config.Config;
import org.eclipse.microprofile.config.ConfigProvider;

public class Configuration {
    private static Configuration instance = null;
    private static final Config CONFIG = ConfigProvider.getConfig();

    public static final String MY_WEB_APP = "my-web-app";
    public static final boolean DEBUG = CONFIG.getOptionalValue("testcontainers.mode.debug", Boolean.class).orElse(false);

    private String myWebAppUrl = "";

    private Configuration() {
    }

    public static Configuration getInstance() {
        if (instance == null) {
            instance = new Configuration();
        }
        return instance;
    }

    public void setMyWebAppUrl(String myWebAppUrl) {
        this.myWebAppUrl = StringUtils.isBlank(myWebAppUrl) ? "" : myWebAppUrl;
    }

    public String getMyWebAppUrl() {
        return myWebAppUrl;
    }
}
