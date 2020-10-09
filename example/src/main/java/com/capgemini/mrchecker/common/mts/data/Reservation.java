package com.capgemini.mrchecker.common.mts.data;

public class Reservation {
	
	private String	date;
	private String	name;
	private String	email;
	private String	id;
	
	private int guestsNumber;
	
	public Reservation(String date, String email, String id) {
		this(date, null, email, 0);
		this.id = id;
	}
	
	public Reservation(String date, String email, int guests) {
		this(date, null, email, guests);
	}
	
	public Reservation(String date, String name, String email, int guests) {
		this.date = date;
		this.name = name;
		this.email = email;
		this.guestsNumber = guests;
	}
	
	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	public int getGuestsNumber() {
		return guestsNumber;
	}
	
	public void setGuestsNumber(int guestsNumber) {
		this.guestsNumber = guestsNumber;
	}
	
	public String getDate() {
		return date;
	}
	
	public void setDate(String date) {
		this.date = date;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getReservationId() {
		return id;
	}
	
	public void setReservationId(String id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	@Override
	public String toString() {
		return "{date: " + date + ", email:" + email + ", ids: " + id + "}\n";
	}
	
}
