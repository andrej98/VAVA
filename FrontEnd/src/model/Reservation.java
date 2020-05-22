package model;

import java.sql.Date;

public class Reservation {
	private int reservation_id;
	private Date checkin_date;
	private Date checkout_date;
	
	private String hotel_name;
	private String address;
	private int stars;
	private String city;
	private String country;
	
	private int price;
	private String paid;
	private int beds;
	private int room_id;
	
	public int getBeds() {
		return beds;
	}


	public void setBeds(int beds) {
		this.beds = beds;
	}


	public Reservation() {
		
	}


	public Reservation(int reservation_id, Date checkin_date, Date checkout_date, String hotel_name, String address,
			int stars, String city, String country, int price, String paid,int room_id) {
		super();
		this.reservation_id = reservation_id;
		this.checkin_date = checkin_date;
		this.checkout_date = checkout_date;
		this.hotel_name = hotel_name;
		this.address = address;
		this.stars = stars;
		this.city = city;
		this.country = country;
		this.price = price;
		this.paid = paid;
		this.room_id=room_id;

	}


	public int getRoom_id() {
		return room_id;
	}


	public void setRoom_id(int room_id) {
		this.room_id = room_id;
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


	public String getHotel_name() {
		return hotel_name;
	}


	public void setHotel_name(String hotel_name) {
		this.hotel_name = hotel_name;
	}


	public String getAddress() {
		return address;
	}


	public void setAddress(String address) {
		this.address = address;
	}


	public int getStars() {
		return stars;
	}


	public void setStars(int stars) {
		this.stars = stars;
	}


	public String getCity() {
		return city;
	}


	public void setCity(String city) {
		this.city = city;
	}


	public String getCountry() {
		return country;
	}


	public void setCountry(String country) {
		this.country = country;
	}


	public int getPrice() {
		return price;
	}


	public void setPrice(int price) {
		this.price = price;
	}


	public String getPaid() {
		return paid;
	}


	public void setPaid(String paid) {
		this.paid = paid;
	}
	
	
}
