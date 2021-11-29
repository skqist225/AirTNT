package com.airtnt.frontend.room;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import com.airtnt.common.entity.Image;
import com.airtnt.common.entity.Room;
import com.airtnt.frontend.booking.BookedDate;
import com.airtnt.frontend.booking.BookingService;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class RoomController {

	@Autowired
	private RoomService roomService;

	@Autowired
	private BookingService bookingService;

	@GetMapping("/rooms/{roomId}")
	public String getRoomById(@PathVariable("roomId") Integer roomId, Model model) throws ParseException {
		Room room = roomService.getRoomById(roomId);

		List<Image> images = new ArrayList<>(room.getImages());
		List<Image> secondToFive = new ArrayList<>();
		for (int i = 0; i < images.size(); i++) {
			if (secondToFive.size() == 4)
				break;

			if (images.get(i).getImage().equals(room.getThumbnail()))
				continue;
			else
				secondToFive.add(images.get(i));
		}

		List<Integer> bedCount = new ArrayList<>();
		for (int i = 0; i < room.getBedCount(); i++) {
			bedCount.add(1);
		}

		// checking thumbnail here
		model.addAttribute("thumbnail", room.renderThumbnailImage());
		model.addAttribute("numberOfBed", bedCount);
		model.addAttribute("roomImages", secondToFive);
		model.addAttribute("room", room);

		List<BookedDate> bookedDates = bookingService.getBookedDate(room);
		model.addAttribute("bookedDates", bookedDates);
		return "room/room_details";
	}

	@GetMapping(value = "wishlists")
	public String wishlists(@AuthenticationPrincipal UserDetails userDetails, Model model) {
		return new String("room/wishlists");
	}

}
