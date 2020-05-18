package controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import service.PaymentService;

@RestController
public class PaymentController {

	@Autowired
	private PaymentService paymentService;
}
