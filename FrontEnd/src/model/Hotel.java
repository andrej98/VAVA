package model;

import java.util.List;


public class Hotel {


	private int hotel_id;
	private String hotel_name;
	private String address;
	private int stars;
	private String city;
	private String country;
	private HotelManager manager;
	private List<Room> rooms;
	private int rooms_count;
	public Hotel() {
	}
	
	
	
	public Hotel(int hotel_id,String hotel_name, String address, int stars, String city, String country, HotelManager manager,int rooms_count) {
		super();
		this.hotel_name = hotel_name;
		this.address = address;
		this.stars = stars;
		this.city = city;
		this.country = country;
		this.manager = manager;
		this.rooms_count = rooms_count;
		this.hotel_id = hotel_id;
	}

	public Hotel(String hotel_name, String address, int stars, String city, String country) {
		this.hotel_name = hotel_name;
		this.address = address;
		this.stars = stars;
		this.city = city;
		this.country = country;
	}
	

	public Hotel(String hotel_name, String address, int stars, String city, String country, HotelManager manager) {
		super();
		this.hotel_name = hotel_name;
		this.address = address;
		this.stars = stars;
		this.city = city;
		this.country = country;
		this.manager = manager;
	}

	public int getHotel_id() {
		return hotel_id;
	}
	public void setHotel_id(int hotel_id) {
		this.hotel_id = hotel_id;
	}
	public String getHotel_name() {
		return hotel_name;
	}
	public void setHotel_name(String hotel_name) {
		this.hotel_name = hotel_name;
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
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public List<Room> getRooms() {
		return rooms;
	}
	public void setRooms(List<Room> rooms) {
		this.rooms = rooms;
	}
	public void setManager(HotelManager manager) {
		this.manager = manager;
	}
	public HotelManager getManager() {
		return manager;
	}



	public int getRooms_count() {
		return rooms_count;
	}



	public void setRooms_count(int rooms_count) {
		this.rooms_count = rooms_count;
	}
	
}
