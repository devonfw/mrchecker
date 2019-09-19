package com.capgemini.mrchecker.webapi.httpbin;

import com.capgemini.mrchecker.test.core.logger.BFLogger;
import com.capgemini.mrchecker.webapi.BasePageWebApiTest;
import com.capgemini.mrchecker.webapi.core.utils.HTMLParser;
import com.capgemini.mrchecker.webapi.pages.httbin.HtmlPage;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import org.jsoup.select.Elements;
import org.junit.Test;

import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class HtmlTest extends BasePageWebApiTest {
	private HtmlPage htmlPage = new HtmlPage();

	@Test
	public void validateCorrectnessOfHtmlPage() {
		int h1ElementsCount = 1;
		String h1ElementText = "Herman Melville - Moby-Dick";
		int pElementCount = 1;
		int pElementTextLength = 3566;

		BFLogger.logInfo("Step 1 - Sending GET query to " + htmlPage.getEndpoint());
		Response response = htmlPage.getHtmlDocument();

		BFLogger.logInfo("Step 2 - Validate response status code: ");
		assertThat(response.statusCode(), is(200));

		BFLogger.logInfo("Step 3 - Validate response body is html that contains proper h1 tag content (count and value)");
		ResponseBody body = response.body();
		String htmlText = body.asString();
		Elements headings = HTMLParser.getElements(htmlText, "h1");
		List<String> headingsText = HTMLParser.getTextFromElements(headings);
		assertThat(headings.size(), is(h1ElementsCount));
		assertThat(headingsText.get(0), is(h1ElementText));

		BFLogger.logInfo("Step 4 - Validate response body is html that contains proper p tag content (count and length)");
		Elements paragraphs = HTMLParser.getElements(htmlText, "p");
		List<String> paragraphsText = HTMLParser.getTextFromElements(paragraphs);
		assertThat(paragraphs.size(), is(pElementCount));
		assertThat(paragraphsText.get(0).length(), is(pElementTextLength));
	}
}