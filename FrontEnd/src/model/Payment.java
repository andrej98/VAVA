package model;

import java.sql.Date;




public class Payment {
	
	private int payment_id;
	
	private Reservation reservation;
	private int amount;
	private Date date_of_payment;
	
	public Payment(Reservation reservation, int amount, Date date_of_payment) {
		super();
		this.reservation = reservation;
		this.amount = amount;
		this.date_of_payment = date_of_payment;
	}
	
	public Payment() {
	}

	public int getPayment_id() {
		return payment_id;
	}

	public void setPayment_id(int payment_id) {
		this.payment_id = payment_id;
	}

	public Reservation getReservation() {
		return reservation;
	}

	public void setReservation_id(Reservation reservation) {
		this.reservation = reservation;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public Date getDate_of_payment() {
		return date_of_payment;
	}

	public void setDate_of_payment(Date date_of_payment) {
		this.date_of_payment = date_of_payment;
	}
	
	
	
}
