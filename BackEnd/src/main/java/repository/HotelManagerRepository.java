package repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import model.Customer;
import model.HotelManager;

@Repository
public interface HotelManagerRepository extends JpaRepository<HotelManager, Integer> {
	public HotelManager findByEmailAndPassword(String email, String password);
}