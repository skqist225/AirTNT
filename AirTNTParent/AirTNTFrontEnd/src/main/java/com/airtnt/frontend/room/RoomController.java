package com.airtnt.frontend.room;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import com.airtnt.common.entity.Image;
import com.airtnt.common.entity.Room;

@Controller
public class RoomController {

	@Autowired
	private RoomService roomService;

	@GetMapping("/rooms/{id}")
	public String getRoomById(@PathVariable("id") int id, Model model) {
		Room room = roomService.getRoomById(id);

		Image firstImage = room.getImages().iterator().next();
		Iterator<Image> images = room.getImages().iterator();
		Set<Image> secondToFive = new HashSet<>();
		byte count = 0;
		while (images.hasNext()) {
			if (count <= 4) {
				secondToFive.add(images.next());
			} else {
				break;
			}
			count++;
		}
		secondToFive.remove(firstImage);
		List<Integer> bedCount = new ArrayList<>();
		for (int i = 0; i < Integer.parseInt(room.getBedCount()); i++) {
			bedCount.add(1);
		}

		// checking thumbnail here

		model.addAttribute("thumbnail", firstImage);
		model.addAttribute("numberOfBed", bedCount);
		model.addAttribute("roomImages", secondToFive);
		model.addAttribute("room", room);

		return "room/room_details";
	}

}
