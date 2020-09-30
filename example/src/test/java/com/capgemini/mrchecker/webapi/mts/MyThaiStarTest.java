package com.capgemini.mrchecker.webapi.mts;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;

import java.util.stream.Stream;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.MethodSource;

import com.capgemini.mrchecker.common.mts.data.User;
import com.capgemini.mrchecker.test.core.BaseTest;
import com.capgemini.mrchecker.test.core.utils.PageFactory;
import com.capgemini.mrchecker.webapi.mts.pages.LoginPage;
import com.capgemini.mrchecker.webapi.mts.pages.TablePage;

import io.restassured.response.Response;

@Tag("MTS_API")
public class MyThaiStarTest extends BaseTest {
	
	private final TablePage tablePage = PageFactory.getPageInstance(TablePage.class);
	
	@BeforeAll
	public static void setUpSuite() {
		PageFactory.getPageInstance(LoginPage.class)
				.login(User.waiterUser());
	}
	
	@ParameterizedTest
	@CsvFileSource(resources = "/datadriven/mts/valid_table_ids.csv", numLinesToSkip = 1)
	public void shouldReturnTableData(String tableId) {
		sendGetByIdRequestAndAssertResponseStatusCode(tableId, 200);
	}
	
	static Stream<String> validTableIds() {
		return Stream.of("0", "1", "2", "3", "4", "5", "6", "7", "8");
	}
	
	@ParameterizedTest
	@MethodSource("invalidTableIds")
	public void shouldReturnErrorWhenInvalidId(String tableId, int expectedStatus) {
		sendGetByIdRequestAndAssertResponseStatusCode(tableId, expectedStatus);
	}
	
	static Stream<Arguments> invalidTableIds() {
		return Stream.of(
				Arguments.of("-1", 500),
				Arguments.of("9", 500),
				Arguments.of("2.1", 404),
				Arguments.of("a", 404));
	}
	
	private void sendGetByIdRequestAndAssertResponseStatusCode(String tableId, int expected) {
		Response response = tablePage.getTableById(tableId);
		
		assertThat(response.getStatusCode(), is(equalTo(expected)));
	}
}
