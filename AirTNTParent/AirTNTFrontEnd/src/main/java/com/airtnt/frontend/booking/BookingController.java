package com.airtnt.frontend.booking;

import java.text.ParseException;

import com.airtnt.common.entity.Booking;
import com.airtnt.common.entity.Room;
import com.airtnt.common.entity.User;
import com.airtnt.frontend.room.RoomService;
import com.airtnt.frontend.user.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/book/")
public class BookingController {

    @Autowired
    private RoomService roomService;

    @Autowired
    private BookingService bookingService;

    @Autowired
    private UserService userService;

    @Value("${stripe.pubkey}")
    private String stripePublicKey;

    @GetMapping("{roomId}")
    public String roomBookings(@PathVariable("roomId") Integer roomId, @Param("checkin") String checkin,
            @Param("checkout") String checkout, @Param("numberOfNights") Integer numberOfNights, Model model) {
        Room room = roomService.getRoomById(roomId);
        String[] checkinArr = checkin.split("-");
        String[] checkoutArr = checkout.split("-");

        model.addAttribute("checkin", checkinArr[0] + " thg " + checkinArr[1] + ", " + checkinArr[2]);
        model.addAttribute("checkout", checkoutArr[0] + " thg " + checkoutArr[1] + ", " + checkoutArr[2]);
        model.addAttribute("checkinPlain", checkin);
        model.addAttribute("checkoutPlain", checkout);
        model.addAttribute("room", room);
        model.addAttribute("numberOfNights", numberOfNights);
        model.addAttribute("stripePublicKey", stripePublicKey);

        return "booking/booking";
    }

    @GetMapping(value = "success-booking/{roomId}")
    public String successBooking(@AuthenticationPrincipal UserDetails userDetails,
            @PathVariable("roomId") Integer roomId,
            @Param("checkin") String checkin,
            @Param("checkout") String checkout,
            @Param("numberOfDays") Integer numberOfDays,
            @Param("siteFee") Float siteFee, Model model) throws ParseException {
        Room room = roomService.getRoomById(roomId);
        User customer = userService.getByEmail(userDetails.getUsername());
        Booking booking = bookingService.createBooking(checkin, checkout, room, numberOfDays, siteFee, customer);

        if (booking == null) {
            return new String("booking/failure");
        }

        model.addAttribute("booking", booking);
        return new String("booking/success");
    }
}
