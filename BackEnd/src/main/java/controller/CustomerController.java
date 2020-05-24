package controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import model.Customer;
import service.CustomerService;

/**
 * Controller trieda ktora poskytuje REST sluzby pre Customer
 * @author Andrej
 *
 */
@RestController
public class CustomerController {

	@Autowired 
	private CustomerService customerService;
	
	/**
	 * Vola sa pri prihlasovani Customera
	 * @param email 
	 * @param password
	 * @return Vrati Customera s danym emailom a heslom
	 */
	@RequestMapping(path = "/login/customer/{email}/{password}", method = RequestMethod.GET)
	public Customer login(@PathVariable String email, @PathVariable String password) {
		return customerService.login(email, password);
	}
	
	/**
	 * 
	 * @param customer_id
	 * @return Vrati Customera podla jeho customer_id
	 */
	@RequestMapping(path = "/customer/{id}", method = RequestMethod.GET)
	public Customer getById(@PathVariable int customer_id) {
		return customerService.getById(customer_id);
	}
	
	/**
	 * Prida noveho Customera, pouziva sa pri registracii 
	 * @param customer
	 */
	@RequestMapping(value="/customer/save", method = RequestMethod.POST)
	public void saveCustomer(@RequestBody Customer customer) {
		customerService.saveCustomer(customer);
	}
}
