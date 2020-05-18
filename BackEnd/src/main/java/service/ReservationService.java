package service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import repository.ReservationRepository;

@Service
public class ReservationService {

	@Autowired
	private ReservationRepository reservationR;
}
