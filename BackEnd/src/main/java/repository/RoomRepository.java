package repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import model.Room;

/**
 * Repository pre triedu Reservation, dedi od JpaRepository
 * @author Andrej
 *
 */
@Repository
public interface RoomRepository extends JpaRepository<Room,Integer>{

}
