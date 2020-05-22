package model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name="room")
public class Room {
	private int beds;
	private int price;
	@JsonIgnore
	@OneToMany(mappedBy="room_id")
	private List<Reservation> reservation;
	@ManyToOne
	@JoinColumn(name="hotel_id", nullable = false)
	private Hotel hotel_id;
	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
	private int room_id;
	private boolean bathroom;
	private boolean heating;
	
	public Room() {
		
	}
	public Room(int id) {
		this.room_id=id;
	}
	
	
	public Room(int beds, int price, Hotel hotel, boolean bathroom, boolean heating) {
		super();
		this.beds = beds;
		this.price = price;
		this.hotel_id = hotel;
		this.bathroom = bathroom;
		this.heating = heating;
	}
	
	
	public List<Reservation> getReservation() {
		return reservation;
	}

	public void setReservation(List<Reservation> reservation) {
		this.reservation = reservation;
	}
	@JsonIgnore
	public Hotel getHotel_id() {
		return hotel_id;
	}
	@JsonProperty
	public void setHotel_id(Hotel hotel_id) {
		this.hotel_id = hotel_id;
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
