package service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import model.Payment;
import repository.PaymentRepository;

/**
 * Service pre triedu Payment
 * @author Andrej
 *
 */
@Service
public class PaymentService {

	@Autowired
	private PaymentRepository paymentR;

	/**
	 * Vlozi novy payment do databazy
	 * @param payment
	 */
	public void savePayment(Payment payment) {
		paymentR.save(payment);		
	}
}
