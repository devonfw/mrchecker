package com.capgemini.mrchecker.webapi.virtualization.soap;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

import com.capgemini.mrchecker.webapi.core.BasePageWebAPI;

/**
 * SOAP endpoint for a SOAP web service that matches a response body with the following SOAP envelope:
 * <?xml version="1.0" encoding="utf-8"?>
 * <soap12:Envelope xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns:xsd=
 * "http://www.w3.org/2001/XMLSchema" xmlns:soap12="http://www.w3.org/2003/05/soap-envelope">
 * <soap12:Body>
 * <FahrenheitToCelsius>
 * <Fahrenheit>100</Fahrenheit>
 * </FahrenheitToCelsius>
 * </soap12:Body>
 * </soap12:Envelope>
 **/
public class FarenheitToCelsiusMethod_Request_FromFile extends BasePageWebAPI {
	
	/*
	 * SOAP response built from Java code
	 */
	// optional parameters
	private String path = "/src/test/resources/soapInput/farenheittocelsius/samplerequest.xml";
	
	public FarenheitToCelsiusMethod_Request_FromFile() {
	}
	
	public FarenheitToCelsiusMethod_Request_FromFile setFilePath(String path) {
		this.path = path;
		return this;
	}
	
	public String getMessage() {
		String message = "";
		try {
			message = getStringOutOfFile(this.path);
		} catch (IOException e) {
			new Exception(e);
		}
		return message;
	}
	
	/*
	 * ------------
	 * Util methods
	 * ------------
	 */
	
	private String getStringOutOfFile(final String filePath) throws IOException {
		String path = System.getProperty("user.dir") + Paths.get(filePath);
		if (!exists(path)) {
			throw new IOException("Could not find file. Path='" + path + "' does not exist");
		}
		
		String responseEnvelope = readFile(path, StandardCharsets.UTF_8);
		return responseEnvelope;
	}
	
	private boolean exists(String path) {
		File f = new File(path);
		return f.exists();
	}
	
	private String readFile(String path, Charset encoding) throws IOException {
		byte[] encoded = Files.readAllBytes(Paths.get(path));
		return new String(encoded, encoding);
	}
	
	public String getEndpoint() {
		// Useful only for REST Tests
		return null;
	}
}