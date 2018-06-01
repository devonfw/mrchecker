package com.capgemini.mrchecker.security.access;

import static io.restassured.RestAssured.given;

import java.util.Arrays;
import java.util.Collection;

import org.apache.http.HttpStatus;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

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
@RunWith(Parameterized.class)
public class DirectoryBrowsingTest extends SecurityTest {
	
	private SessionEnum				session;
	private SubUrlEnum				path;
	private EnvironmentParam	origin;
	private int								statusCode;
	
	@Parameters(name = "{index}: Accessing {1}{2} as {0}, expecting HTTP {3}")
	public static Collection<Object[]> data() {
		return Arrays.asList(new Object[][] {
		        { SessionEnum.ANON, EnvironmentParam.SECURITY_CLIENT_ORIGIN, SubUrlEnum.IMG_DIR, HttpStatus.SC_FORBIDDEN },
		        { SessionEnum.ANON, EnvironmentParam.SECURITY_SERVER_ORIGIN, SubUrlEnum.REST_ROOT, HttpStatus.SC_FORBIDDEN },
		});
	}
	
	public DirectoryBrowsingTest(SessionEnum session, EnvironmentParam origin, SubUrlEnum path, int statusCode) {
		this.session = session;
		this.origin = origin;
		this.path = path;
		this.statusCode = statusCode;
	}
	
	@Test
	public void testHeader() {
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
