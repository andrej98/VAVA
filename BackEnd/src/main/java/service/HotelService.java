package service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import model.Hotel;
import repository.HotelRepository;

@Service
public class HotelService {

	@Autowired
	private HotelRepository hotelR;
	
	public List<Hotel> getAll(){
		List<Hotel> hotels = new ArrayList<Hotel>();
		hotelR.findAll().forEach(hotels::add);
		return hotels;
	}
	
	public Hotel getHotel(int id) {
		Optional<Hotel> optionalHotel = hotelR.findById(id);
		if(optionalHotel.isPresent()) {
			return optionalHotel.get();
		}
		return null;
	}

	public void saveHotel(Hotel hotel) {
		hotelR.save(hotel);		
	}
}
