package service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import model.Customer;
import repository.CustomerRepository;

/**
 * Service pre triedu Customer
 * @author Andrej
 *
 */
@Service
public class CustomerService {
	
	@Autowired
	private CustomerRepository customerR;
	
	/**
	 * Vola sa pri prihlasovani Customera
	 * @param email 
	 * @param password
	 * @return Vrati Customera s danym emailom a heslom
	 */
	public Customer login(String email, String password) {
		Customer c = customerR.findByEmailAndPassword(email, password);
		System.out.println(c.getEmail().toString());
		return c;
	}

	/**
	 * Ulozi Customera do databazy
	 * @param customer
	 */
	public void saveCustomer(Customer customer) {
		customerR.save(customer);
	}

	/**
	 * Vrati customera podla customer_id
	 * @param customer_id
	 * @return
	 */
	public Customer getById(int customer_id) {
		Optional<Customer> optionalM = customerR.findById(customer_id);
		if(optionalM.isPresent()) {
			return optionalM.get();
		}
		return null;
	}
}
