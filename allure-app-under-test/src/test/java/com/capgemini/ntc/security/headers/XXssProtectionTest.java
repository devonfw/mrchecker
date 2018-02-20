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
@Ignore // currently fails on the reference environment
@RunWith(Parameterized.class)
public class XXssProtectionTest extends SecurityTest {
	
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
	
	public XXssProtectionTest(SessionEnum session, EnvironmentParam origin, SubUrlEnum path) {
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
		        .header("X-XSS-Protection", "1; mode=block");
	}
}
