package controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import model.Hotel;

import service.HotelService;

/**
 * Controller trieda ktora poskytuje REST sluzby pre Hotel
 * @author Andrej
 *
 */
@RestController
public class HotelController {

	@Autowired
	private HotelService hotelService;
	
	/**
	 * Pouziva sa ked si zakaznik prezera vsetky hotely
	 * @return Vrati zoznam vsetkych hotelov
	 */
	@RequestMapping(value = "/allHotels", method = RequestMethod.GET)
	public List<Hotel> getAll(){
		return hotelService.getAll();
	}
	
	/**
	 * 
	 * @param hotel_id
	 * @return Vrati hotel podla hotel_id
	 */
	@RequestMapping(value = "/hotel/{hotel_id}", method = RequestMethod.GET)
	public Hotel getHotel(@PathVariable int hotel_id){
		return hotelService.getHotel(hotel_id);
	}
	
	/**
	 * Zmaze hotel podla hotel_id
	 * @param hotel_id
	 */
	@RequestMapping(value = "hotel/delete/{hotel_id}", method = RequestMethod.DELETE)
	public void deleteHotel(@PathVariable int hotel_id) {
		hotelService.deleteHotel(hotel_id);
	}

	
	/**
	 * Prida novy hotel 
	 * @param hotel
	 */
	@RequestMapping(value="hotel/save", method = RequestMethod.POST)
	public void saveHotel(@RequestBody Hotel hotel) {
		hotelService.saveHotel(hotel);
	}
	
	/**
	 * Updatne dany hotel
	 * @param hotel
	 */
	@RequestMapping(value="/hotel/update", method = RequestMethod.PUT)
	public void updateHotel(@RequestBody Hotel hotel) {
		hotelService.updateHotel(hotel);
	}
	
	/**
	 * Sluzi na filtrovanie podla zadanych parametrov, ak nie je nejaky parameter zadany, ta ho ignoruje
	 * @param hotel_name
	 * @param city
	 * @param country
	 * @param price
	 * @param beds
	 * @param stars
	 * @return Zoznam hotelov splnajucich dane kriteria
	 */
	@GetMapping("/hotels")
	public List<Hotel> filter(  @RequestParam Optional<String> hotel_name, 
							    @RequestParam Optional<String> city, 
								@RequestParam Optional<String> country,										  
								@RequestParam Optional<Integer> price,										  
								@RequestParam Optional<Integer> beds,
								@RequestParam Optional<Integer> stars){
		return hotelService.filter(hotel_name, city, country ,price,beds,stars);
	}
	
}
