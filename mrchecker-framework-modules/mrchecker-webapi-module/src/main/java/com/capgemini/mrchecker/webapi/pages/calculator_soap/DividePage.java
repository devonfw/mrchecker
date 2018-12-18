package com.capgemini.mrchecker.webapi.pages.calculator_soap;

import com.capgemini.mrchecker.webapi.core.BasePageWebAPI;
import com.capgemini.mrchecker.webapi.core.base.driver.DriverManager;

import io.restassured.response.Response;

public class DividePage extends BasePageWebAPI {
	
	private final static String	HOSTNAME	= "http://www.dneonline.com";
	private final static String	PATH		= "/calculator.asmx?op=Divide";
	private final static String	ENDPOINT	= HOSTNAME + PATH;
	
	private final static String ROOT = "Divide";
	
	private SOAPTemplate soapTemplate = new SOAPTemplate(ROOT);
	
	{
		soapTemplate.addAttributeToRoot("xmlns", "http://tempuri.org/");
	}
	
	public Response sendPOST() {
		return DriverManager.getDriverWebAPI()
				.with()
				.contentType("text/xml; charset=utf-8")
				.header("SOAPAction", "http://tempuri.org/Divide")
				.body(soapTemplate.getMessage())
				.log()
				.all()
				.when()
				.post(ENDPOINT)
				.thenReturn();
	}
	
	public void setIntA(int numberA) {
		soapTemplate.getRoot()
				.element("intA")
				.text(String.valueOf(numberA));
	}
	
	public void setIntB(int numberB) {
		soapTemplate.getRoot()
				.element("intB")
				.text(String.valueOf(numberB));
	}
	
	@Override
	public String getEndpoint() {
		return ENDPOINT;
	}
	
	/*
	 * SAMPLE REQUEST:
	 * <soap12:Envelope xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	 * xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns:soap12="http://www.w3.org/2003/05/soap-envelope">
	 * <soap12:Body>
	 * <Divide xmlns="http://tempuri.org/">
	 * <intA>int</intA>
	 * <intB>int</intB>
	 * </Divide>
	 * </soap12:Body>
	 * </soap12:Envelope>
	 */
	
	/*
	 * SAMPLE RESPONSE:
	 * <soap12:Envelope xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	 * xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns:soap12="http://www.w3.org/2003/05/soap-envelope">
	 * <soap12:Body>
	 * <DivideResponse xmlns="http://tempuri.org/">
	 * <DivideResult>int</DivideResult>
	 * </DivideResponse>
	 * </soap12:Body>
	 * </soap12:Envelope>
	 */
}
