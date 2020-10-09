package com.capgemini.mrchecker.webapi.httpbin;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThanOrEqualTo;
import static org.hamcrest.Matchers.is;

import org.junit.jupiter.api.Test;

import com.capgemini.mrchecker.core.groupTestCases.testSuites.tags.TestsWebApi;
import com.capgemini.mrchecker.test.core.BaseTest;
import com.capgemini.mrchecker.test.core.logger.BFLogger;
import com.capgemini.mrchecker.test.core.utils.PageFactory;
import com.capgemini.mrchecker.webapi.pages.httbin.DelayPage;

import io.restassured.response.Response;

@TestsWebApi
public class DelayTest extends BaseTest {
	
	private final Integer	seconds0	= 0;
	private final Integer	seconds1	= 1;
	private final Integer	seconds10	= 10;
	private final Integer	seconds100	= 100;
	
	private static final int MAX_DELAY_SECONDS = 10;
	
	private enum DelayApiCaller {
		GET {
			@Override
			public Response callDelayApi(String seconds) {
				return DELAY_PAGE.sendDelayGETQuery(seconds);
			}
		},
		POST {
			@Override
			public Response callDelayApi(String seconds) {
				return DELAY_PAGE.sendDelayPOSTQuery(seconds);
			}
		},
		DELETE {
			@Override
			public Response callDelayApi(String seconds) {
				return DELAY_PAGE.sendDelayDELETEQuery(seconds);
			}
		},
		PATCH {
			@Override
			public Response callDelayApi(String seconds) {
				return DELAY_PAGE.sendDelayPATCHQuery(seconds);
			}
		},
		PUT {
			@Override
			public Response callDelayApi(String seconds) {
				return DELAY_PAGE.sendDelayPUTQuery(seconds);
			}
		};
		
		private static final DelayPage DELAY_PAGE;
		static {
			DELAY_PAGE = PageFactory.getPageInstance(DelayPage.class);
		}
		
		public abstract Response callDelayApi(String seconds);
		
		public String getEndpoint() {
			return DELAY_PAGE.getEndpoint();
		}
		
		public String getMethod() {
			return name();
		}
		
	}
	
	@Test
	public void sendGETZeroSecondDelay() {
		sendDelay(DelayApiCaller.GET, seconds0);
	}
	
	@Test
	public void sendGETOneSecondDelay() {
		sendDelay(DelayApiCaller.GET, seconds1);
	}
	
	@Test
	public void sendGETTenSecondDelay() {
		sendDelay(DelayApiCaller.GET, seconds10);
	}
	
	@Test
	public void sendGETOneHundredSecondDelay() {
		sendDelay(DelayApiCaller.GET, seconds100);
	}
	
	@Test
	public void sendPOSTZeroSecondDelay() {
		sendDelay(DelayApiCaller.POST, seconds0);
	}
	
	@Test
	public void sendPOSTOneSecondDelay() {
		sendDelay(DelayApiCaller.POST, seconds1);
	}
	
	@Test
	public void sendPOSTTenSecondDelay() {
		sendDelay(DelayApiCaller.POST, seconds10);
	}
	
	@Test
	public void sendPOSTOneHundredSecondDelay() {
		sendDelay(DelayApiCaller.POST, seconds100);
	}
	
	@Test
	public void sendPUTZeroSecondDelay() {
		sendDelay(DelayApiCaller.PUT, seconds0);
	}
	
	@Test
	public void sendPUTOneSecondDelay() {
		sendDelay(DelayApiCaller.PUT, seconds1);
	}
	
	@Test
	public void sendPUTTenSecondDelay() {
		sendDelay(DelayApiCaller.PUT, seconds10);
	}
	
	@Test
	public void sendPUTOneHundredSecondDelay() {
		sendDelay(DelayApiCaller.PUT, seconds100);
	}
	
	@Test
	public void sendPATCHZeroSecondDelay() {
		sendDelay(DelayApiCaller.PATCH, seconds0);
	}
	
	@Test
	public void sendPATCHOneSecondDelay() {
		sendDelay(DelayApiCaller.PATCH, seconds1);
	}
	
	@Test
	public void sendPATCHTenSecondDelay() {
		sendDelay(DelayApiCaller.PATCH, seconds10);
	}
	
	@Test
	public void sendPATCHOneHundredSecondDelay() {
		sendDelay(DelayApiCaller.PATCH, seconds100);
	}
	
	@Test
	public void sendDELETEZeroSecondDelay() {
		sendDelay(DelayApiCaller.DELETE, seconds0);
	}
	
	@Test
	public void sendDELETEOneSecondDelay() {
		sendDelay(DelayApiCaller.DELETE, seconds1);
	}
	
	@Test
	public void sendDELETETenSecondDelay() {
		sendDelay(DelayApiCaller.DELETE, seconds10);
	}
	
	@Test
	public void sendDELETEOneHundredSecondDelay() {
		sendDelay(DelayApiCaller.DELETE, seconds100);
	}
	
	private void sendDelay(DelayApiCaller delayApiCaller, Integer seconds) {
		BFLogger.logInfo("Step 1 - Sending " + delayApiCaller.getMethod() + " query to " + delayApiCaller.getEndpoint() +
				" with delay of: " + seconds + " second(s)");
		
		long startTime = System.currentTimeMillis();
		Response response = delayApiCaller.callDelayApi(seconds.toString());
		int actualDelay = (int) ((System.currentTimeMillis() - startTime) / 1000);
		int minimumExpectedSeconds = Math.min(seconds, MAX_DELAY_SECONDS);
		int maximumExpectedSeconds = minimumExpectedSeconds + 1;
		BFLogger.logInfo("Step 2 - Assert delay duration within expected lower bound");
		assertThat(actualDelay, greaterThanOrEqualTo(minimumExpectedSeconds));
		// No asserts on upper bound due to service instability, just logging the result.
		if (actualDelay <= maximumExpectedSeconds) {
			BFLogger.logInfo("Step 3 - Delay duration within expected upper bound");
		} else {
			BFLogger.logInfo("Step 3 - Delay duration exceeds expected upper bound");
		}
		
		BFLogger.logInfo("Step 4 - Validate response status code (should be 200): ");
		assertThat(response.statusCode(), is(200));
	}
}