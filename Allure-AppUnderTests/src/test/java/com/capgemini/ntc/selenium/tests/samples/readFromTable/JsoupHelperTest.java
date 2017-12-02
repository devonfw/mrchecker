package com.capgemini.ntc.selenium.tests.samples.readFromTable;

import static org.junit.Assert.assertArrayEquals
;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.capgemini.ntc.selenium.core.BasePage;
import com.capgemini.ntc.selenium.core.exceptions.BFElementNotFoundException;
import com.capgemini.ntc.selenium.jsoupHelper.JsoupHelper;
import com.capgemini.ntc.selenium.tests.PageTestUtils;
import com.capgemini.ntc.test.core.BaseTest;
import com.capgemini.ntc.test.core.logger.BFLogger;

public class JsoupHelperTest extends BaseTest {

	private String username = "loginName";
	private String password = "Password345!";
	private final By selectorTableBody = By.cssSelector("div.fid-datagrid.magicgrid--wrapper");
	private final By selectorMultipleElements = By.cssSelector("tr.normal-row");
	private final By selectorAccountSelector = By.cssSelector("div.account-selector--accounts-wrapper");
	private final By selectorMultipleAccounts = By
			.cssSelector("span.account-selector--tab-row.account-selector--account-name");
	private final By selectorPositionsTableRow = By.cssSelector("table.NG--NotUsedClass tbody.p-positions-tbody>tr");
	private final By selectorSingleElementSearchArea = By.cssSelector("#pgnb");
	private final By selectorSingleElement = By.cssSelector("div.pnmm > ul > li:nth-child(1) > div > a");
	private final By selectorNotExisting = By.cssSelector("abcNotExist");
	private final By selectorElementWithChildText = By
			.cssSelector("tr.normal-row:nth-of-type(7) > td:nth-child(7) span.magicgrid--stacked-data-value");
	// selector from MagicGridRow
	private final By selectorStockSymbol = By.cssSelector("span.stock-symbol");
	private WebElement table;
	private long timePassed;

	@Override
	public void setUp() {
		table = BasePage.getDriver().findElement(selectorTableBody);
	}

	@Override
	public void tearDown() {
		PageTestUtils.logout();
	}

	@Test
	public void findTextsReturnsOneValuesWhenOneExist() {
		BFLogger.logInfo("[NEW METHODS]");
		String singleText = "Accounts & Trade";
		startTimeMeasure();
		List<String> valuesNewMethod = JsoupHelper.findTexts(selectorSingleElement);
		String textNewMethod = valuesNewMethod.get(0);
		stopTimeMeasure();
		assertOneValue(valuesNewMethod);
		assertEquals("Incorrect text was found.", singleText, textNewMethod);

		WebElement searchArea = BasePage.getDriver().findElement(selectorSingleElementSearchArea);
		startTimeMeasure();
		valuesNewMethod = JsoupHelper.findTexts(searchArea, selectorSingleElement);
		textNewMethod = valuesNewMethod.get(0);
		stopTimeMeasure();
		assertOneValue(valuesNewMethod);
		assertEquals("Incorrect text was found.", singleText, textNewMethod);

		BFLogger.logInfo("[OLD METHOD]");
		startTimeMeasure();
		List<WebElement> valuesOldMethod = BasePage.getDriver().findElements(selectorSingleElement);
		String textOldMethod = valuesOldMethod.get(0).getText();
		stopTimeMeasure();
		assertOneValue(valuesOldMethod);

		assertEquals("Incorrect text was found.", textNewMethod, textOldMethod);
	}

	@Test
	public void findTextsReturnsMultipleValuesWhenMultipleExist() {
		BFLogger.logInfo("[NEW METHODS]");
		startTimeMeasure();
		WebElement accountSelector = BasePage.getDriver().findElement(selectorAccountSelector);
		List<String> valuesNewMethodArea = JsoupHelper.findTexts(accountSelector, selectorMultipleAccounts);
		assertMultipleValues(valuesNewMethodArea);
		stopTimeMeasure();

		startTimeMeasure();
		List<String> valuesNewMethodPage = JsoupHelper.findTexts(selectorMultipleAccounts);
		stopTimeMeasure();
		assertMultipleValues(valuesNewMethodPage);

		assertListsEquls(valuesNewMethodArea, valuesNewMethodPage);

		BFLogger.logInfo("[OLD METHOD]");
		startTimeMeasure();
		List<WebElement> elements = BasePage.getDriver().findElements(selectorMultipleAccounts);
		List<String> valuesOldMethod = new ArrayList<String>();
		for (WebElement element : elements) {
			valuesOldMethod.add(element.getText());
		}
		stopTimeMeasure();
		assertMultipleValues(valuesOldMethod);

		assertListsEquls(valuesNewMethodPage, valuesOldMethod);
	}

