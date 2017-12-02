package com.capgemini.ntc.selenium.tests.samples.dataDriven.OutsideData;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.capgemini.ntc.selenium.core.BasePage;
import com.capgemini.ntc.selenium.core.utils.TimeUtills;
import com.capgemini.ntc.selenium.tests.PageTestUtils;
import com.capgemini.ntc.selenium.tests.samples.dataDriven.InsideData.CardList;
import com.capgemini.ntc.test.core.BaseTest;
import com.capgemini.ntc.test.core.testRunners.ParallelParameterized;

import junitparams.Parameters;

@RunWith(ParallelParameterized.class)
public class CardOrderWithDataDrivenTest extends BaseTest {

	public CardOrderWithDataDrivenTest() {
		super();
	}

	private final String VALUE_ON_PAGE = "Accounts";


	public void setUpTest(String login) {
	}

	@Test
	@Parameters(source = CardOrderDataProvider.class)
	public void checkCardOrderV2(MatrixDataContainer data) {
		setUpTest(data.getLogin());

		WebDriverWait wait = new WebDriverWait(BasePage.getDriver(), 10);
		
		CardList cardList = new CardList();

		String[][] actualCardsTitlesOrder = cardList.cardTitles();
		String[][] expectedCardsTitlesOrder = data.getExpectedValues();
		// check if number of columns is equal
		assertEquals("Number of columns is not equal.", expectedCardsTitlesOrder.length, cardList.columnsCount());
	}

	@Override
	public void tearDown() {
		PageTestUtils.logout();
	}

	@Override
	public void setUp() {

	}

}
