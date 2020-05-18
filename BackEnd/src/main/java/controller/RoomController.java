package controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import model.Room;
import service.RoomService;

@RestController
public class RoomController {

	@Autowired
	private RoomService roomService;
	
	//POST
	@RequestMapping(value="room/save", method = RequestMethod.POST)
	public void saveRoom(@RequestBody Room room) {
		roomService.saveRoom(room);
	}
	
	//DELETE
	@RequestMapping(value = "room/delete/{room_id}", method = RequestMethod.DELETE)
	public void deleteRoom(@PathVariable int room_id) {
		roomService.deleteRoom(room_id);
	}
	
}