	@Test
	public void findTextsReturnsZeroValuesWhenZeroExist() {
		BFLogger.logInfo("[NEW METHODS]");
		startTimeMeasure();
		List<String> values = JsoupHelper.findTexts(table, selectorNotExisting);
		stopTimeMeasure();
		assertNoValues(values);

		startTimeMeasure();
		values = JsoupHelper.findTexts(selectorNotExisting);
		stopTimeMeasure();
		assertNoValues(values);

		BFLogger.logInfo("[OLD METHOD]");
		startTimeMeasure();
		List<WebElement> elements = BasePage.getDriver().findElements(selectorNotExisting);
		stopTimeMeasure();
		assertTrue(elements.isEmpty());
	}

	@Test
	public void findOwnTextsReturnsOneValueWhenOneExist() {
		String singleText = "$10.00";
		startTimeMeasure();
		List<String> valuesNewMethod = JsoupHelper.findOwnTexts(selectorElementWithChildText);
		String textNewMethod = valuesNewMethod.get(0);
		stopTimeMeasure();
		assertOneValue(valuesNewMethod);
		assertEquals("Incorrect text was found.", singleText, textNewMethod);

		WebElement searchArea = BasePage.getDriver().findElement(selectorTableBody);
		startTimeMeasure();
		valuesNewMethod = JsoupHelper.findOwnTexts(searchArea, selectorElementWithChildText);
		textNewMethod = valuesNewMethod.get(0);
		stopTimeMeasure();
		assertOneValue(valuesNewMethod);
		assertEquals("Incorrect text was found.", singleText, textNewMethod);
	}

	@Test
	public void findOwnTextsReturnsMultipleWhenMultipleExist() {
		startTimeMeasure();
		List<String> valuesNewMethodArea = JsoupHelper.findOwnTexts(table, selectorMultipleElements);
		assertMultipleValues(valuesNewMethodArea);
		stopTimeMeasure();

		startTimeMeasure();
		List<String> valuesNewMethodPage = JsoupHelper.findOwnTexts(selectorMultipleElements);
		stopTimeMeasure();
		assertMultipleValues(valuesNewMethodPage);

		assertListsEquls(valuesNewMethodArea, valuesNewMethodPage);
	}

	@Test
	public void findOwnTextsReturnsZeroValuesWhenZeroExist() {
		startTimeMeasure();
		List<String> values = JsoupHelper.findOwnTexts(table, selectorNotExisting);
		stopTimeMeasure();
		assertNoValues(values);

		startTimeMeasure();
		values = JsoupHelper.findOwnTexts(selectorNotExisting);
		stopTimeMeasure();
		assertNoValues(values);
	}

	@Test
	public void findAttributeReturnsOneValuesWhenOneExist() {
		BFLogger.logInfo("[NEW METHODS]");
		String attribute = "class";
		startTimeMeasure();
		List<String> valuesNewMethod = JsoupHelper.findAttributes(selectorSingleElement, attribute);
		stopTimeMeasure();
		assertOneValue(valuesNewMethod);

		WebElement searchArea = BasePage.getDriver().findElement(selectorSingleElementSearchArea);
		startTimeMeasure();
		valuesNewMethod = JsoupHelper.findAttributes(searchArea, selectorSingleElement, attribute);
		stopTimeMeasure();
		assertOneValue(valuesNewMethod);

		BFLogger.logInfo("[OLD METHOD]");
		startTimeMeasure();
		List<WebElement> elements = BasePage.getDriver().findElements(selectorSingleElement);
		List<String> valuesOldMethod = new ArrayList<String>();
		for (WebElement element : elements) {
			valuesOldMethod.add(element.getAttribute(attribute));
		}
		stopTimeMeasure();
		assertOneValue(valuesOldMethod);

		assertListsEquls(valuesNewMethod, valuesOldMethod);
	}

