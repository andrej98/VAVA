package controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import model.Customer;
import model.Hotel;
import service.HotelService;

@RestController
public class HotelController {

	@Autowired
	private HotelService hotelService;
	
	//GET
	@RequestMapping(value = "/allHotels", method = RequestMethod.GET)
	public List<Hotel> getAll(){
		return hotelService.getAll();
	}
	
	//GET
	@RequestMapping(value = "/hotel/{hotel_id}", method = RequestMethod.GET)
	public Hotel getHotel(@PathVariable int hotel_id){
		return hotelService.getHotel(hotel_id);
	}
	
	//DELETE
	@RequestMapping(value = "hotel/delete/{hotel_id}", method = RequestMethod.DELETE)
	public void deleteHotel(@PathVariable int hotel_id) {
		hotelService.deleteHotel(hotel_id);
	}

	
	//POST 
	@RequestMapping(value="hotel/save", method = RequestMethod.POST)
	public void saveHotel(@RequestBody Hotel hotel) {
		hotelService.saveHotel(hotel);
	}

}
