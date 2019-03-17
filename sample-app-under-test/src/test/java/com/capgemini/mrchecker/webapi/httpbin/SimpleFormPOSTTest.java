package com.capgemini.mrchecker.webapi.httpbin;

import static com.capgemini.mrchecker.webapi.core.utils.RegexMatcher.matches;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.greaterThanOrEqualTo;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.Matchers.lessThanOrEqualTo;
import static org.junit.Assert.assertThat;

import java.util.List;
import java.util.Map;

import org.hamcrest.core.StringContains;
import org.junit.BeforeClass;
import org.junit.Test;

import com.capgemini.mrchecker.test.core.logger.BFLogger;
import com.capgemini.mrchecker.webapi.pages.httbin.SimpleFormPOSTPage;
import com.capgemini.mrchecker.webapi.pages.httbin.SimpleFormPOSTPage.PizzaSize;
import com.capgemini.mrchecker.webapi.pages.httbin.SimpleFormPOSTPage.PizzaToppings;

import io.restassured.response.Response;

/*
 * This example covering only positive cases because 'httpbin.org/post' doesn't include server-side validations.
 * On the other hand 'httpbin.org/forms/post' including client-side validations. Below tests will cover them.
 */

public class SimpleFormPOSTTest extends com.capgemini.mrchecker.webapi.BasePageWebApiTest {
	
	private static SimpleFormPOSTPage	simplePOSTPage	= new SimpleFormPOSTPage();
	private static Response				response;
	
	// Valid data to fill the form
	private static final String			CUSTOMER_NAME			= "Jan Kowalski";
	private static final String			TELEPHONE_NUMBER		= "123456789";
	private static final String			EMAIL_ADDRESS			= "jan.kowalski@email.com";
	private static final PizzaSize		PIZZA_SIZE				= PizzaSize.LARGE;
	private static final PizzaToppings	PIZZA_TOPPING_1			= PizzaToppings.BACON;
	private static final PizzaToppings	PIZZA_TOPPING_2			= PizzaToppings.EXTRA_CHEESE;
	private static final int			DELIVERY_TIME_HOUR		= 12;
	private static final int			DELIVERY_TIME_MINUTE	= 30;
	private static final String			DELIVERY_INSTRUCTION	= "8 floor";
	
	@BeforeClass
	public static void submitFormWithValidData() {
		BFLogger.logInfo("Step 1 - Setting up CustomerName field: " + CUSTOMER_NAME);
		simplePOSTPage.setCustomerName(CUSTOMER_NAME);
		
		BFLogger.logInfo("Step 2 - Setting up Telephone Number field: " + TELEPHONE_NUMBER);
		simplePOSTPage.setTelephoneNumber(TELEPHONE_NUMBER);
		
		BFLogger.logInfo("Step 3 - Setting up EmailAddress field: " + EMAIL_ADDRESS);
		simplePOSTPage.setEmailAddress(EMAIL_ADDRESS);
		
		BFLogger.logInfo("Step 4 - Setting up PizzaSize field: " + PIZZA_SIZE.getPizzaSizeValue());
		simplePOSTPage.pickPizzaSize(PIZZA_SIZE);
		
		BFLogger.logInfo("Step 5 - Setting up PizzaToppings field: " +
				PIZZA_TOPPING_1.getPizzaToppingValue() + ", " + PIZZA_TOPPING_2.getPizzaToppingValue());
		simplePOSTPage.pickPizzaToppings(PIZZA_TOPPING_1, PIZZA_TOPPING_2);
		
		BFLogger.logInfo("Step 6 - Setting up DeliveryTime field: " + DELIVERY_TIME_HOUR + ":" + DELIVERY_TIME_MINUTE);
		simplePOSTPage.setPrefferedDeliveryTime(DELIVERY_TIME_HOUR, DELIVERY_TIME_MINUTE);
		
		BFLogger.logInfo("Step 7 - Setting up DeliveryInstruction field: " + DELIVERY_INSTRUCTION);
		simplePOSTPage.setDeliveryInstruction(DELIVERY_INSTRUCTION);
		
		BFLogger.logInfo("Step 8 - Submiting the form - sending POST query to " + simplePOSTPage.getEndpoint());
		response = simplePOSTPage.submitForm();
		
		BFLogger.logInfo("Step 9 - Validate response status code (should be 200)");
		assertThat(response.statusCode(), equalTo(200));
		
		BFLogger.logInfo("RESPONSE Body: ");
		response.getBody()
				.prettyPrint();
	}
	
