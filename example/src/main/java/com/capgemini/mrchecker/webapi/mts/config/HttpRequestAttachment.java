package com.capgemini.mrchecker.webapi.mts.config;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import io.qameta.allure.attachment.AttachmentData;
import io.restassured.specification.MultiPartSpecification;

public class HttpRequestAttachment implements AttachmentData {
	private final String				name;
	private final String				url;
	private final String				method;
	private final String				body;
	private final String				curl;
	private final Map<String, String>	headers;
	private final Map<String, String>	cookies;
	private final Map<String, String>	formParams;
	private final Map<String, Object>	multiPartFormData;
	
	public HttpRequestAttachment(String name,
			String url,
			String method,
			String body,
			String curl,
			Map<String, String> headers,
			Map<String, String> cookies,
			Map<String, String> formParams,
			Map<String, Object> multiPartFormData) {
		this.name = name;
		this.url = url;
		this.method = method;
		this.body = body;
		this.curl = curl;
		this.headers = headers;
		this.cookies = cookies;
		this.formParams = formParams;
		this.multiPartFormData = multiPartFormData;
	}
	
	public String getUrl() {
		return this.url;
	}
	
	public String getMethod() {
		return this.method;
	}
	
	public String getBody() {
		return this.body;
	}
	
	public Map<String, String> getHeaders() {
		return this.headers;
	}
	
	public Map<String, String> getCookies() {
		return this.cookies;
	}
	
	public Map<String, String> getFormParams() {
		return this.formParams;
	}
	
	public Map<String, Object> getMultiPartFormData() {
		return this.multiPartFormData;
	}
	
	public String getCurl() {
		return this.curl;
	}
	
	public String getName() {
		return this.name;
	}
	
	public static final class Builder {
		private final String				name;
		private final String				url;
		private final Map<String, String>	headers				= new HashMap();
		private final Map<String, String>	cookies				= new HashMap();
		private final Map<String, String>	formParams			= new HashMap();
		private final Map<String, Object>	multiPartFormData	= new HashMap();
		private String						method;
		private String						body;
		
		private Builder(String name, String url) {
			Objects.requireNonNull(name, "Name must not be null value");
			Objects.requireNonNull(url, "Url must not be null value");
			this.name = name;
			this.url = url;
		}
		
		public static HttpRequestAttachment.Builder create(String attachmentName, String url) {
			return new HttpRequestAttachment.Builder(attachmentName, url);
		}
		
		private static void appendHeader(StringBuilder builder, String key, String value) {
			builder.append(" -H '")
					.append(key)
					.append(": ")
					.append(value)
					.append('\'');
		}
		
		private static void appendCookie(StringBuilder builder, String key, String value) {
			builder.append(" -b '")
					.append(key)
					.append('=')
					.append(value)
					.append('\'');
		}
		
		private static void appendFormParam(StringBuilder builder, String key, String value) {
			builder.append(" -F '")
					.append(key)
					.append('=')
					.append(value)
					.append('\'');
		}
		
		public HttpRequestAttachment.Builder setMethod(String method) {
			Objects.requireNonNull(method, "Method must not be null value");
			this.method = method;
			return this;
		}
		
		public HttpRequestAttachment.Builder setHeader(String name, String value) {
			Objects.requireNonNull(name, "Header name must not be null value");
			Objects.requireNonNull(value, "Header value must not be null value");
			this.headers.put(name, value);
			return this;
		}
		
		public HttpRequestAttachment.Builder setHeaders(Map<String, String> headers) {
			Objects.requireNonNull(headers, "Headers must not be null value");
			this.headers.putAll(headers);
			return this;
		}
		
		public HttpRequestAttachment.Builder setCookie(String name, String value) {
			Objects.requireNonNull(name, "Cookie name must not be null value");
			Objects.requireNonNull(value, "Cookie value must not be null value");
			this.cookies.put(name, value);
			return this;
		}
		
		public HttpRequestAttachment.Builder setFormParam(String name, String value) {
			Objects.requireNonNull(name, "Form Parameter name must not be null value");
			Objects.requireNonNull(value, "Form Parameter must not be null value");
			this.formParams.put(name, value);
			return this;
		}
		
		public HttpRequestAttachment.Builder setFormParams(Map<String, String> formParams) {
			Objects.requireNonNull(cookies, "Form Parameters must not be null value");
			this.formParams.putAll(formParams);
			return this;
		}
		
		public HttpRequestAttachment.Builder setMultiPartFormData(List<MultiPartSpecification> multiPartFormData) {
			Objects.requireNonNull(cookies, "Multi Part Data must not be null value");
			for (MultiPartSpecification multiPart : multiPartFormData) {
				this.multiPartFormData.put(multiPart.getMimeType(), ((File) multiPart.getContent()).getName());
			}
			return this;
		}
		
		public HttpRequestAttachment.Builder setCookies(Map<String, String> cookies) {
			Objects.requireNonNull(cookies, "Cookies must not be null value");
			this.cookies.putAll(cookies);
			return this;
		}
		
		public HttpRequestAttachment.Builder setBody(String body) {
			Objects.requireNonNull(body, "Body should not be null value");
			this.body = body;
			return this;
		}
		
		public HttpRequestAttachment build() {
			return new HttpRequestAttachment(this.name, this.url, this.method, this.body, this.getCurl(), this.headers, this.cookies, this.formParams,
					this.multiPartFormData);
		}
		
		private String getCurl() {
			StringBuilder builder = new StringBuilder("curl -v");
			if (Objects.nonNull(this.method)) {
				builder.append(" -X ")
						.append(this.method);
			}
			
			builder.append(" '")
					.append(this.url)
					.append('\'');
			this.headers.forEach((key, value) -> appendHeader(builder, key, value));
			this.cookies.forEach((key, value) -> appendCookie(builder, key, value));
			this.formParams.forEach((key, value) -> appendFormParam(builder, key, value));
			if (Objects.nonNull(this.body)) {
				builder.append(" -d '")
						.append(this.body)
						.append('\'');
			}
			
			return builder.toString();
		}
	}
}
