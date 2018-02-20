package com.capgemini.ntc.security.headers;

import static io.restassured.RestAssured.given;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import com.capgemini.ntc.security.EnvironmentParam;
import com.capgemini.ntc.security.SecurityTest;
import com.capgemini.ntc.security.SubUrlEnum;
import com.capgemini.ntc.security.session.SessionEnum;

import io.restassured.specification.RequestSpecification;

/**
 * The test verifies the presence and proper configuration of the
 * X-Content-Type-Options header.
 * OWASP ASVS requirement V11.6: Verify that all API responses contain
 * X-Content-Type-Options: nosniff and Content-Disposition: attachment;
 * filename="api.json" (or other appropriate filename for the content type).
 * Purpose: There is a strong urge for the browser vendors to show, that their browser is
 * better than the competition. This has lead to the point, where the browser is capable
 * of ignoring the content type set by the developer and override it with what the browser
 * sniffed out of the downloaded file content. This might improve the usability in cases,
 * where the dev has set the content type incorrectly, and open door to to attacks performed
 * by polyglot files (e.g. files being both post script documents and javascript).
 * Read also: [1]
 * https://docs.microsoft.com/en-us/previous-versions/windows/internet-explorer/ie-developer/compatibility/gg622941(v=vs.85)
 * 
 * @author Marek Puchalski, Capgemini
 */
@Ignore // currently fails on the reference environment
@RunWith(Parameterized.class)
public class XContentTypeOptionsTest extends SecurityTest {
	
	private SessionEnum				session;
	private SubUrlEnum				path;
	private EnvironmentParam	origin;
	
	@Parameters(name = "{index}: Accessing {1}{2} as {0}, expecting valid header definition")
	public static Collection<Object[]> data() {
		return Arrays.asList(new Object[][] {
		        { SessionEnum.ANON, EnvironmentParam.SECURITY_CLIENT_ORIGIN, SubUrlEnum.ROOT },
		        { SessionEnum.WAITER, EnvironmentParam.SECURITY_SERVER_ORIGIN, SubUrlEnum.CURRENT_USER },
		});
	}
	
	public XContentTypeOptionsTest(SessionEnum session, EnvironmentParam origin, SubUrlEnum path) {
		this.session = session;
		this.origin = origin;
		this.path = path;
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
		        .statusCode(200)
		        .header("X-Content-Type-Options", "nosniff");
	}
}
