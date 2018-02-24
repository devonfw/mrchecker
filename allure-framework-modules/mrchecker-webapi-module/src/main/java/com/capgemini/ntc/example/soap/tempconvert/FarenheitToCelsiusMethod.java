package com.capgemini.ntc.example.soap.tempconvert;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.FactoryConfigurationError;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.soap.MessageFactory;
import javax.xml.soap.MimeHeaders;
import javax.xml.soap.SOAPBody;
import javax.xml.soap.SOAPConnection;
import javax.xml.soap.SOAPElement;
import javax.xml.soap.SOAPEnvelope;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPHeader;
import javax.xml.soap.SOAPMessage;
import javax.xml.soap.SOAPPart;
import javax.xml.transform.TransformerException;
import javax.xml.xpath.XPathConstants;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.capgemini.ntc.test.core.logger.BFLogger;
import com.capgemini.ntc.webapi.core.BasePage;
import com.jamesmurty.utils.XMLBuilder;
import com.jamesmurty.utils.XMLBuilder2;

public class FarenheitToCelsiusMethod extends BasePage {
	
	private int fahrenheitToCelsiusResult;
	
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
	 * <FahrenheitToCelsiusResult>100</FahrenheitToCelsiusResult>
	 * 
	 * @param value
	 * @return
	 */
	public FarenheitToCelsiusMethod setFahrenheitToCelsiusResult(int value) {
		
		this.fahrenheitToCelsiusResult = value;
		return this;
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
	public String buildXml() throws ParserConfigurationException, FactoryConfigurationError {
		
		XMLBuilder builder = XMLBuilder.create("Projects")
				.e("java-xmlbuilder")
				.a("language", "Java")
				.a("scm", "SVN")
				.e("Location")
				.a("type", "URL")
				.t("http://code.google.com/p/java-xmlbuilder/")
				.up()
				.up()
				.e("JetS3t")
				.a("language", "Java")
				.a("scm", "CVS")
				.e("Location")
				.a("type", "URL")
				.t("http://jets3t.s3.amazonaws.com/index.html");
		
		return "";
		
	}
	
	public void buildSOAP() throws SOAPException, ParserConfigurationException, Error, TransformerException {
		
		// @formatter:off
		XMLBuilder2 builder = XMLBuilder2.create("Projects")
					.e("java-xmlbuilder").a("language", "Java").a("scm", "SVN")
						.e("Location").a("type", "URL").t("http://code.google.com/p/java-xmlbuilder/")
						.up()
					.up()
					.e("JetS3t").a("language", "Java").a("scm", "CVS")
						.e("Location").a("type", "URL").t("http://jets3t.s3.amazonaws.com/index.html");
		// @formatter:on
		
		BFLogger.logDebug("!!!! Document: " + builder.asString()
				.replace("<Projects>", "")
				.replace("</Projects>", ""));
		
		// Find all nodes under Projects
		NodeList nodes = (NodeList) builder.xpathQuery(
				"/Projects/*", XPathConstants.NODESET);
		BFLogger.logDebug("!!!! GetDocument: " + nodes.item(0)
				.toString());
		
		String document = builder.root()
				.getDocument()
				.toString();
		
		BFLogger.logDebug("!!!! GetDocument: " + document);
	}
	
	/**
	 * Create a SOAP request
	 * 
	 * @param body
	 *            the body of the SOAP message
	 * @param operation
	 *            the operation from the SOAP server invoked
	 * @return the SOAP message request completed
	 * @throws SOAPException
	 */
	private SOAPMessage createSOAPRequest(SOAPElement body,
			String operation)
			throws SOAPException {
		
		// The SOAP server URI
		String uriSOAPServer = null;
		// The SOAP connection
		SOAPConnection soapConnection = null;
		
		// If you want to add namespace to the header, follow this constant
		final String PREFIX_NAMESPACE = "ns";
		final String NAMESPACE = "http://other.namespace.to.add.to.header";
		
		final MessageFactory messageFactory = MessageFactory.newInstance();
		final SOAPMessage soapMessage = messageFactory.createMessage();
		final SOAPPart soapPart = soapMessage.getSOAPPart();
		
		// SOAP Envelope
		final SOAPEnvelope envelope = soapPart.getEnvelope();
		envelope.addNamespaceDeclaration(PREFIX_NAMESPACE, NAMESPACE);
		
		// SOAP Body
		final SOAPBody soapBody = envelope.getBody();
		soapBody.addChildElement(body);
		
		// Mime Headers
		final MimeHeaders headers = soapMessage.getMimeHeaders();
		BFLogger.logDebug("SOAPAction : " + uriSOAPServer + operation);
		headers.addHeader("SOAPAction", uriSOAPServer + operation);
		
		soapMessage.saveChanges();
		
		/* Print the request message */
		BFLogger.logDebug("Request SOAP Message :" + soapMessage.toString());
		return soapMessage;
	}
	
	/**
	 * Transform a String to a SOAP element
	 * 
	 * @param xmlRequestBody
	 *            the string body representation
	 * @return a SOAP element
	 * @throws SOAPException
	 * @throws SAXException
	 * @throws IOException
	 * @throws ParserConfigurationException
	 */
	private SOAPElement stringToSOAPElement(String xmlRequestBody)
			throws SOAPException, SAXException, IOException,
			ParserConfigurationException {
		
		// Load the XML text into a DOM Document
		final DocumentBuilderFactory builderFactory = DocumentBuilderFactory
				.newInstance();
		builderFactory.setNamespaceAware(true);
		final InputStream stream = new ByteArrayInputStream(
				xmlRequestBody.getBytes());
		final Document doc = builderFactory.newDocumentBuilder()
				.parse(stream);
		
		// Use SAAJ to convert Document to SOAPElement
		// Create SoapMessage
		final MessageFactory msgFactory = MessageFactory.newInstance();
		final SOAPMessage message = msgFactory.createMessage();
		final SOAPBody soapBody = message.getSOAPBody();
		
		// This returns the SOAPBodyElement that contains ONLY the Payload
		return soapBody.addDocument(doc);
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
	
	public String buildSOAP2() throws SOAPException, SAXException, IOException, ParserConfigurationException {
		
		// @formatter:off
		XMLBuilder2 builder = XMLBuilder2.create("Projects")
					.e("java-xmlbuilder").a("language", "Java").a("scm", "SVN")
						.e("Location").a("type", "URL").t("http://code.google.com/p/java-xmlbuilder/")
						.up()
					.up()
					.e("JetS3t").a("language", "Java").a("scm", "CVS")
						.e("Location").a("type", "URL").t("http://jets3t.s3.amazonaws.com/index.html");
		// @formatter:on
		
		String xmlRequestBody = builder.asString();
		
		// Send SOAP Message to SOAP Server
		final SOAPElement stringToSOAPElement = stringToSOAPElement(xmlRequestBody);
		final SOAPMessage soapMsg = createSOAPRequest(stringToSOAPElement, "");
		
		BFLogger.logDebug("TEST soapResponse: ");
		soapMsg.writeTo(System.out);
		return soapMsg.toString();
		
	}
	
	public void buildSoapBody() throws SOAPException, IOException {
		
		MessageFactory factory = MessageFactory.newInstance();
		SOAPMessage soapMsg = factory.createMessage();
		SOAPPart part = soapMsg.getSOAPPart();
		
		SOAPEnvelope envelope = part.getEnvelope();
		SOAPHeader header = envelope.getHeader();
		SOAPBody body = envelope.getBody();
		
		header.addTextNode("Training Details");
		
		// @formatter:off
		body.addBodyElement(envelope.createName("JAVA")).addAttribute(envelope.createName("attr1"), "testValue")
				.addChildElement("WS").addTextNode("Training on Web service");
		
		body.addBodyElement(envelope.createName("JAVA"))
			.addChildElement("Spring").addTextNode("Training on Spring 3.0");
		// @formatter:on
		
		BFLogger.logDebug("!!!! SOAPmsg: " + soapMsg.toString());
		soapMsg.writeTo(System.out);
		
	}
	
	public static void main(String[] args) throws SAXException, IOException, SOAPException, ParserConfigurationException, TransformerException, Error {
		new FarenheitToCelsiusMethod().buildSOAP();
		new FarenheitToCelsiusMethod().buildSoapBody();
		new FarenheitToCelsiusMethod().buildSOAP();
		new FarenheitToCelsiusMethod().buildSOAP2();
	}
}
