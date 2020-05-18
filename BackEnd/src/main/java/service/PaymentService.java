package service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import repository.PaymentRepository;

@Service
public class PaymentService {

	@Autowired
	private PaymentRepository paymentR;
}
