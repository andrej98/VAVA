package repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import model.Reservation;
import model.ReservationDTO;

/**
 * Repository pre triedu Reservation, dedi od JpaRepository
 * @author Andrej
 *
 */
@Repository
public interface ReservationRepository extends JpaRepository<Reservation,Integer>{

	/**
	 * Vracia zoznam ReservationDTO daneho Customera. Je pouzita JPQL query
	 * @param customer_id
	 * @return Vracia zoznam ReservationDTO daneho Customera
	 */
	@Query("SELECT new model.ReservationDTO(reservation.reservation_id, "
			+ "hotel.hotel_name,hotel.address ,hotel.city, hotel.country,hotel.stars,room.beds, "
			+ "reservation.checkin_date,reservation.checkout_date, "
			+ "room.price, "
			+ "CASE WHEN (payment is NULL) "
			+ "THEN 'no' "
			+ "ELSE 'yes' END AS paid ,room.room_id ) "
			+ "FROM Hotel hotel "
			+ "JOIN hotel.rooms room "
			+ "JOIN room.reservation reservation "
			+ "Join reservation.customer customer "
			+ "LEFT JOIN reservation.payment payment "
			+ "WHERE customer.customer_id = :customer_id "
			+ "ORDER BY hotel_name ")
	public List<ReservationDTO> getAll(@Param("customer_id") int customer_id);

}
