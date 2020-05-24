package service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import model.HotelManager;
import repository.HotelManagerRepository;

/**
 * Service pre triedu HotelManager
 * @author Andrej
 *
 */
@Service
public class HotelManagerService {

	@Autowired
	private HotelManagerRepository managerR;

	/**
	 * Vola sa pri prihlasovani HotelManagera
	 * @param email 
	 * @param password
	 * @return Vrati HotelManagera s danym emailom a heslom
	 */
	public HotelManager login(String email, String password) {
		HotelManager man = managerR.findByEmailAndPassword(email, password);
		System.out.println(man.getEmail().toString());
		return man;
	}

	/**
	 * Ulozenie noveho HotelManagera do databazy 
	 * @param manager
	 */
	public void saveManager(HotelManager manager) {
		managerR.save(manager);
		
	}

	/**
	 * Vrati HotelManager podla manager_id
	 * @param manager_id
	 * @return
	 */
	public HotelManager getById(int manager_id) {
		Optional<HotelManager> optionalM = managerR.findById(manager_id);
		if(optionalM.isPresent()) {
			return optionalM.get();
		}
		return null;
	}
}
