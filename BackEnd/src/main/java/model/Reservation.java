package model;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name="reservation")
public class Reservation {
	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
	private int reservation_id;
	private Date checkin_date;
	private Date checkout_date;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="customer_id")
	private Customer customer;
	@OneToOne(mappedBy="reservation_id")
	private Payment payment;
	
	@ManyToOne
	@JoinColumn(name="room_id")
	private Room room_id;
	
		
	@JsonIgnore
	public Payment getPayment() {
		return payment;
	}
	//@JsonIgnore
	public void setPayment(Payment payment) {
		this.payment = payment;
	}

	
	public void setRoom(Room room) {
		this.room_id = room;
	}

	public Reservation() {
		
	}
	public Reservation(int id) {
		this.reservation_id=id;
	}
	
	
	public Reservation(Date checkin_date, Date checkout_date, Room room, Customer customer) {
		super();
		this.checkin_date = checkin_date;
		this.checkout_date = checkout_date;
		this.room_id = room;
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
	@JsonIgnore
	public Room getRoom_id() {
		return room_id;
	}
	@JsonProperty
	public void setRoom_id(Room room) {
		this.room_id = room;
	}
	@JsonIgnore
	public Customer getCustomer() {
		return customer;
	}
	@JsonProperty
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	
	
}
