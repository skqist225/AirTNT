package com.airtnt.frontend.progress;

import java.text.ParseException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.airtnt.common.entity.Booking;
import com.airtnt.common.entity.Review;
import com.airtnt.common.entity.Room;
import com.airtnt.common.entity.User;
import com.airtnt.frontend.booking.BookingService;
import com.airtnt.frontend.review.ReviewService;
import com.airtnt.frontend.room.RoomService;
import com.airtnt.frontend.user.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequestMapping("/progress/")
public class ProgressController {

    @Autowired
    private BookingService bookingService;

    @Autowired
    private UserService userService;

    @Autowired
    private RoomService roomService;

    @Autowired
    private ReviewService reviewService;

    @GetMapping(value = "earnings")
    public String earnings(@AuthenticationPrincipal UserDetails userDetails, @Param("year") Integer year,
            Model model) throws ParseException {
        int currentYear = LocalDateTime.now().getYear();
        if (year == null) {
            return "redirect:/progress/earnings?year=" + currentYear;
        }

        User host = userService.getByEmail(userDetails.getUsername());
        List<Room> rooms = roomService.getRoomsByHostId(host);
        Integer[] roomIds = new Integer[rooms.size()];

        for (int i = 0; i < rooms.size(); i++) {
            roomIds[i] = rooms.get(i).getId();
        }

        LocalDateTime startDate = LocalDateTime.of(year, 1, 1, 0, 0, 0);
        LocalDateTime endDate = LocalDateTime.of(year, 12, 31, 0, 0, 0);

        List<Booking> bookings = bookingService.getBookingsByRooms(roomIds, startDate, endDate);

        float totalFee = 0;
        Map<Integer, Float> feesInMonth = new HashMap<>();
        Map<Integer, Integer> numberOfBookingsInMonth = new HashMap<>();
        for (Booking b : bookings) {
            if (b.getRoom().getCurrency().getSymbol().equals("$"))
                totalFee += b.getTotalFee() * 22705;
            else
                totalFee += b.getTotalFee();

            Integer monthValue = b.getBookingDate().getMonthValue();

            if (feesInMonth.containsKey(monthValue))
                feesInMonth.put(
                        monthValue,
                        b.getTotalFee() + feesInMonth.get(monthValue));
            else
                feesInMonth.put(monthValue, b.getTotalFee());

            if (numberOfBookingsInMonth.containsKey(monthValue))
                numberOfBookingsInMonth.put(
                        monthValue,
                        1 + numberOfBookingsInMonth.get(monthValue));
            else
                numberOfBookingsInMonth.put(monthValue, 1);
        }

        feesInMonth.entrySet().stream().sorted(Map.Entry.comparingByKey());
        numberOfBookingsInMonth.entrySet().stream().sorted(Map.Entry.comparingByKey());

        model.addAttribute("bookings", bookings);
        model.addAttribute("feesInMonth", feesInMonth);
        model.addAttribute("numberOfBookingsInMonth", numberOfBookingsInMonth);
        model.addAttribute("totalFee", totalFee);
        model.addAttribute("currencySymbol", "đ");
        return new String("progress/earnings");
    }

    @GetMapping(value = "reviews")
    public String reviews(@AuthenticationPrincipal UserDetails userDetails,
            @RequestParam(name = "numberOfStars", required = false, defaultValue = "0") String numberOfStars,
            Model model) throws ParseException {
        User host = userService.getByEmail(userDetails.getUsername());
        List<Room> rooms = roomService.getRoomsByHostId(host);
        Integer[] roomIds = new Integer[rooms.size()];

        for (int i = 0; i < rooms.size(); i++) {
            roomIds[i] = rooms.get(i).getId();
        }
        float finalRatings = 0;
        List<Review> reviews = reviewService.getReviewsByRoom(roomIds, Integer.parseInt(numberOfStars));
        for (Review r : reviews) {
            finalRatings += r.getFinalRating();
        }
        model.addAttribute("reviews", reviews);
        model.addAttribute("finalRatings", finalRatings / reviews.size());

        return new String("progress/reviews");
    }

}
