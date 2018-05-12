package com.capgemini.ntc.webapi.httpbin;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

import com.capgemini.ntc.test.core.logger.BFLogger;
import com.capgemini.ntc.webapi.BasePageWebApiTest;
import com.capgemini.ntc.webapi.pages.httbin.SimplePOSTPage;
import com.capgemini.ntc.webapi.pages.httbin.SimplePOSTPage.PizzaSize;
import com.capgemini.ntc.webapi.pages.httbin.SimplePOSTPage.PizzaToppings;

import io.restassured.response.Response;

public class SimplePOSTTest extends BasePageWebApiTest {
	
	private SimplePOSTPage simplePOSTPage = new SimplePOSTPage();
	
	@Test
	public void sendSimplePOSTQuery() {
		BFLogger.logInfo("Step 1 - Sending POST query to " + simplePOSTPage.getEndpoint());
		Response response = simplePOSTPage.sendPOSTQuery();
		
		BFLogger.logInfo("Step 2 - Validate response status code: ");
		assertThat(response.statusCode(), is(200));
		
		BFLogger.logInfo("RESPONSE Body " + response.thenReturn()
						.body()
						.asString());
	}
	
	@Test
	public void fillFormWithValidData() {
		simplePOSTPage.setCustomerName("Jan Kowalski");
		simplePOSTPage.setTelephoneNumber("123456789");
		simplePOSTPage.setEmailAddress("jan.kowalski@email.com");
		simplePOSTPage.pickPizzaSize(PizzaSize.LARGE);
		simplePOSTPage.pickPizzaToppings(PizzaToppings.BACON, PizzaToppings.EXTRA_CHEESE);
		simplePOSTPage.setPrefferedDeliveryTime(12, 30);
		simplePOSTPage.setDeliveryInstruction("8 floor");
		
		Response response = simplePOSTPage.sendPOSTQuery();
		
		BFLogger.logInfo("Step 2 - Validate response status code: ");
		assertThat(response.statusCode(), is(200));
		
		BFLogger.logInfo("RESPONSE Body " + response.thenReturn()
						.body()
						.asString());
	}
}
