package service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import model.Hotel;
import repository.HotelRepository;

/**
 * Service pre triedu Hotel
 * @author Andrej
 *
 */
@Service
public class HotelService {

	@Autowired
	private HotelRepository hotelR;
	
	/**
	 * Vrati zoznam vsetkych hotelov
	 * @return
	 */
	public List<Hotel> getAll(){
		List<Hotel> hotels = new ArrayList<Hotel>();
		hotelR.findAll().forEach(hotels::add);
		return hotels;
	}
	
	/**
	 * Vrati Hotel podla id 
	 * @param id
	 * @return
	 */
	public Hotel getHotel(int id) {
		Optional<Hotel> optionalHotel = hotelR.findById(id);
		if(optionalHotel.isPresent()) {
			return optionalHotel.get();
		}
		return null;
	}

	/**
	 * Ulozi hotel do databazy
	 * @param hotel
	 */
	public void saveHotel(Hotel hotel) {
		hotelR.save(hotel);		
	}

	/**
	 * Zmaze hotel z databazy
	 * @param hotel_id
	 */
	public void deleteHotel(int hotel_id) {
		hotelR.deleteById(hotel_id);
	}

	/**
	 * Aktualizuje udaje daneho hotela
	 * @param hotel
	 */
	public void updateHotel(Hotel hotel) {
		hotelR.save(hotel);
	}
	
	/**
	 * Sluzi na filtrovanie hotelov, obsahuje volitelne parametre
	 * @param hotel_name
	 * @param city
	 * @param country
	 * @param price
	 * @param beds
	 * @param stars
	 * @return Vrati hotely splnajuce dane kriteria
	 */
	public List<Hotel> filter(Optional<String> hotel_name, Optional<String> city, Optional<String> country,
	 Optional<Integer> price, Optional<Integer> beds, Optional<Integer> stars ) {
		return hotelR.filter(hotel_name.orElse("_"), city.orElse("_"),
				country.orElse("_") ,price.orElse(0),beds.orElse(0),stars.orElse(0) );
	}

	
}
