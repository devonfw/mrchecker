package com.capgemini.mrchecker.security.access;

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

import io.restassured.specification.RequestSpecification;

/**
 * The test verifies that directory browsing is disabled.
 * OWASP ASVS requirement V4.5: Verify that directory browsing is disabled
 * unless deliberately desired. Additionally, applications should not allow
 * discovery or disclosure of file or directory metadata, such as Thumbs.db,
 * .DS_Store, .git or .svn folders.
 * Purpose: You don't want the attacker to learn about the system more then
 * needed and he certainly does not need to list files in the directories
 * on your server (unless you deliberately desire this feature). Putting
 * private content of your e.g. .git folder into your distribution package
 * to be uploaded to the server and downloaded by the attacker because of
 * directory browsing feature is a mortal sin on its own.
 *
 * @author Marek Puchalski, Capgemini
 */
@TestsSecurity
@Disabled("Can't connect to host")
public class DirectoryBrowsingTest extends SecurityTest {
	
	public static Stream<Arguments> getArguments() {
		return Stream.of(
				Arguments.of(SessionEnum.ANON, EnvironmentParam.SECURITY_CLIENT_ORIGIN, SubUrlEnum.IMG_DIR, HttpStatus.SC_FORBIDDEN),
				Arguments.of(SessionEnum.ANON, EnvironmentParam.SECURITY_SERVER_ORIGIN, SubUrlEnum.REST_ROOT, HttpStatus.SC_FORBIDDEN));
	}
	
	@ParameterizedTest
	@MethodSource("getArguments")
	public void testHeader(SessionEnum session, EnvironmentParam origin, SubUrlEnum path, int statusCode) {
		RequestSpecification rs = getSessionManager()
				.initBuilder(session)
				.setBaseUri(origin.getValue())
				.setBasePath(path.getValue())
				.build();
		given(rs)
				.when()
				.get()
				.then()
				.statusCode(statusCode);
	}
}
