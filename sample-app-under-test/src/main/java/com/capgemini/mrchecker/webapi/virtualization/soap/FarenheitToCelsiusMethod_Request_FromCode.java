package com.capgemini.mrchecker.webapi.virtualization.soap;

import java.io.IOException;

import javax.xml.parsers.FactoryConfigurationError;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPMessage;
import javax.xml.transform.TransformerException;

import org.xml.sax.SAXException;

import com.capgemini.mrchecker.webapi.core.BasePageWebAPI;
import com.capgemini.mrchecker.webapi.soap.SoapMessageGenerator;
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
	 * SOAP response built from Java code
	 */
	
	public FarenheitToCelsiusMethod_Request_FromCode() {
		setRoot("FahrenheitToCelsius");
	}
	
	/**
	 * @return Generate SOAP request in String format
	 */
	public String getMessage() {
		String message = "";
		try {
			SOAPMessage soapMessage = SoapMessageGenerator.createSOAPmessage(this.getRoot()
					.asString());
			message = SoapMessageGenerator.printSoapMessage(soapMessage);
		} catch (SOAPException | SAXException | IOException | ParserConfigurationException | TransformerException e) {
			new Exception(e);
		}
		return message;
	}
	
	/**
	 * @return Root XML structure
	 */
	public XMLBuilder getRoot() {
		return xmlBody;
	}
	
	// Set any type of "nodes" under xml Root
	/**
	 * Set "Fahrenheit" node under xml Root
	 *
	 * @param fahrenheit
	 * @return <FahrenheitToCelsius>
	 *         <Fahrenheit>100</Fahrenheit>
	 *         </FahrenheitToCelsius>
	 */
	public FarenheitToCelsiusMethod_Request_FromCode setFahrenheit(int fahrenheit) {
		getRoot().element("Fahrenheit")
				.text(Integer.toString(fahrenheit));
		return this;
	}
	
	// Set any nodes under xml Root
	/**
	 * Set "Smth" node under xml Root
	 *
	 * @param fahrenheit
	 * @return <FahrenheitToCelsius>
	 *         <Smth>Hello</Smth>
	 *         </FahrenheitToCelsius>
	 */
	public FarenheitToCelsiusMethod_Request_FromCode setSmth(String Smth) {
		getRoot().element("Smth")
				.text(Smth);
		return this;
	}
	
	/*
	 * ----------------------------------
	 * Any handy actions after this point
	 * ----------------------------------
	 */
	private XMLBuilder xmlBody;
	
	private void setRoot(String nodeName) {
		try {
			this.xmlBody = XMLBuilder.create(nodeName);
		} catch (ParserConfigurationException | FactoryConfigurationError e) {
			new Exception(e);
		}
	}
	
	public String getEndpoint() {
		// Useful only for REST Tests
		return null;
	}
}
