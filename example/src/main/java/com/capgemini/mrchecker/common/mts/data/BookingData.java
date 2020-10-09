package com.capgemini.mrchecker.common.mts.data;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class BookingData {
	public static final String	DEFAULT_NAME			= "Someone";
	public static final String	DEFAULT_EMAIL			= "email@test.de";
	public static final String	DEFAULT_BOOKING_DATE	= LocalDate.now()
			.plusDays(7)
			.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")) + "T18:00:12.111Z";
	public static final int		DEFAULT_GUESTS			= 2;
	
	public static BookingData defaultBookingData() {
		BookingData bookingData = new BookingData();
		bookingData.setAssistants(DEFAULT_GUESTS);
		bookingData.setName(DEFAULT_NAME);
		bookingData.setBookingDate(DEFAULT_BOOKING_DATE);
		bookingData.setEmail(DEFAULT_EMAIL);
		return bookingData;
	}
	
	private Integer	id;
	private String	name;
	private String	bookingDate;
	private String	email;
	private Integer	assistants;
	
	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getBookingDate() {
		return bookingDate;
	}
	
	public void setBookingDate(String bookingDate) {
		this.bookingDate = bookingDate;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public Integer getAssistants() {
		return assistants;
	}
	
	public void setAssistants(Integer assistants) {
		this.assistants = assistants;
	}
}
