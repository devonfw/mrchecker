package com.capgemini.mrchecker.security.headers;

import static io.restassured.RestAssured.given;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.capgemini.mrchecker.security.EnvironmentParam;
import com.capgemini.mrchecker.security.SecurityTest;
import com.capgemini.mrchecker.security.SubUrlEnum;
import com.capgemini.mrchecker.security.session.SessionEnum;

import io.restassured.specification.RequestSpecification;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;

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
@RunWith(JUnitParamsRunner.class)
public class XContentTypeOptionsTest extends SecurityTest {
	
	private Object[] addParameters() {
		return new Object[][] {
						{ SessionEnum.ANON, EnvironmentParam.SECURITY_CLIENT_ORIGIN, SubUrlEnum.ROOT },
						{ SessionEnum.WAITER, EnvironmentParam.SECURITY_SERVER_ORIGIN, SubUrlEnum.CURRENT_USER },
		};
	}
	
	@Test
	@Parameters(method = "addParameters")
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
				.header("X-Content-Type-Options", "nosniff");
	}
}
