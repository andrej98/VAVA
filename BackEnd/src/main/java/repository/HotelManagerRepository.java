package repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import model.HotelManager;

/**
 * Repository pre triedu HotelManager, dedi od JpaRepository
 * @author Andrej
 *
 */
@Repository
public interface HotelManagerRepository extends JpaRepository<HotelManager, Integer> {
	
	/**
	 * Vyhladavanie podla emailu a hesla
	 * @param email
	 * @param password
	 * @return Vrati HotelManagera podla emailu a hesla
	 */
	public HotelManager findByEmailAndPassword(String email, String password);
}
