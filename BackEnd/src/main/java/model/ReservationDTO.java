package model;

import java.util.Date;



public class ReservationDTO {
	
	private int reservation_id;
	private String hotel_name;
	private String address;
	private String city;
	private String country;
	private int stars;
	private int beds;
	private Date checkin_date;
	private Date checkout_date;
	private int price;
	private String paid;
	
	public ReservationDTO() {
		
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

	public int getBeds() {
		return beds;
	}

	public void setBeds(int beds) {
		this.beds = beds;
	}

	public ReservationDTO(int reservation_id, String hotel_name, String address,
			String city, String country, int stars, int beds, Date checkin_date, Date checkout_date, 
			 int price, String paid) {
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
		this.beds = beds;
	}
	
}
