package service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import model.Payment;
import repository.PaymentRepository;

@Service
public class PaymentService {

	@Autowired
	private PaymentRepository paymentR;

	public void savePayment(Payment payment) {
		paymentR.save(payment);		
	}
}
