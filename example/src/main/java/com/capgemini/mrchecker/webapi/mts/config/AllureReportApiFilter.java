package com.capgemini.mrchecker.webapi.mts.config;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import org.json.JSONArray;
import org.json.JSONObject;

import io.qameta.allure.attachment.DefaultAttachmentProcessor;
import io.qameta.allure.attachment.FreemarkerAttachmentRenderer;
import io.qameta.allure.attachment.http.HttpResponseAttachment;
import io.restassured.filter.FilterContext;
import io.restassured.filter.OrderedFilter;
import io.restassured.internal.NameAndValue;
import io.restassured.internal.support.Prettifier;
import io.restassured.parsing.Parser;
import io.restassured.response.Response;
import io.restassured.specification.FilterableRequestSpecification;
import io.restassured.specification.FilterableResponseSpecification;

/**
 * Original file at io.qameta.allure.restassured.AllureRestAssured
 */
public class AllureReportApiFilter implements OrderedFilter {
	private final List<String>	RESTRICTED_DATA			= Arrays.asList("password", "api-key");
	private String				requestTemplatePath		= "http-request.ftl";
	private String				responseTemplatePath	= "http-response.ftl";
	
	private static Map<String, String> toMapConverter(Iterable<? extends NameAndValue> items) {
		Map<String, String> result = new HashMap<>();
		items.forEach((h) -> {
			String var10000 = result.put(h.getName(), h.getValue());
		});
		return result;
	}
	
	public AllureReportApiFilter setRequestTemplate(String templatePath) {
		this.requestTemplatePath = templatePath;
		return this;
	}
	
	public AllureReportApiFilter setResponseTemplate(String templatePath) {
		this.responseTemplatePath = templatePath;
		return this;
	}
	
	public Response filter(FilterableRequestSpecification requestSpec, FilterableResponseSpecification responseSpec, FilterContext filterContext) {
		Prettifier prettifier = new Prettifier();
		Map<String, String> headers = toMapConverter(requestSpec.getHeaders());
		for (String key : headers.keySet()) {
			if (RESTRICTED_DATA.contains(key.toLowerCase())) {
				headers.put(key, "****************");
			}
		}
		HttpRequestAttachment.Builder requestAttachmentBuilder = HttpRequestAttachment.Builder.create("[REQUEST]", requestSpec.getURI())
				.setMethod(requestSpec.getMethod())
				.setHeaders(headers)
				.setCookies(toMapConverter(requestSpec.getCookies()))
				.setFormParams(requestSpec.getFormParams())
				.setMultiPartFormData(requestSpec.getMultiPartParams());
		Object body = requestSpec.getBody();
		if (Objects.nonNull(body)) {
			if (body.toString()
					.startsWith("[")) {
				JSONArray jsonArray = new JSONArray(body.toString());
				requestAttachmentBuilder.setBody(prettifier.prettify(jsonArray.toString(), Parser.JSON));
			} else {
				JSONObject jsonifiedBody = new JSONObject(body.toString());
				if (jsonifiedBody.has("password")) {
					jsonifiedBody.put("password", "****************");
				}
				requestAttachmentBuilder.setBody(prettifier.prettify(jsonifiedBody.toString(), Parser.JSON));
			}
		}
		
		HttpRequestAttachment requestAttachment = requestAttachmentBuilder.build();
		(new DefaultAttachmentProcessor()).addAttachment(requestAttachment, new FreemarkerAttachmentRenderer(this.requestTemplatePath));
		
		Response response = filterContext.next(requestSpec, responseSpec);
		HttpResponseAttachment responseAttachment = io.qameta.allure.attachment.http.HttpResponseAttachment.Builder.create("[RESPONSE] " + response.getStatusLine())
				.setResponseCode(response.getStatusCode())
				.setHeaders(toMapConverter(response.getHeaders()))
				.setBody(prettifier.getPrettifiedBodyIfPossible(response, response.getBody()))
				.build();
		(new DefaultAttachmentProcessor()).addAttachment(responseAttachment, new FreemarkerAttachmentRenderer(this.responseTemplatePath));
		return response;
	}
	
	public int getOrder() {
		return 2147483647;
	}
}