	@Test
	public void responseFormShouldIncludeSevenFields() {
		Map<String, Object> formFields = response.jsonPath()
				.getMap("form");
		
		BFLogger.logInfo("Validate response 'form' - should contains 7 fields");
		assertThat(formFields.size(), equalTo(7));
	}
	
	@Test
	public void validateCorrectnessOfCustomerNameResponse() {
		String customerName = response.path("form.custname");
		
		BFLogger.logInfo("Validate response 'form.custname' - should be equal to: " + CUSTOMER_NAME);
		assertThat(customerName, equalTo(CUSTOMER_NAME));
	}
	
	@Test
	public void validateCorrectnessOfTelephoneResponse() {
		String customerTelephone = response.path("form.custtel");
		
		BFLogger.logInfo("Validate response 'form.custtel' - should be equal to: " + TELEPHONE_NUMBER);
		assertThat(customerTelephone, equalTo(TELEPHONE_NUMBER));
		
		BFLogger.logInfo("Validate response 'form.custtel' - should matches 9 numbers");
		assertThat(customerTelephone, matches("\\d{9}"));
	}
	
	@Test
	public void validateCorrectnessOfEmailAddressResponse() {
		String customerEmailAddress = response.path("form.custemail");
		
		BFLogger.logInfo("Validate response 'form.custemail' - should be equal to: " + EMAIL_ADDRESS);
		assertThat(customerEmailAddress, equalTo(EMAIL_ADDRESS));
		
		BFLogger.logInfo("Validate response 'form.custemail' - should contains '@' char");
		assertThat(customerEmailAddress, StringContains.containsString("@"));
		
		BFLogger.logInfo("Validate response 'form.custemail' - should matches with standard emails");
		assertThat(customerEmailAddress, matches("[a-zA-Z0-9.]+@[a-zA-Z0-9.]+"));
	}
	
	@Test
	public void validateCorrectnessOfPizzaSizeResponse() {
		String pizzaSize = response.path("form.size");
		
		BFLogger.logInfo("Validate response 'form.size' - should be equal to: " + PIZZA_SIZE.getPizzaSizeValue());
		assertThat(pizzaSize, equalTo(PIZZA_SIZE.getPizzaSizeValue()));
	}
	
	@Test
	public void validateCorrectnessOfPizzaToppingsResponse() {
		List<String> pizzaToppings = response.jsonPath()
				.getList("form.topping");
		
		BFLogger.logInfo("Validate response 'form.topping' - should contains less than 4 fields");
		assertThat(pizzaToppings.size(), lessThan(4));
		
		BFLogger.logInfo("Validate response 'form.topping' - should matches with provided Pizza Toppings");
		for (String topping : pizzaToppings) {
			assertThat(topping, matches("(" + PIZZA_TOPPING_1.getPizzaToppingValue() +
					"|" + PIZZA_TOPPING_2.getPizzaToppingValue() + ")"));
		}
	}
	
	@Test
	public void validateCorrectnessOfDeliveryTimeResponse() {
		String deliveryTime = response.path("form.delivery");
		
		BFLogger.logInfo("Validate response 'form.delivery' - should matches with standard HH:MM");
		assertThat(deliveryTime, matches("\\d\\d:\\d\\d"));
		
		BFLogger.logInfo("Validate response 'form.delivery' - should be equal to: " + DELIVERY_TIME_HOUR + ":" + DELIVERY_TIME_MINUTE);
		assertThat(deliveryTime, equalTo(DELIVERY_TIME_HOUR + ":" + DELIVERY_TIME_MINUTE));
		
		BFLogger.logInfo("Validate response 'form.delivery' - delivery time should be provided in open hours (11 - 21)");
		assertThat(DELIVERY_TIME_HOUR, lessThanOrEqualTo(21));
		assertThat(DELIVERY_TIME_HOUR, greaterThanOrEqualTo(11));
		
		assertThat(DELIVERY_TIME_MINUTE, lessThan(60));
		assertThat(DELIVERY_TIME_MINUTE, greaterThanOrEqualTo(0));
	}
	
	@Test
	public void validateCorrectnessOfDeliveryInstructionsResponse() {
		String pizzaSize = response.path("form.comments");
		
		BFLogger.logInfo("Validate response 'form.comments' - should be equal to: " + DELIVERY_INSTRUCTION);
		assertThat(pizzaSize, equalTo(DELIVERY_INSTRUCTION));
	}
}
