package controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import model.Payment;
import service.PaymentService;

/**
 * Controller trieda ktora poskytuje REST sluzby pre Payment
 * @author Andrej
 *
 */
@RestController
public class PaymentController {

	@Autowired
	private PaymentService paymentService;
	
	/**
	 * Pridanie noveho Paymentu, pouziva sa pri platbe za rezervaciu 
	 * @param payment
	 */
	@RequestMapping(value="payment/save", method = RequestMethod.POST)
	public void savePayment(@RequestBody Payment payment) {
		paymentService.savePayment(payment);
	}
}
