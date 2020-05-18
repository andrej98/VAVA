package repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import model.Reservation;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation,Integer>{

}
