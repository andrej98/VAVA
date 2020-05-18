package model;

import java.util.List;



public class Room {
	private int beds;
	private int price;
	private List<Reservation> reservation;
	
	private Hotel hotel;
	
	private int room_id;
	private boolean bathroom;
	private boolean heating;
	
	public Room() {
		
	}
	
	public Room(int beds, int price, Hotel hotel, boolean bathroom, boolean heating) {
		super();
		this.beds = beds;
		this.price = price;
		this.hotel = hotel;
		this.bathroom = bathroom;
		this.heating = heating;
	}
	public int getBeds() {
		return beds;
	}
	public void setBeds(int beds) {
		this.beds = beds;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public Hotel getHotel() {
		return hotel;
	}
	public void setHotel_id(Hotel hotel) {
		this.hotel = hotel;
	}
	public int getRoom_id() {
		return room_id;
	}
	public void setRoom_id(int room_id) {
		this.room_id = room_id;
	}
	public boolean isBathroom() {
		return bathroom;
	}
	public void setBathroom(boolean bathroom) {
		this.bathroom = bathroom;
	}
	public boolean isHeating() {
		return heating;
	}
	public void setHeating(boolean heating) {
		this.heating = heating;
	}
	
}
