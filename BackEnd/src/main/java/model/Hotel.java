package model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Predstavuje tabulku hotel v databaze
 * @author Andrej
 *
 */
@Entity
@Table(name="hotel")
public class Hotel {

	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
	private int hotel_id;
	private String hotel_name;
	private String address;
	private int stars;
	private String city;
	private String country;
	@ManyToOne(fetch = FetchType.LAZY)	
	@JoinColumn(name="manager_id")
	private HotelManager manager_id;
	@OneToMany(mappedBy="hotel_id", fetch = FetchType.LAZY)
	private List<Room> rooms;
	
	public Hotel() {
	}
	
	public Hotel(int hotel_id) {
		this.hotel_id=hotel_id;
	}
		
	
	public Hotel(String hotel_name, String address, int stars, String city, String country, HotelManager manager) {
		super();
		this.hotel_name = hotel_name;
		this.address = address;
		this.stars = stars;
		this.city = city;
		this.country = country;
		this.manager_id = manager;
	}
	
	@JsonIgnore
	public HotelManager getManager_id() {
		return manager_id;
	}
	@JsonProperty
	public void setManager_id(HotelManager manager_id) {
		this.manager_id = manager_id;
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
}
