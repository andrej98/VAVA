package model;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="payment")
public class Payment {
	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
	private int payment_id;
	@JsonIgnore
	@OneToOne
	@JoinColumn(name="reservation_id")
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
