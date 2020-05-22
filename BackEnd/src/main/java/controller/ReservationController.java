package controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import model.Hotel;
import service.ReservationService;

@RestController
public class ReservationController {

	@Autowired
	private ReservationService reservationService;
	
//	//GET
//		@RequestMapping(value = "/reservations/{customer}", method = RequestMethod.GET)
//		public List<Hotel> getAll(){
//			return reservationService.getAll();
//		}
		
}
