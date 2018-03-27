package com.capgemini.ntc.endpoint.soap;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

import com.capgemini.ntc.test.core.logger.BFLogger;
import com.capgemini.ntc.webapi.core.BasePageWebAPI;

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
 * 
 * @throws IOException
 **/
public class FarenheitToCelsiusMethod_Request_FromFile extends BasePageWebAPI {
	
	/*
	 * Build SOAP response builded from Java code
	 */
	
	// Builder Class
	public static class Builder {
		// optional parameters
		private String path = "/src/test/resources/soapInput/farenheittocelsius/samplerequest.xml";
		
		/**
		 * @param fahrenheit
		 * @return Structure
		 */
		public Builder setFilePath(String path) {
			this.path = path;
			return this;
		}
		
		/**
		 * @return Generate SOAP response as String
		 * @throws IOException
		 */
		public String build() throws IOException {
			return new FarenheitToCelsiusMethod_Request_FromFile(this).build();
			
		}
	}
	
	private String path;
	
	private FarenheitToCelsiusMethod_Request_FromFile(Builder builder) {
		this.path = builder.path;
	}
	
	private String build() throws IOException {
		return getStringOutOfFile(this.path);
	}
	
	/*
	 * ------------
	 * Util methods
	 * ------------
	 */
	
	private String getStringOutOfFile(final String filePath) throws IOException {
		String path = System.getProperty("user.dir") + Paths.get(filePath);
		if (!exists(path)) {
			BFLogger.logError("Could not find file. Path='" + path + "' does not exist");
			throw new IOException("Could not find file. Path='" + path + "' does not exist");
		}
		
		String responseEnvelope = readFile(path, StandardCharsets.UTF_8);
		return responseEnvelope;
	}
	
	private boolean exists(String path) {
		File f = new File(path);
		if (f.exists())
			return true;
		
		return false;
	}
	
	private String readFile(String path,
			Charset encoding)
			throws IOException {
		byte[] encoded = Files.readAllBytes(Paths.get(path));
		return new String(encoded, encoding);
	}
	
}
