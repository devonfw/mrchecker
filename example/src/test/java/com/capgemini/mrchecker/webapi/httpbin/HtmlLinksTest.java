package com.capgemini.mrchecker.webapi.httpbin;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import java.text.MessageFormat;
import java.util.List;
import java.util.stream.Stream;

import org.jsoup.select.Elements;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import com.capgemini.mrchecker.core.groupTestCases.testSuites.tags.TestsWebApi;
import com.capgemini.mrchecker.test.core.BaseTest;
import com.capgemini.mrchecker.test.core.logger.BFLogger;
import com.capgemini.mrchecker.test.core.utils.PageFactory;
import com.capgemini.mrchecker.webapi.core.utils.HTMLParser;
import com.capgemini.mrchecker.webapi.pages.httbin.LinksPage;

import io.restassured.response.Response;
import io.restassured.response.ResponseBody;

@TestsWebApi
public class HtmlLinksTest extends BaseTest {
	private static LinksPage linksPage;
	
	@BeforeAll
	public static void setUpClass() {
		linksPage = PageFactory.getPageInstance(LinksPage.class);
	}
	
	private static Stream<Arguments> getArguments1() {
		return Stream.of(Arguments.of(-1, 5),
				Arguments.of(5, -1),
				Arguments.of(-1, -1));
	}
	
	private static Stream<Arguments> getArguments2() {
		return Stream.of(Arguments.of(10, 5),
				Arguments.of(5, 10),
				Arguments.of(10, 10),
				Arguments.of(5, 0),
				Arguments.of(0, 5),
				Arguments.of(0, 0));
	}
	
	@ParameterizedTest
	@MethodSource("getArguments1")
	public void sendGetWithNegativeParamAndValidateNotFoundStatusCode(int n, int offset) {
		BFLogger.logInfo(MessageFormat.format("Step 1 - Sending GET query to {0} with positive n param: {1} and negative offset: {2}", linksPage.getEndpoint(), n, offset));
		Response response = linksPage.getHtmlDocument(n, offset);
		
		BFLogger.logInfo("Step 2 - Validate response status code (should be 404): ");
		assertThat(response.statusCode(), is(404));
	}
	
	@ParameterizedTest
	@MethodSource("getArguments2")
	public void sendGetWithPositiveParamsAndValidateHTMLPage(int n, int offset) {
		BFLogger.logInfo(MessageFormat.format("Step 1 - Sending GET query to {0} with n param: {1} and offset: {2}", linksPage.getEndpoint(), n, offset));
		Response response = linksPage.getHtmlDocument(n, offset);
		
		BFLogger.logInfo("Step 2 - Validate response status code (should be 200): ");
		assertThat(response.statusCode(), is(200));
		
		// When n is equal 0 there is still one link
		int initLinkCount = n == 0 ? 1 : n;
		
		// Offset cuts count of links if it is lower than n
		int properLinkCount = offset >= initLinkCount ? initLinkCount : initLinkCount - 1;
		BFLogger.logInfo("Step 3 - Validate links in html response - count should be " + properLinkCount);
		ResponseBody body = response.body();
		String htmlText = body.asString();
		Elements links = HTMLParser.getElements(htmlText, "a");
		
		assertThat(links.size(), is(properLinkCount));
		
		BFLogger.logInfo("Step 4 - Validate links in html response - text");
		List<String> linksText = HTMLParser.getTextFromElements(links);
		linksText.forEach(link -> {
			int j = linksText.indexOf(link);
			if (j >= offset) {
				j++;
			}
			assertThat(link, is(String.valueOf(j)));
		});
		
		BFLogger.logInfo("Step 5 - Validate links in html response - href attribute");
		List<String> linksLink = HTMLParser.getAttributeValueFromElements(links, "href");
		linksLink.forEach(link -> {
			int j = linksLink.indexOf(link);
			if (j >= offset) {
				j++;
			}
			assertThat(link, is(MessageFormat.format("/links/{0}/{1}", initLinkCount, j)));
		});
		
		// When offset cuts some link
		if (offset < n) {
			BFLogger.logInfo(MessageFormat.format("Step 6 - Validate that there is text equal to offset: {0}", offset));
			String bodyText = HTMLParser.getElements(htmlText, "body")
					.first()
					.ownText();
			assertThat(bodyText, is(String.valueOf(offset)));
		}
	}
}