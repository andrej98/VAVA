package controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import model.Reservation;
import model.ReservationDTO;
import service.ReservationService;

/**
 * Controller trieda ktora poskytuje REST sluzby pre Reservation
 * @author Andrej
 *
 */
@RestController
public class ReservationController {

	@Autowired
	private ReservationService reservationService;
	
	/**
	 * Vrati vsetky rezervacie daneho Customera 
	 * @param customer_id
	 * @return
	 */
	@RequestMapping(value = "/reservations/{customer_id}", method = RequestMethod.GET)
	public List<ReservationDTO> getAll(@PathVariable int customer_id){
		List<ReservationDTO> list = reservationService.getAll(customer_id);
		return list;
	}
	
	/**
	 * Zmaze danu rezervaciu
	 * @param reservation_id
	 */
	@RequestMapping(value = "reservation/delete/{reservation_id}", method = RequestMethod.DELETE)
	public void deleteReservation(@PathVariable int reservation_id) {
		reservationService.deleteReservation(reservation_id);
	}
	
	/**
	 * Prida novu rezervaciu 
	 * @param reservation
	 */
	@RequestMapping(value="reservation/save", method = RequestMethod.POST)
	public void saveReservation(@RequestBody Reservation reservation) {
		reservationService.saveReservation(reservation);
	}
		
}
