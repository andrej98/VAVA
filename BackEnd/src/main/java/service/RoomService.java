package service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import model.Room;
import repository.RoomRepository;

/**
 * Service pre triedu Room
 * @author Andrej
 *
 */
@Service
public class RoomService {

	@Autowired
	private RoomRepository roomR;

	/**
	 * Ulozi novu izbu do databazy
	 * @param room
	 */
	public void saveRoom(Room room) {
		roomR.save(room);
	}

	/**
	 * Zmaze danu izbu z databazy
	 * @param room_id
	 */
	public void deleteRoom(int room_id) {
		roomR.deleteById(room_id);
	}
}
