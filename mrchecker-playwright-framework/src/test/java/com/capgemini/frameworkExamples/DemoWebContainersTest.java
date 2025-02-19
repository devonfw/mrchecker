package com.capgemini.frameworkExamples;

import com.capgemini.framework.enums.PrjEpics;
import com.capgemini.framework.playwright.containers.BaseContainersTest;
import com.capgemini.framework.tags.Status;
import com.capgemini.frameworkExamples.page.TestContainersDemoPage;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.TmsLink;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

@QuarkusTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class DemoWebContainersTest extends BaseContainersTest {
    private TestContainersDemoPage testContainersDemoPage = new TestContainersDemoPage();

    @TmsLink("Test Management System ID")
    @Epic(PrjEpics.EXAMPLES)
    @Feature("TestContainer")
    @Description("Test case description")
    @Test
    @Tag("TestContainer")
    @Tag(Status.DONE)
    void demoContainersTest() {
        System.out.println("Executing Test...");
        var returnText = testContainersDemoPage.clickButtonAndReturnText();
        Assertions.assertEquals("Request info", returnText);
    }
}