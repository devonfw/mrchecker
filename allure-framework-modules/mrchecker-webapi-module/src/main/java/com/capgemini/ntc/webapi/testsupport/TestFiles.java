package com.capgemini.ntc.webapi.testsupport;

import static com.github.tomakehurst.wiremock.common.Exceptions.throwUnchecked;

import java.io.IOException;

import com.google.common.base.Charsets;
import com.google.common.io.Resources;

public class TestFiles {
	
	public static String file(String path) {
		try {
			return Resources.toString(Resources.getResource(path), Charsets.UTF_8);
		} catch (IOException e) {
			return throwUnchecked(e, String.class);
		}
	}
}
