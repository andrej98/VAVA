package controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import model.Payment;
import model.Reservation;
import service.PaymentService;

@RestController
public class PaymentController {

	@Autowired
	private PaymentService paymentService;
	
	//POST 
	@RequestMapping(value="payment/save", method = RequestMethod.POST)
	public void savePayment(@RequestBody Payment payment) {
		paymentService.savePayment(payment);
	}
}
