package controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import model.Customer;
import model.HotelManager;
import service.CustomerService;

@RestController
public class CustomerController {

	@Autowired 
	private CustomerService customerService;
	
	//GET
	@RequestMapping(path = "/login/customer/{email}/{password}", method = RequestMethod.GET)
	public Customer login(@PathVariable String email, @PathVariable String password) {
		return customerService.login(email, password);
	}
	
	//GET by id
	@RequestMapping(path = "/customer/{id}", method = RequestMethod.GET)
	public Customer getById(@PathVariable int customer_id) {
		return customerService.getById(customer_id);
	}
	
	//POST 
	@RequestMapping(value="/customer/save", method = RequestMethod.POST)
	public void saveCustomer(@RequestBody Customer customer) {
		customerService.saveCustomer(customer);
	}
}
