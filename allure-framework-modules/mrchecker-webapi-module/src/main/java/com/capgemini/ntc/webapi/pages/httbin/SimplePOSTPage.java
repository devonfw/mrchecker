package com.capgemini.ntc.webapi.pages.httbin;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.capgemini.ntc.webapi.core.BasePageWebAPI;
import com.capgemini.ntc.webapi.core.base.driver.DriverManager;
import com.capgemini.ntc.webapi.pages.environment.GetEnvironmentParam;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class SimplePOSTPage extends BasePageWebAPI {
	
	private final static String	HOSTNAME	= GetEnvironmentParam.HTTPBIN.getValue();
	private final static String	PATH		= "/post";
	private final static String	ENDPOINT	= HOSTNAME + PATH;
	
	private static Map<String, Object> requestFields = new HashMap<>();
	
	static {
		requestFields.put("custname", "");
		requestFields.put("custtel", "");
		requestFields.put("custemail", "");
		requestFields.put("comments", "");
		requestFields.put("delivery", "");
		requestFields.put("size", "");
		requestFields.put("topping", "");
	}
	
	public Response sendPOSTQuery() {
		// BFLogger.logInfo(Arrays.toString(requestFields.entrySet()
		// .toArray()));
		return DriverManager.getDriverWebAPI()
						.contentType(ContentType.URLENC)
						.formParams(requestFields)
						.when()
						.post(ENDPOINT);
	}
	
	@Override
	public String getEndpoint() {
		return ENDPOINT;
	}
	
	public void setCustomerName(String customerName) {
		requestFields.replace("custname", customerName);
	}
	
	public void setTelephoneNumber(String telephoneNumber) {
		requestFields.replace("custtel", telephoneNumber);
	}
	
	public void setEmailAddress(String emailAddress) {
		requestFields.replace("custemail", emailAddress);
	}
	
	public void pickPizzaSize(PizzaSize pizzaSize) {
		requestFields.replace("size", pizzaSize.getPizzaSizeValue());
	}
	
	public void pickPizzaToppings(PizzaToppings... pizzaToppings) {
		List<PizzaToppings> toppings = new ArrayList<>(Arrays.asList(pizzaToppings));
		List<String> nb = toppings.stream()
						.map(element -> element.getPizzaToppingValue())
						.collect(Collectors.toList());
		requestFields.replace("topping", nb);
		
	}
	
	public void setPrefferedDeliveryTime(int hours, int minutes) {
		requestFields.replace("delivery", hours + ":" + minutes);
	}
	
	public void setDeliveryInstruction(String deliveryInstruction) {
		requestFields.put("comments", deliveryInstruction);
	}
	
	public enum PizzaSize {
		SMALL("small"),
		MEDIUM("medium"),
		LARGE("large");
		
		private String pizzaSizeValue;
		
		PizzaSize(String pizzaSizeValue) {
			this.pizzaSizeValue = pizzaSizeValue;
		}
		
		public String getPizzaSizeValue() {
			return pizzaSizeValue;
		}
	}
	
	public enum PizzaToppings {
		BACON("bacon"),
		EXTRA_CHEESE("cheese"),
		ONION("onion"),
		MUSHROOM("mushroom");
		
		private String pizzaToppingValue;
		
		PizzaToppings(String pizzaToppingValue) {
			this.pizzaToppingValue = pizzaToppingValue;
		}
		
		public String getPizzaToppingValue() {
			return pizzaToppingValue;
		}
	}
	
}