package com.airtnt.frontend.room;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.airtnt.common.entity.Room;

@Controller
public class RoomController {
	
	@Autowired
	private RoomService roomService;

    @GetMapping("/room/{id}")
    public String getRoomById(@PathVariable("id") int id, Model model) {
    	
    	System.out.println(id);
    	
    	Room room = roomService.getRoomById(id);
    	
    	model.addAttribute("room", room);
    	
    	return "room/room_details";
    }
}
