package service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import model.HotelManager;
import repository.HotelManagerRepository;

@Service
public class HotelManagerService {

	@Autowired
	private HotelManagerRepository managerR;

	public HotelManager login(String email, String password) {
		HotelManager man = managerR.findByEmailAndPassword(email, password);
		System.out.println(man.getEmail().toString());
		return man;
	}

	public void saveManager(HotelManager manager) {
		managerR.save(manager);
		
	}

	public HotelManager getById(int manager_id) {
		Optional<HotelManager> optionalM = managerR.findById(manager_id);
		if(optionalM.isPresent()) {
			return optionalM.get();
		}
		return null;
	}
}
