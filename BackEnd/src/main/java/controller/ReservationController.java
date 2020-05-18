package controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import service.ReservationService;

@RestController
public class ReservationController {

	@Autowired
	private ReservationService reservationService;
}
