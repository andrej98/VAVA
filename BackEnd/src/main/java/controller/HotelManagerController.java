package controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import model.HotelManager;
import service.HotelManagerService;

/**
 * Controller trieda ktora poskytuje REST sluzby pre HotelManager
 * @author Andrej
 *
 */
@RestController
public class HotelManagerController {

	@Autowired
	private HotelManagerService managerService;
	
	/**
	 * Vola sa pri prihlasovani HotelManagera
	 * @param email 
	 * @param password
	 * @return Vrati HotelManagera s danym emailom a heslom
	 */	@RequestMapping(path = "/login/manager/{email}/{password}", method = RequestMethod.GET)
	public HotelManager login(@PathVariable String email, @PathVariable String password) {
		return managerService.login(email, password);
	}
	
	/**
	 * 
	 * @param manager_id
	 * @return Vrati HotelManagera podla jeho manager_id
	 */
	@RequestMapping(path = "/manager/{manager_id}", method = RequestMethod.GET)
	public HotelManager getById(@PathVariable int manager_id) {
		return managerService.getById(manager_id);
	}
	
	/**
	 * Ulozi noveho HotelManagera do databazy, pouziva sa pri registracii
	 * @param manager
	 */
	@RequestMapping(value="/manager/save", method = RequestMethod.POST)
	public void saveManager(@RequestBody HotelManager manager) {
		managerService.saveManager(manager);
	}
}
