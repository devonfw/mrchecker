package com.capgemini.mrchecker.webapi.httpbin;

import com.capgemini.mrchecker.test.core.logger.BFLogger;
import com.capgemini.mrchecker.webapi.BasePageWebApiTest;
import com.capgemini.mrchecker.webapi.core.utils.HTMLParser;
import com.capgemini.mrchecker.webapi.pages.httbin.LinksPage;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.jsoup.select.Elements;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.text.MessageFormat;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

@RunWith(JUnitParamsRunner.class)
public class HtmlLinksTest extends BasePageWebApiTest {
	private LinksPage linksPage = new LinksPage();

	@Test
	@Parameters({
			"-1, 5",
			"5,  -1",
			"-1, -1"
	})
	public void sendGetWithNegativeParamAndValidateNotFoundStatusCode(int n, int offset) {
		BFLogger.logInfo(MessageFormat.format("Step 1 - Sending GET query to {0} with positive n param: {1} and negative offset: {2}", linksPage.getEndpoint(), n, offset));
		Response response = linksPage.getHtmlDocument(n, offset);

		BFLogger.logInfo("Step 2 - Validate response status code (should be 404): ");
		assertThat(response.statusCode(), is(404));
	}

	@Test
	@Parameters({
			"10, 5",
			"5,  10",
			"10, 10",
			"0,  5",
			"5,  0",
			"0, 0"
	})
	public void sendGetWithPositiveParamsAndValidateHTMLPage(int n, int offset) {
		BFLogger.logInfo(MessageFormat.format("Step 1 - Sending GET query to {0} with n param: {1} and offset: {2}", linksPage.getEndpoint(), n, offset));
		Response response = linksPage.getHtmlDocument(n, offset);

		BFLogger.logInfo("Step 2 - Validate response status code (should be 200): ");
		assertThat(response.statusCode(), is(200));

		//When n is equal 0 there is still one link
		int initLinkCount = n == 0 ? 1 : n;

		//Offset cuts count of links if it is lower than n
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

		//When offset cuts some link
		if (offset < n) {
			BFLogger.logInfo(MessageFormat.format("Step 6 - Validate that there is text equal to offset: {0}", offset));
			String bodyText = HTMLParser.getElements(htmlText, "body").first().ownText();
			assertThat(bodyText, is(String.valueOf(offset)));
		}
	}
}