	@Test
	public void findAttributeReturnsMultipleValuesWhenMultipleExist() {
		BFLogger.logInfo("[NEW METHODS]");
		String attribute = "class";
		startTimeMeasure();
		List<String> valuesNewMethodPage = JsoupHelper.findAttributes(selectorMultipleAccounts, attribute);
		stopTimeMeasure();
		assertMultipleValues(valuesNewMethodPage);

		WebElement searchArea = BasePage.getDriver().findElement(selectorAccountSelector);
		startTimeMeasure();
		List<String> valuesNewMethodArea = JsoupHelper.findAttributes(searchArea, selectorMultipleAccounts, attribute);
		stopTimeMeasure();
		assertMultipleValues(valuesNewMethodArea);

		assertListsEquls(valuesNewMethodPage, valuesNewMethodArea);

		BFLogger.logInfo("[OLD METHOD]");
		startTimeMeasure();
		List<WebElement> elements = BasePage.getDriver().findElements(selectorMultipleAccounts);
		List<String> valuesOldMethod = new ArrayList<String>();
		for (WebElement element : elements) {
			valuesOldMethod.add(element.getAttribute(attribute));
		}
		stopTimeMeasure();
		assertMultipleValues(valuesOldMethod);

		assertListsEquls(valuesNewMethodArea, valuesOldMethod);
	}

	@Test
	public void findAttributeReturnsNullValuesWhenNoExist() {
		BFLogger.logInfo("[NEW METHODS]");
		String attribute = "cmyk";
		startTimeMeasure();
		List<String> valuesNewMethodPage = JsoupHelper.findAttributes(selectorMultipleAccounts, attribute);
		stopTimeMeasure();
		assertOnlyNullValues(valuesNewMethodPage);

		WebElement searchArea = BasePage.getDriver().findElement(selectorAccountSelector);
		startTimeMeasure();
		List<String> valuesNewMethodArea = JsoupHelper.findAttributes(searchArea, selectorMultipleAccounts, attribute);
		stopTimeMeasure();
		assertOnlyNullValues(valuesNewMethodArea);
		assertEquals("Different size of lists.", valuesNewMethodPage.size(), valuesNewMethodArea.size());

		BFLogger.logInfo("[OLD METHOD]");
		startTimeMeasure();
		List<WebElement> elements = BasePage.getDriver().findElements(selectorMultipleAccounts);
		List<String> valuesOldMethod = new ArrayList<String>();
		for (WebElement element : elements) {
			valuesOldMethod.add(element.getAttribute(attribute));
		}
		stopTimeMeasure();
		assertOnlyNullValues(valuesOldMethod);
		assertEquals("Different size of lists.", valuesNewMethodArea.size(), valuesOldMethod.size());
	}

	@Test
	public void findElementsReturnsManyElementsWhenManyElementsExist() {
		String symbol = "KYN";
		List<MagicGridRow> rowsNewMethod = new ArrayList<MagicGridRow>();

		BFLogger.logInfo("[NEW METHODS]");
		startTimeMeasure();
		List<WebElement> values = JsoupHelper.findElements(selectorPositionsTableRow, selectorStockSymbol, symbol);
		rowsNewMethod.clear();
		for (WebElement element : values) {
			rowsNewMethod.add(new MagicGridRow(BasePage.getDriver(), null, element, null));
		}
		stopTimeMeasure();
		assertMultipleValues(rowsNewMethod);

		startTimeMeasure();
		values = JsoupHelper.findElements(selectorPositionsTableRow, symbol);
		rowsNewMethod.clear();
		for (WebElement element : values) {
			rowsNewMethod.add(new MagicGridRow(BasePage.getDriver(), null, element, null));
		}
		stopTimeMeasure();
		assertMultipleValues(rowsNewMethod);

		// BFLogger.logInfo("[OLD METHOD]");
		// startTimeMeasure();
		// List<MagicGridRow> rowsOldMethod = positionsTab.getTableBody().getPositionsBySymbol(symbol);
		// stopTimeMeasure();
		// assertMultipleValues(rowsOldMethod);
		//
		// assertEquals(rowsNewMethod.size(), rowsOldMethod.size());
		// for (int i = 0; i < rowsNewMethod.size(); i++) {
		// assertEquals(rowsNewMethod.get(i).getStockName(), rowsOldMethod.get(i).getStockName());
		// }
	}

