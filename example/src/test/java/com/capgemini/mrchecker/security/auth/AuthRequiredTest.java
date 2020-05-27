package com.capgemini.mrchecker.security.auth;

import static io.restassured.RestAssured.given;

import java.util.stream.Stream;

import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import com.capgemini.mrchecker.core.groupTestCases.testSuites.tags.TestsSecurity;
import com.capgemini.mrchecker.security.EnvironmentParam;
import com.capgemini.mrchecker.security.SecurityTest;
import com.capgemini.mrchecker.security.SubUrlEnum;
import com.capgemini.mrchecker.security.session.SessionEnum;

import io.restassured.http.Method;
import io.restassured.specification.RequestSpecification;

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

@TestsSecurity
@Disabled("Can't connect to host")
public class AuthRequiredTest extends SecurityTest {
	
	private static Stream<Arguments> getArguments() {
		
		String body = "{\"pagination\":{\"size\":8,\"page\":1,\"total\":1},\"sort\":[]}";
		return Stream.of(
				// Negative case
				Arguments.of(SessionEnum.ANON, EnvironmentParam.SECURITY_SERVER_ORIGIN, SubUrlEnum.ORDER_SEARCH, body, HttpStatus.SC_FORBIDDEN),
				// Positive case
				Arguments.of(SessionEnum.WAITER, EnvironmentParam.SECURITY_SERVER_ORIGIN, SubUrlEnum.ORDER_SEARCH, body, HttpStatus.SC_OK));
	}
	
	@ParameterizedTest
	@MethodSource("getArguments")
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
