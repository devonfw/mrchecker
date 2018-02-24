package com.capgemini.ntc.webapi.wiremock;

import static com.github.tomakehurst.wiremock.common.HttpClientUtils.getEntityAsByteArrayAndCloseStream;
import static com.google.common.base.Charsets.UTF_8;
import static com.google.common.collect.Iterables.getFirst;

import java.nio.charset.Charset;

import org.apache.http.Header;
import org.apache.http.HttpResponse;

import com.google.common.collect.ImmutableListMultimap;
import com.google.common.collect.Multimap;

public class WireMockResponse {
	
	private final HttpResponse	httpResponse;
	private final byte[]		content;
	
	public WireMockResponse(HttpResponse httpResponse) {
		this.httpResponse = httpResponse;
		content = getEntityAsByteArrayAndCloseStream(httpResponse);
	}
	
	public int statusCode() {
		return httpResponse.getStatusLine()
				.getStatusCode();
	}
	
	public String content() {
		if (content == null) {
			return null;
		}
		return new String(content, Charset.forName(UTF_8.name()));
	}
	
	public byte[] binaryContent() {
		return content;
	}
	
	public String firstHeader(String key) {
		return getFirst(headers().get(key), null);
	}
	
	public Multimap<String, String> headers() {
		ImmutableListMultimap.Builder<String, String> builder = ImmutableListMultimap.builder();
		
		for (Header header : httpResponse.getAllHeaders()) {
			builder.put(header.getName(), header.getValue());
		}
		
		return builder.build();
	}
	
	public String statusMessage() {
		return httpResponse.getStatusLine()
				.getReasonPhrase();
	}
}
