package model;

import java.sql.Date;

public class Reservation {
	private int reservation_id;
	private Date checkin_date;
	private Date checkout_date;
	
	private Customer customer;
	
	private Payment payment;
	
	private Room room;
	
	
	public String getHotel_name() {
		return this.getRoom().getHotel().getHotel_name();
	}

	public String getAddress() {
		return this.getRoom().getHotel().getAddress();
	}

	public String getCity() {
		return this.getRoom().getHotel().getCity();
	}

	
	public String getCountry() {
		return this.getRoom().getHotel().getCountry();
	}

	public int getStars() {
		return this.getRoom().getHotel().getStars();
	}

	public int getBeds() {
		return this.getRoom().getBeds();
	}

	//public int getPrice() {
//		java.util.Date in = new java.util.Date(this.checkin_date.getTime());
//		java.util.Date out = new java.util.Date(this.checkin_date.getTime());
//
//		return ChronoUnit.DAYS.between(in,out)*this.getRoom().getPrice();
//	}

//	public int getPaid() {
//		return paid;
//	}
	
	public Reservation() {
		
	}
	
	public Reservation(Date checkin_date, Date checkout_date, Room room, Customer customer, int reservation_id) {
		super();
		this.checkin_date = checkin_date;
		this.checkout_date = checkout_date;
		this.room = room;
		this.customer = customer;
		this.reservation_id = reservation_id;
	}

	public int getReservation_id() {
		return reservation_id;
	}

	public void setReservation_id(int reservation_id) {
		this.reservation_id = reservation_id;
	}

	public Date getCheckin_date() {
		return checkin_date;
	}

	public void setCheckin_date(Date checkin_date) {
		this.checkin_date = checkin_date;
	}

	public Date getCheckout_date() {
		return checkout_date;
	}

	public void setCheckout_date(Date checkout_date) {
		this.checkout_date = checkout_date;
	}

	public Room getRoom() {
		return room;
	}

	public void setRoom(Room room) {
		this.room = room;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public Payment getPayment() {
		return payment;
	}

	public void setPayment(Payment payment) {
		this.payment = payment;
	}
	
	
}
