package controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import model.Hotel;
import model.Reservation;
import model.ReservationDTO;
import service.ReservationService;

@RestController
public class ReservationController {

	@Autowired
	private ReservationService reservationService;
	
	//GET
	@RequestMapping(value = "/reservations/{customer_id}", method = RequestMethod.GET)
	public List<ReservationDTO> getAll(@PathVariable int customer_id){
		List<ReservationDTO> list = reservationService.getAll(customer_id);
		return list;
	}
		
}
