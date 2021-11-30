package com.airtnt.frontend.progress;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.airtnt.common.entity.Booking;
import com.airtnt.common.entity.Room;
import com.airtnt.common.entity.User;
import com.airtnt.frontend.booking.BookingService;
import com.airtnt.frontend.room.RoomService;
import com.airtnt.frontend.user.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
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

    @GetMapping(value = "earnings")
    public String earnings(@AuthenticationPrincipal UserDetails UserDetails, Model model) throws ParseException {
        User host = userService.getByEmail(UserDetails.getUsername());
        List<Room> rooms = roomService.getRoomsByHostId(host);
        List<Integer> roomIds = new ArrayList<>();
        for (Room r : rooms) {
            roomIds.add(r.getId());
        }

        LocalDateTime startDate = LocalDateTime.of(2021, 1, 1, 0, 0, 0);
        LocalDateTime endDate = LocalDateTime.of(2021, 12, 31, 0, 0, 0);

        List<Booking> bookings = bookingService.getBookingsByRooms(roomIds, startDate, endDate);

        float totalFee = 0;
        Map<Integer, Float> feesInMonth = new HashMap<>();
        Map<Integer, Integer> numberOfBookingsInMonth = new HashMap<>();
        for (Booking b : bookings) {
            totalFee += b.getTotalFee();
            Integer monthValue = b.getBookingDate().getMonthValue();

            if (feesInMonth.get(monthValue) == null)
                feesInMonth.put(monthValue, b.getTotalFee());
            else
                feesInMonth.put(
                        monthValue,
                        b.getTotalFee() + feesInMonth.get(monthValue));

            if (numberOfBookingsInMonth.get(monthValue) == null)
                numberOfBookingsInMonth.put(monthValue, 1);
            else
                numberOfBookingsInMonth.put(
                        monthValue,
                        1 + numberOfBookingsInMonth.get(monthValue));
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

}
