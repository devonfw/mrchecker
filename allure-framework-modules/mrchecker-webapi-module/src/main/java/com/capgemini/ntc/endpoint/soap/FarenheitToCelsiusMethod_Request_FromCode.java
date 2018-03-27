package com.capgemini.ntc.endpoint.soap;

import java.io.IOException;

import javax.xml.parsers.FactoryConfigurationError;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPMessage;
import javax.xml.transform.TransformerException;

import org.xml.sax.SAXException;

import com.capgemini.ntc.webapi.core.BasePageWebAPI;
import com.capgemini.ntc.webapi.soap.SoapMessageGenerator;
import com.jamesmurty.utils.XMLBuilder;

/**
 * SOAP endpoint for a SOAP web service that matches a request body with the following SOAP envelope:
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
public class FarenheitToCelsiusMethod_Request_FromCode extends BasePageWebAPI {
	
	/*
	 * Build SOAP response builded from Java code
	 */
	
	// Builder Class
	public static class Builder {
		// optional parameters
		private int fahrenheit;
		
		/**
		 * @param fahrenheit
		 * @return Structure
		 */
		public Builder setFahrenheit(int fahrenheit) {
			this.fahrenheit = fahrenheit;
			return this;
		}
		
		/**
		 * @return Generate SOAP response as String
		 */
		public String build() {
			return new FarenheitToCelsiusMethod_Request_FromCode(this).build();
			
		}
	}
	
	private XMLBuilder xmlBody;
	
	private FarenheitToCelsiusMethod_Request_FromCode(Builder builder) {
		
		setRoot();
		// set any other nodes in file
		setFahrenheit(builder.fahrenheit);
		
	}
	
	private void setRoot() {
		try {
			this.xmlBody = XMLBuilder.create("FahrenheitToCelsius");
		} catch (ParserConfigurationException | FactoryConfigurationError e) {
			new Exception(e);
		}
	}
	
	private XMLBuilder getRoot() {
		return xmlBody;
	}
	
	// Set any nodes under xml Root
	private void setFahrenheit(int fahrenheit) {
		getRoot().element("Fahrenheit")
				.text(Integer.toString(fahrenheit));
	}
	
	// Generate SOAP response
	private String build() {
		String messageInString = "";
		try {
			SOAPMessage soapMessage = SoapMessageGenerator.createSOAPmessage(this.getRoot()
					.asString());
			messageInString = SoapMessageGenerator.printSoapMessage(soapMessage);
		} catch (SOAPException | SAXException | IOException | ParserConfigurationException | TransformerException e) {
			new Exception(e);
		}
		return messageInString;
	}
}
