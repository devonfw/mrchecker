package com.capgemini.mrchecker.common.mts.data;

public class Booking {
	public static Booking defaultValidBooking() {
		Booking booking = new Booking();
		booking.setBooking(BookingData.defaultBookingData());
		return booking;
	}
	
	private BookingData booking;
	
	public BookingData getBooking() {
		return booking;
	}
	
	public void setBooking(BookingData booking) {
		this.booking = booking;
	}
}
