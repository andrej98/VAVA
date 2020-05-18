package model;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;



public class HotelManager {
	private int manager_id;
	private String first_name;
	private String last_name;
	private String email;
	private String password;
	private Date birth_date;
	private char gender;
	private String address;
	private List<Hotel> hotels = new ArrayList<>();
	
	public HotelManager(){
	}
	
	public HotelManager(String first_name, String last_name, String email, String password, Date birth_date,
			char gender, String address) {
		super();
		this.first_name = first_name;
		this.last_name = last_name;
		this.email = email;
		this.password = password;
		this.birth_date = birth_date;
		this.gender = gender;
		this.address = address;
	}
	public int getManager_id() {
		return manager_id;
	}
	public void setManager_id(int manager_id) {
		this.manager_id = manager_id;
	}
	public String getFirst_name() {
		return first_name;
	}
	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}
	public String getLast_name() {
		return last_name;
	}
	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Date getBirth_date() {
		return birth_date;
	}
	public void setBirth_date(Date birth_date) {
		this.birth_date = birth_date;
	}
	public char getGender() {
		return gender;
	}
	public void setGender(char gender) {
		this.gender = gender;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}

	public List<Hotel> getHotels() {
		return hotels;
	}

	public void setHotels(List<Hotel> hotels) {
		this.hotels = hotels;
	}
	
	public String getName() {
		return this.first_name + " " + this.last_name;
	}
}
