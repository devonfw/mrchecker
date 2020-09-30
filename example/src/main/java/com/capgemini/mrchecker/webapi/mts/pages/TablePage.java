package com.capgemini.mrchecker.webapi.mts.pages;

import io.qameta.allure.Step;
import io.restassured.response.Response;

public class TablePage extends MTSWebApiBasePage {
	
	@Override
	public String getEndpoint() {
		return "/services/rest/bookingmanagement/v1/table";
	}
	
	@Step("Call GET table by ID")
	public Response getTableById(String id) {
		return getRequestBuilder().get(getEndpoint() + "/" + id);
	}
}
