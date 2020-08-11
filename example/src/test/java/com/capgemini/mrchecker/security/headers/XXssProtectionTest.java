package com.capgemini.mrchecker.security.headers;

import static io.restassured.RestAssured.given;

import java.util.stream.Stream;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import com.capgemini.mrchecker.core.groupTestCases.testSuites.tags.TestsSecurity;
import com.capgemini.mrchecker.security.EnvironmentParam;
import com.capgemini.mrchecker.security.SecurityTest;
import com.capgemini.mrchecker.security.SubUrlEnum;
import com.capgemini.mrchecker.security.session.SessionEnum;

import io.restassured.specification.RequestSpecification;

/**
 * The test verifies the presence and proper configuration of the
 * X-XSS-Protection header.
 * OWASP ASVS requirement V11.8: Verify that the X-XSS-Protection: 1; mode=block
 * header is in place.
 * Purpose: The header in this form may prevent some cases of reflected XSS. It
 * is supported by the IE [1] and Chrome-based browsers. It is turned on by
 * default, but the header like "1; mode=block" enforces the more rigorous mode
 * where the rendering of the whole page is stopped. This is the preferred mode,
 * as the less rigorous one can introduce vulnerabilities that would not exist
 * otherwise [2].
 * Read also: [1]
 * https://blogs.msdn.microsoft.com/ieinternals/2011/01/31/controlling-the-xss-filter/
 * [2] https://www.slideshare.net/masatokinugawa/xxn-en [3]
 * https://www.quora.com/How-effective-is-x-xss-protection-response-header
 *
 * @author Marek Puchalski, Capgemini
 */

@TestsSecurity
@Disabled("Can't connect to host")
public class XXssProtectionTest extends SecurityTest {
	
	public static Stream<Arguments> getArguments() {
		return Stream.of(
				Arguments.of(SessionEnum.ANON, EnvironmentParam.SECURITY_CLIENT_ORIGIN, SubUrlEnum.ROOT),
				Arguments.of(SessionEnum.WAITER, EnvironmentParam.SECURITY_SERVER_ORIGIN, SubUrlEnum.CURRENT_USER));
	}
	
	@ParameterizedTest
	@MethodSource("getArguments")
	public void testHeader(SessionEnum session, EnvironmentParam origin, SubUrlEnum path) {
		RequestSpecification rs = getSessionManager()
				.initBuilder(session)
				.setBaseUri(origin.getValue())
				.setBasePath(path.getValue())
				.build();
		given(rs)
				.when()
				.get()
				.then()
				.statusCode(200)
				.header("X-XSS-Protection", "1; mode=block");
	}
}
