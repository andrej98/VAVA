package model;

import java.sql.Date;



public class Reservation {
	private int reservation_id;
	private Date checkin_date;
	private Date checkout_date;
	
	private Customer customer;
	
	private Payment payment;
	
	private Room room;
	
	public Reservation() {
		
	}
	
	public Reservation(Date checkin_date, Date checkout_date, Room room, Customer customer) {
		super();
		this.checkin_date = checkin_date;
		this.checkout_date = checkout_date;
		this.room = room;
		this.customer = customer;
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

	public void setRoom_id(Room room) {
		this.room = room;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	
	
}
