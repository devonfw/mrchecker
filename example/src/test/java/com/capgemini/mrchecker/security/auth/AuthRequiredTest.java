package com.capgemini.mrchecker.security.auth;

import static io.restassured.RestAssured.given;

import org.apache.http.HttpStatus;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.capgemini.mrchecker.security.EnvironmentParam;
import com.capgemini.mrchecker.security.SecurityTest;
import com.capgemini.mrchecker.security.SubUrlEnum;
import com.capgemini.mrchecker.security.session.SessionEnum;

import io.restassured.http.Method;
import io.restassured.specification.RequestSpecification;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;

/**
 * The test verifies, that protected resources can not be accessed by unauthenticated
 * users.
 * OWASP ASVS requirement V2.1: Verify all pages and resources by default
 * require authentication except those specifically intended to be public
 * (Principle of complete mediation)
 * Purpose: This is one of the most basic protection mechanisms. Unit test
 * verifies only a part of this requirement (resources that require authentication -
 * enforce authentication). The second part of this requirement ("by default
 * require") must be validated manually.
 * Read also:
 * [1]
 * https://www.owasp.org/index.php/Authentication_Cheat_Sheet
 *
 * @author Marek Puchalski, Capgemini
 */
@RunWith(JUnitParamsRunner.class)
public class AuthRequiredTest extends SecurityTest {
	
	private Object[] addParameters() {
		String body = "{\"pagination\":{\"size\":8,\"page\":1,\"total\":1},\"sort\":[]}";
		return new Object[][] {
						// Negative case
						{ SessionEnum.ANON, EnvironmentParam.SECURITY_SERVER_ORIGIN, SubUrlEnum.ORDER_SEARCH,
										body, HttpStatus.SC_FORBIDDEN
						},
						// Positive case
						{ SessionEnum.WAITER, EnvironmentParam.SECURITY_SERVER_ORIGIN, SubUrlEnum.ORDER_SEARCH,
										body, HttpStatus.SC_OK
						}
		};
		
	}
	
	@Test
	@Parameters(method = "addParameters")
	public void testHeader(SessionEnum session, EnvironmentParam origin, SubUrlEnum path, String body, int statusCode) {
		RequestSpecification rs = getSessionManager()
				.initBuilder(session)
				.setBaseUri(origin.getValue())
				.setBasePath(path.getValue())
				.addHeader("Content-Type", "application/json")
				.setBody(body)
				.build();
		given(rs)
				.when()
				.request(Method.POST)
				.then()
				.statusCode(statusCode);
	}
}
