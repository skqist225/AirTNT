package com.airtnt.airtntapp.room;

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

import com.airtnt.airtntapp.booking.BookedDate;
import com.airtnt.airtntapp.booking.BookingService;
import com.airtnt.airtntapp.review.ReviewService;
import com.airtnt.airtntapp.user.UserService;
import com.airtnt.common.entity.Booking;
import com.airtnt.common.entity.Image;
import com.airtnt.common.entity.Review;
import com.airtnt.common.entity.Room;
import com.airtnt.common.entity.User;

@Controller
public class RoomController {

	@Autowired
	private RoomService roomService;

	@Autowired
	private BookingService bookingService;

	@Autowired
	private UserService userService;

	@Autowired
	private ReviewService reviewService;

	@GetMapping("/rooms/{roomId}")
	public String getRoomById(@PathVariable("roomId") Integer roomId, @AuthenticationPrincipal UserDetails userDetails,
			Model model) throws ParseException {
		Room room = roomService.getRoomById(roomId);

		User user = null;
		if (userDetails != null) {
			user = userService.getByEmail(userDetails.getUsername());
			List<Room> favRooms = new ArrayList<>(user.getRooms());

			if (favRooms.contains(room)) {
				model.addAttribute("wishlists", favRooms.get(favRooms.indexOf(room)).getId());
			}
		}

		List<BookedDate> bookedDates = bookingService.getBookedDate(room);
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

		List<Booking> bookings = bookingService.getBookingsByRoom(room);
		Integer[] bookingIds = new Integer[bookings.size()];
		for (int i = 0; i < bookings.size(); i++)
			bookingIds[i] = bookings.get(i).getId();

		List<Review> reviews = reviewService.getReviewsByBookings(bookingIds);

		List<Integer> bedCount = new ArrayList<>();
		for (int i = 0; i < room.getBedCount(); i++) {
			bedCount.add(1);
		}

		float avgRatings = 0;

		for (Review r : reviews) {
			avgRatings += r.getFinalRating();
		}

		if (reviews.size() > 0)
			avgRatings /= reviews.size();

		// checking thumbnail here
		model.addAttribute("thumbnail", room.renderThumbnailImage());
		model.addAttribute("numberOfBed", bedCount);
		model.addAttribute("roomImages", secondToFive);
		model.addAttribute("room", room);
		model.addAttribute("bookedDates", bookedDates);
		model.addAttribute("avgRatings", avgRatings);
		model.addAttribute("reviews", reviews);
		model.addAttribute("numberOfReviews", reviews.size());
		if (user == null)
			model.addAttribute("user", null);
		else
			model.addAttribute("user", user.getFullName());
		return "room/room_details";
	}

	@GetMapping(value = "room/{roomId}/delete")
	public String deleteRoom(@PathVariable("roomId") Integer roomId, Model model) {
		roomService.deleteRoom(roomId);
		model.addAttribute("deleteMessage", "Xóa thành công");

		return "redirect:/hosting/listings/1";
	}

	@GetMapping(value = "wishlists")
	public String wishlists(@AuthenticationPrincipal UserDetails userDetails, Model model) {
		User user = userService.getByEmail(userDetails.getUsername());
		model.addAttribute("wishlists", user.getRooms());
		return new String("room/wishlists");
	}
}
