package repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import model.Hotel;

/**
 * Repository pre triedu HotelManager, dedi od JpaRepository
 * @author Andrej
 *
 */
@Repository
public interface HotelRepository extends JpaRepository<Hotel, Integer>, JpaSpecificationExecutor<Hotel> {

	/**
	 * Sluzi na filtrovanie hotelov podla zadanych kriterii, kriteria su volitelne, ak su nezadane, neberu sa do uvahy. 
	 * Na filtrovanie pouzivam nativnu query 
	 * @param hotel_name
	 * @param city
	 * @param country
	 * @param price
	 * @param beds
	 * @param stars
	 * @return Vrati zoznam hotelov splnajucich zadane kriteria
	 */
	@Query(value="SELECT manager_id,hotel.hotel_id, hotel_name, address, city, country, stars, COUNT(room.hotel_id) AS rooms_count "
			+ "FROM hotel LEFT JOIN room ON room.hotel_id = hotel.hotel_id "
			+ "WHERE (LOWER(hotel_name) LIKE LOWER('%' || :hotel_name || '%')) "
			+ "AND (LOWER(city) LIKE LOWER('%' || :city || '%')) "
			+ "AND (LOWER(country) LIKE LOWER('%' || :country || '%')) "
			+ "AND ((price <= :price) OR (:price = 0 )) "
			+ "AND ((beds = :beds) OR (:beds = 0 )) "
			+ "AND ((stars = :stars) OR (:stars = 0 )) "
			+ "GROUP BY hotel.hotel_id HAVING COUNT(room.hotel_id) > 0", nativeQuery=true)
	public List<Hotel> filter(@Param("hotel_name")String hotel_name, @Param("city")String city,
			@Param("country") String country , @Param("price") Integer price, @Param("beds") Integer beds,@Param("stars") Integer stars);
}
