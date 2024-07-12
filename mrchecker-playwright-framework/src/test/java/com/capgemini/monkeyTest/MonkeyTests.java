package com.capgemini.monkeyTest;

import com.capgemini.framework.enums.PrjEpics;
import com.capgemini.framework.logger.AllureStepLogger;
import com.capgemini.framework.logger.Logger;
import com.capgemini.framework.playwright.BaseTest;
import com.capgemini.framework.tags.Status;
import com.capgemini.pages.demoqaforms.DemoQALoginPage;
import com.capgemini.pages.demoqaforms.helper.GremlinsHelper;
import io.qameta.allure.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static com.capgemini.framework.playwright.PlaywrightFactory.getPage;

/**
 * @author Michal Carlo
 * @author Malgorzata Dzienia
 * @since 2024-03-19
 * Gremlins is a monkey testing library that performs random actions on the page.
 */

@Epic(PrjEpics.DEMO_QA)
@Feature("GUI Monkey Tests")
public class MonkeyTests extends BaseTest {
	
	private final DemoQALoginPage demoQALoginPage    = new DemoQALoginPage();

	
	@Step("[SETUP]")
	@BeforeEach
	public void beforeEach() {
		AllureStepLogger.step("This is example step inside setUpTest()");
		demoQALoginPage.startPage();
		getPage().onConsoleMessage((msg) -> {
			Logger.logInfo(msg.text());
		});
	
	}
	@TmsLink("Test Management System ID")
	@Test
	@Tag(Status.REVIEW)
	void demoQALoginPageMonkeyTest_fill_form_test() throws IOException {
		GremlinsHelper gremlinsHelper = new GremlinsHelper();
		GremlinsHelper.setupGremlinsScript(getPage());
		GremlinsHelper.startGremlinHordeWithGremlinTypes(getPage(), List.of("clicker", "formFiller", "typer"));
	}
	
	@Test
	@Tag(Status.REVIEW)
	void demoQALoginPageMonkeyTest_test() throws IOException {
		GremlinsHelper gremlinsHelper = new GremlinsHelper();
		GremlinsHelper.setupGremlinsScript(getPage());
		GremlinsHelper.startGremlinHorde(getPage());
	}
}

