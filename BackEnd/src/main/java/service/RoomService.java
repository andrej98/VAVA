package service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import model.Room;
import repository.RoomRepository;

@Service
public class RoomService {

	@Autowired
	private RoomRepository roomR;

	public void saveRoom(Room room) {
		roomR.save(room);
	}

	public void deleteRoom(int room_id) {
		roomR.deleteById(room_id);
	}
}
