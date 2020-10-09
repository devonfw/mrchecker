package com.capgemini.mrchecker.webapi.mts.config;

import static org.awaitility.Awaitility.await;

import java.text.MessageFormat;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.json.JSONArray;
import org.json.JSONObject;

import com.capgemini.mrchecker.test.core.logger.BFLogger;
import com.capgemini.mrchecker.webapi.mts.pages.MTSWebApiBasePage;

import io.restassured.filter.Filter;
import io.restassured.filter.FilterContext;
import io.restassured.http.Header;
import io.restassured.response.Response;
import io.restassured.specification.FilterableRequestSpecification;
import io.restassured.specification.FilterableResponseSpecification;

public class CustomFilter implements Filter {
	
	private static final List RESTRICTED_DATA = Arrays.asList("password", "api-key");
	
	@Override
	public Response filter(FilterableRequestSpecification requestSpec, FilterableResponseSpecification responseSpec, FilterContext ctx) {
		if (System.getProperty("debug") != null) {
			
			String message = MessageFormat.format("\n[REQUEST]\n\tMETHOD:\n\t\t{0}\n\tURI:\n\t\t{1}\n\tHEADERS:", requestSpec.getMethod(), requestSpec.getURI());
			for (Header header : requestSpec.getHeaders()) {
				Header header1 = header;
				if (RESTRICTED_DATA.contains(header1.getName()
						.toLowerCase())) {
					header1 = new Header(header1.getName(), "*****************");
				}
				message += MessageFormat.format("\n\t\t{0}", header1);
			}
			message += "\n\tPARAMETERS:";
			Map<String, String> params = requestSpec.getFormParams();
			for (String key : params.keySet()) {
				message += MessageFormat.format("\n\t\t{0}: {1}", key, params.get(key));
			}
			message += "\n\tREQUEST BODY:\n";
			
			Object o = requestSpec.getBody();
			try {
				
				if (o != null) {
					if (o.toString()
							.startsWith("[")) {
						JSONArray jsonArray = new JSONArray(requestSpec.getBody()
								.toString());
						message += jsonArray.toString(8);
					} else {
						JSONObject json = new JSONObject(requestSpec.getBody()
								.toString()); // Convert text to object
						if (json.has("password")) {
							json.put("password", "*****************");
						}
						message += json.toString(8);
					}
				}
			} catch (Exception e) {
				message = o.toString();
			}
			BFLogger.logDebug(message);
			Response r = ctx.next(requestSpec, responseSpec);
			
			await().atMost(MTSWebApiBasePage.SERVER_RESPONSE_TIMEOUT_SEC, TimeUnit.SECONDS)
					.until(() -> r.thenReturn() != null);
			BFLogger.logDebug("\n[RESPONSE]");
			r.getBody()
					.prettyPeek();
			return r;
		} else {
			Response r = ctx.next(requestSpec, responseSpec);
			
			await().atMost(MTSWebApiBasePage.SERVER_RESPONSE_TIMEOUT_SEC, TimeUnit.SECONDS)
					.until(() -> r.thenReturn() != null);
			return r;
		}
	}
}
