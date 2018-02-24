package com.capgemini.ntc.example.soap.tempconvert;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

import javax.xml.parsers.FactoryConfigurationError;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPMessage;
import javax.xml.transform.TransformerException;

import org.xml.sax.SAXException;

import com.capgemini.ntc.test.core.logger.BFLogger;
import com.capgemini.ntc.webapi.core.BasePage;
import com.capgemini.ntc.webapi.soap.SOAPClient;
import com.jamesmurty.utils.XMLBuilder;

public class FarenheitToCelsiusMethod extends BasePage {
	
	private double fahrenheitToCelsiusResult;
	
	/**
	 * <FahrenheitToCelsiusResult>100</FahrenheitToCelsiusResult>
	 * 
	 * @param value
	 * @return
	 */
	public FarenheitToCelsiusMethod setFahrenheitToCelsiusResult(double value) {
		
		this.fahrenheitToCelsiusResult = value;
		return this;
	}
	
	private String getFahrenheitToCelsiusResult() {
		return String.valueOf(this.fahrenheitToCelsiusResult);
	}
	
	// https://github.com/buildscientist/wiremock-demo/
	
	/**
	 * SOAP endpoint for a SOAP web service that matches a response body with the following SOAP envelope:
	 * <soap:Envelope xmlns:soap="http://schemas.xmlsoap.org/soap/envelope/"
	 * xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns:xsd="http://www.w3.org/2001/XMLSchema">
	 * <soap:Body>
	 * <FahrenheitToCelsiusResponse>
	 * <FahrenheitToCelsiusResult>100</FahrenheitToCelsiusResult> </FahrenheitToCelsiusResponse>
	 * </soap:Body>
	 * </soap:Envelope>
	 * 
	 * @throws IOException
	 **/
	public String fromFile_request() throws IOException {
		return getStringOutOfFile("/src/test/resources/soapInput/farenheittocelsius/samplerequest.xml");
	}
	
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
	 * @throws IOException
	 **/
	public String fromFile_response() throws IOException {
		return getStringOutOfFile("/src/test/resources/soapInput/farenheittocelsius/sampleresponse.xml");
	}
	
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
	public String fromCode_response() throws ParserConfigurationException, FactoryConfigurationError, TransformerException, SOAPException, SAXException, IOException {
		
		//@formatter:off
		XMLBuilder xmlBody = XMLBuilder.create("FahrenheitToCelsiusResponse")
				.element("FahrenheitToCelsiusResult").text(getFahrenheitToCelsiusResult());
		//@formatter:on
		
		SOAPMessage soapMessage = SOAPClient.createSOAPmessage(xmlBody.asString());
		
		return SOAPClient.printSoapMessage(soapMessage);
		
	}
	
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
