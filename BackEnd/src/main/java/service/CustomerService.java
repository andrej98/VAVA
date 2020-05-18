package service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import model.Customer;
import model.Hotel;
import model.HotelManager;
import repository.CustomerRepository;

@Service
public class CustomerService {
	
	@Autowired
	private CustomerRepository customerR;
	
	public Customer login(String email, String password) {
		Customer c = customerR.findByEmailAndPassword(email, password);
		System.out.println(c.getEmail().toString());
		return c;
	}

	public void saveCustomer(Customer customer) {
		customerR.save(customer);
	}

	public Customer getById(int customer_id) {
		Optional<Customer> optionalM = customerR.findById(customer_id);
		if(optionalM.isPresent()) {
			return optionalM.get();
		}
		return null;
	}
}
