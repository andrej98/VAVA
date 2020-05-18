package controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import model.Customer;
import model.HotelManager;
import service.CustomerService;
import service.HotelManagerService;

@RestController
public class HotelManagerController {

	@Autowired
	private HotelManagerService managerService;
	
	//GET by login
	@RequestMapping(path = "/login/manager/{email}/{password}", method = RequestMethod.GET)
	public HotelManager login(@PathVariable String email, @PathVariable String password) {
		return managerService.login(email, password);
	}
	
	//GET by id
	@RequestMapping(path = "/manager/{manager_id}", method = RequestMethod.GET)
	public HotelManager getById(@PathVariable int manager_id) {
		return managerService.getById(manager_id);
	}
	
	//POST 
	@RequestMapping(value="/manager/save", method = RequestMethod.POST)
	public void saveManager(@RequestBody HotelManager manager) {
		managerService.saveManager(manager);
	}
}
