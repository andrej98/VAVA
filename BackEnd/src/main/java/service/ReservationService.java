package service;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import model.Hotel;
import model.Reservation;
import model.ReservationDTO;
import repository.ReservationRepository;

@Service
public class ReservationService {

	@Autowired
	private ReservationRepository reservationR;

	public List<ReservationDTO> getAll(int customer_id) {
		return reservationR.getAll(customer_id);
	}
}
