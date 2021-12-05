package com.airtnt.airtntapp.room;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import com.airtnt.common.entity.Amentity;
import com.airtnt.common.entity.Category;
import com.airtnt.common.entity.City;
import com.airtnt.common.entity.Country;
import com.airtnt.common.entity.Currency;
import com.airtnt.common.entity.Image;
import com.airtnt.common.entity.PriceType;
import com.airtnt.common.entity.Role;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import org.springframework.web.bind.annotation.ModelAttribute;

import com.airtnt.common.entity.RoomGroup;
import com.airtnt.common.entity.RoomPrivacy;
import com.airtnt.common.entity.RoomType;
import com.airtnt.common.entity.Rule;
import com.airtnt.common.entity.State;
import com.airtnt.common.entity.StayType;
import com.airtnt.common.entity.User;
import com.airtnt.airtntapp.booking.BookedDate;
import com.airtnt.airtntapp.booking.BookingService;
import com.airtnt.airtntapp.city.CityService;
import com.airtnt.airtntapp.review.ReviewService;
import com.airtnt.airtntapp.rule.RuleService;
import com.airtnt.airtntapp.state.StateService;
import com.airtnt.airtntapp.user.UserService;
import com.airtnt.common.entity.Booking;
import com.airtnt.common.entity.Review;
import com.airtnt.common.entity.Room;

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

	@Autowired
	private RuleService ruleService;

	@Autowired
	private StateService stateService;

	@Autowired
	private CityService cityService;

	@GetMapping("/room/{roomId}")
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

	@GetMapping(value = "room/{roomId}/publish-celebration")
	public String publishCelebration(@AuthenticationPrincipal UserDetails userDetails,
			@ModelAttribute RoomPostDTO payload,
			@PathVariable("roomId") Integer roomId, Model model) throws IOException {
		User user = userService.getByEmail(userDetails.getUsername());
		model.addAttribute("userName", user.getFullName());
		model.addAttribute("roomId", roomId);

		return "become_host/publish_celebration";
	}

	@PostMapping("room/save")
	public String saveRoom(@AuthenticationPrincipal UserDetails userDetails,
			@ModelAttribute RoomPostDTO payload) throws IOException {
		Set<Rule> rules = new HashSet<>();
		Set<Amentity> amentities = new HashSet<>();
		Set<Image> images = new HashSet<>();

		Iterator<Rule> itr = ruleService.listAllRule();
		while (itr.hasNext()) {
			rules.add(itr.next());
		}

		for (int i = 0; i < payload.getAmentities().length; i++) {
			amentities.add(new Amentity(payload.getAmentities()[i]));
		}

		for (int i = 0; i < payload.getImages().length; i++) {
			images.add(new Image(payload.getImages()[i]));
		}
		PriceType pt = payload.getPriceType() == "PER_NIGHT" ? PriceType.PER_NIGHT : PriceType.PER_WEEK;
		StayType st = payload.getStayType() == "DAY" ? StayType.DAY : StayType.WEEK;
		Country country = new Country(payload.getCountry());

		// check if state exist
		State state = stateService.getStateByName(payload.getState());
		if (state != null) { // exist

		} else {
			state = new State(payload.getState(), country);
		}

		// check if city exist
		City city = cityService.getCityByName(payload.getCity());
		if (city != null) {

		} else
			city = new City(payload.getCity(), state);

		User user = userService.getByEmail(userDetails.getUsername());
		user.setRole(new Role(1));
		userService.saveUser(user);
		Room room = Room.builder().name(payload.getName()).accomodatesCount(payload.getAccomodatesCount())
				.bathroomCount(payload.getBathroomCount()).bedCount(payload.getBedCount())
				.bedroomCount(payload.getBedroomCount()).description(payload.getDescription()).amentities(amentities)
				.images(images).latitude(payload.getLatitude()).longitude(payload.getLongitude())
				.price(payload.getPrice()).priceType(pt).minimumStay(payload.getMinimumStay()).stayType(st).city(city)
				.state(state).country(country).rules(rules).host(new User(payload.getHost()))
				.roomGroup(new RoomGroup(payload.getRoomGroup())).roomType(new RoomType(payload.getRoomType()))
				.host(new User(payload.getHost())).category(new Category(payload.getCategory()))
				.currency(new Currency(payload.getCurrency())).privacyType(new RoomPrivacy(payload.getPrivacyType()))
				.thumbnail(images.iterator().next().getImage()).street(payload.getStreet()).status(false).build();

		Room savedRoom = roomService.save(room);

		/* MOVE IMAGE TO FOLDER */
		String uploadDir = "../room_images/" + userDetails.getUsername() + "/" + savedRoom.getId();
		String source = "../room_images/" + userDetails.getUsername() + "/";
		Path sourcePath = Paths.get(source);
		Path targetPath = Files.createDirectories(Paths.get(uploadDir));
		for (String imageName : payload.getImages()) {
			Files.move(sourcePath.resolve(imageName), targetPath.resolve(imageName),
					StandardCopyOption.REPLACE_EXISTING);
		}

		return "room/" + savedRoom.getId() + "/publish-celebration";
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
