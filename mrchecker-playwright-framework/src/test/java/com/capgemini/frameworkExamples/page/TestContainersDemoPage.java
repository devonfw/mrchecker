package com.capgemini.frameworkExamples.page;

import com.capgemini.framework.logger.AllureStepLogger;
import com.capgemini.framework.playwright.infrastructure.Configuration;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.capgemini.framework.playwright.PlaywrightFactory.getPage;
import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class TestContainersDemoPage {
    private static final int PAGE_LOADING_TIMEOUT = 20000;
    private static final Logger log = LoggerFactory.getLogger(TestContainersDemoPage.class);

    public String clickButtonAndReturnText() {
        log.info(Configuration.getInstance().getMyWebAppUrl());

        getPage().navigate(Configuration.getInstance().getMyWebAppUrl(), new Page.NavigateOptions().setTimeout(PAGE_LOADING_TIMEOUT));
        getPage().onLoad(p -> AllureStepLogger.step("Page loaded!"));
        getPage().getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Show request details")).click();

        assertThat(getPage().getByRole(AriaRole.HEADING, new Page.GetByRoleOptions().setName("Request info").setExact(true))).isVisible();
        return getPage().getByRole(AriaRole.HEADING, new Page.GetByRoleOptions().setName("Request info").setExact(true)).innerText();
    }
}
