package repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import model.Hotel;

@Repository
public interface HotelRepository extends JpaRepository<Hotel, Integer> {

}
