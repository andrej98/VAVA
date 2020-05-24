package controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import model.Room;
import service.RoomService;

/**
 * Controller trieda ktora poskytuje REST sluzby pre Room
 * @author Andrej
 *
 */
@RestController
public class RoomController {

	@Autowired
	private RoomService roomService;
	
	/**
	 * Ulozenie novej izby
	 * @param room
	 */
	@RequestMapping(value="room/save", method = RequestMethod.POST)
	public void saveRoom(@RequestBody Room room) {
		roomService.saveRoom(room);
	}
	
	/**
	 * Zmazanie danej izby
	 * @param room_id
	 */
	@RequestMapping(value = "room/delete/{room_id}", method = RequestMethod.DELETE)
	public void deleteRoom(@PathVariable int room_id) {
		roomService.deleteRoom(room_id);
	}
	
}
