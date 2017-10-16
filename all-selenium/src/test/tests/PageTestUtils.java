package com.example.selenium.tests.tests;

import java.io.IOException;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.methods.GetMethod;

import com.example.core.base.environments.ParametersManager;
import com.example.core.logger.BFLogger;
import com.example.selenium.core.utils.TimeUtills;
import com.example.selenium.pages.enums.ServicesURLsEnum;

public class PageTestUtils {

	private static int responseStatus = 0;

	public static void logout() {
		// TODO Auto-generated method stub
	}

	public static void waitForLoginServerAvailable() {
		int prevStatus = 0;
		int howLong = 0;
		while (!isLoginServerAvailable()) {
			TimeUtills.waitSeconds(ParametersManager.parameters().getImplicityWaitTimer());
			howLong += ParametersManager.parameters().getImplicityWaitTimer();
			prevStatus = responseStatus;
		}
		if (prevStatus > 0)
			BFLogger.logEnv(
					"There was problem with env. Time wait " + howLong + " sec. . There was status: " + prevStatus);
	}

	private static boolean isLoginServerAvailable() {
		return isServerAvailable(ServicesURLsEnum.WWW_FONT_URL.getAddress());
	}

	private static boolean isServerAvailable(String url) {
		HttpClient client = new HttpClient();
		HttpMethod method = new GetMethod(url);
		try {
			client.executeMethod(method);
		} catch (IOException e1) {
			BFLogger.logError("Connection problem");
			return false;
		}
		responseStatus = method.getStatusCode();
		return responseStatus == 200;
	}

}