	@Test
	public void findElementsReturnsOneElementWhenOneElementExist() {
		BFLogger.logInfo("[NEW METHODS]");
		String symbol = "-AAPL7150717C120";
		List<MagicGridRow> rowsNewMethod = new ArrayList<MagicGridRow>();

		startTimeMeasure();
		List<WebElement> values = JsoupHelper.findElements(selectorPositionsTableRow, selectorStockSymbol, symbol);
		rowsNewMethod.clear();
		for (WebElement element : values) {
			rowsNewMethod.add(new MagicGridRow(BasePage.getDriver(), null, element, null));
		}
		stopTimeMeasure();
		assertOneValue(values);

		startTimeMeasure();
		values = JsoupHelper.findElements(selectorPositionsTableRow, selectorStockSymbol, symbol);
		rowsNewMethod.clear();
		for (WebElement element : values) {
			rowsNewMethod.add(new MagicGridRow(BasePage.getDriver(), null, element, null));
		}
		stopTimeMeasure();
		assertOneValue(rowsNewMethod);

		startTimeMeasure();
		values = JsoupHelper.findElements(selectorPositionsTableRow, symbol);
		rowsNewMethod.clear();
		for (WebElement element : values) {
			rowsNewMethod.add(new MagicGridRow(BasePage.getDriver(), null, element, null));
		}
		stopTimeMeasure();
		assertOneValue(rowsNewMethod);

		// BFLogger.logInfo("[OLD METHOD]");
		// startTimeMeasure();
		// List<MagicGridRow> rowsOldMethod = positionsTab.getTableBody().getPositionsBySymbol(symbol);
		// stopTimeMeasure();
		// assertOneValue(rowsOldMethod);
		//
		// assertEquals(rowsNewMethod.get(0).getStockName(), rowsOldMethod.get(0).getStockName());
	}

	@Test(expected = BFElementNotFoundException.class)
	public void findElementsThrowsPiAtElementNotFoundExceptionWhenElementNotExistTwoParameters() {
		String symbol = "abcdefgh1234";
		JsoupHelper.findElements(selectorPositionsTableRow, symbol);
	}

	@Test(expected = BFElementNotFoundException.class)
	public void findElementsThrowsPiAtElementNotFoundExceptionWhenElementNotExistTreeParameters() {
		String symbol = "abcdefgh1234";
		JsoupHelper.findElements(selectorPositionsTableRow, selectorStockSymbol, symbol);
	}

	@Test
	public void selectorsWithQuotesWorkFine() {
		By selectorWithQuotes = By
				.cssSelector("span[class='account-selector--tab-row account-selector--account-name']");
		List<String> results = JsoupHelper.findOwnTexts(selectorWithQuotes);
		assertMultipleValues(results);
		selectorWithQuotes = By
				.cssSelector("span[class=  'account-selector--tab-row account-selector--account-name'    ]");
		results = JsoupHelper.findOwnTexts(selectorWithQuotes);
		assertMultipleValues(results);
	}

	private <T> void assertOneValue(List<T> values) {
		assertTrue(values.size() + " values were found.", values.size() == 1);
	}

	private <T> void assertMultipleValues(List<T> values) {
		assertTrue(values.size() + " values were found.", values.size() > 1);
	}

	private <T> void assertNoValues(List<T> values) {
		assertTrue(values.size() + " values were found.", values.isEmpty());
	}

	private <T> void assertOnlyNullValues(List<T> values) {
		for (T value : values) {
			assertNull("Value not null: " + value, value);
		}
	}

	private void startTimeMeasure() {
		timePassed = System.currentTimeMillis();
	}

	private void stopTimeMeasure() {
		BFLogger.logInfo("Fetching duration: " + (System.currentTimeMillis() - timePassed));
	}

	private void assertListsEquls(List<?> expectedValues, List<?> actualValues) {
		assertArrayEquals(expectedValues.toArray(new String[expectedValues.size()]),
				actualValues.toArray(new String[actualValues.size()]));
	}

}
