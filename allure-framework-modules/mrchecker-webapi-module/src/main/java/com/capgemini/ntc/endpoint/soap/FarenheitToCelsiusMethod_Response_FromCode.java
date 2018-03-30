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

public class FarenheitToCelsiusMethod_Response_FromCode extends BasePageWebAPI {
	
	/*
	 * Build SOAP response builded from Java code
	 */
	
	public FarenheitToCelsiusMethod_Response_FromCode() {
		setRoot("FahrenheitToCelsiusResponse");
	}
	
	/**
	 * @return Generate SOAP response in String format
	 */
	public String getMessage() {
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
	
	/**
	 * @return Root XML structure
	 */
	public XMLBuilder getRoot() {
		return xmlBody;
	}
	
	// Set any nodes under xml Root
	/**
	 * @param fahrenheit
	 * @return <FahrenheitToCelsius>
	 *         <Fahrenheit>100</Fahrenheit>
	 *         </FahrenheitToCelsius>
	 */
	public FarenheitToCelsiusMethod_Response_FromCode setFahrenheitToCelsiusResult(double value) {
		getRoot()
				.element("FahrenheitToCelsiusResult")
				.text(Double.toString(value));
		return this;
	}
	
	// Set any nodes under xml Root
	/**
	 * @param fahrenheit
	 * @return <FahrenheitToCelsiusResponse>
	 *         <FahrenheitToCelsiusResult>37.7777777777778</FahrenheitToCelsiusResult>
	 *         </FahrenheitToCelsiusResponse>
	 */
	public FarenheitToCelsiusMethod_Response_FromCode setSmth(String Smth) {
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
	
	/**
	 * Set value for node "FahrenheitToCelsiusResult" in response body
	 * <FahrenheitToCelsiusResult>100</FahrenheitToCelsiusResult>
	 * 
	 * @param value
	 * @return
	 */
	
	/*
	 * Build SOAP response builded from Java code
	 */
	
	/**
	 * SOAP endpoint for a SOAP web service that matches a request body with the following SOAP envelope:
	 * <?xml version="1.0" encoding="utf-8"?>
	 * <soap12:Envelope xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns:xsd=
	 * "http://www.w3.org/2001/XMLSchema" xmlns:soap12="http://www.w3.org/2003/05/soap-envelope">
	 * <soap12:Body>
	 * <FahrenheitToCelsiusResponse>
	 * <FahrenheitToCelsiusResult>37.7777777777778</FahrenheitToCelsiusResult>
	 * </FahrenheitToCelsiusResponse>
	 * </soap12:Body>
	 * </soap12:Envelope>
	 * 
	 * @throws FactoryConfigurationError
	 * @throws ParserConfigurationException
	 * @throws TransformerException
	 * @throws SAXException
	 * @throws SOAPException
	 * @throws IOException
	 **/
	
}
