package repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import model.Customer;

/**
 * Repository pre triedu Customer, dedi od JpaRepository
 * @author Andrej
 *
 */
@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {

	/**
	 * Vyhladavanie podla emailu a hesla
	 * @param email
	 * @param password
	 * @return Vrati Customera podla emailu a hesla
	 */
	public Customer findByEmailAndPassword(String email, String password);
}
