package com.airtnt.frontend.booking;

import java.text.ParseException;

import com.airtnt.common.entity.Booking;
import com.airtnt.common.entity.Room;
import com.airtnt.common.entity.User;
import com.airtnt.frontend.room.RoomService;
import com.airtnt.frontend.user.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BookingRestController {

    @Autowired
    private RoomService roomService;

    @Autowired
    private BookingService bookingService;

    @Autowired
    private UserService userService;

    @GetMapping(value = "/book/create-booking/{roomId}")
    public BookingDTO createBooking(@AuthenticationPrincipal UserDetails userDetails,
            @PathVariable("roomId") Integer roomId,
            @Param("checkin") String checkin,
            @Param("checkout") String checkout,
            @Param("numberOfDays") Integer numberOfDays,
            @Param("siteFee") Float siteFee) throws ParseException {
        Room room = roomService.getRoomById(roomId);
        User customer = userService.getByEmail(userDetails.getUsername());
        Booking booking = bookingService.createBooking(checkin, checkout, room, numberOfDays, siteFee, customer);

        BookingDTO bDTO = new BookingDTO(booking.getId(), booking.getBookingDate(),
                booking.getRoom().getCurrency().getSymbol(), booking.getTotalFee(), 0);

        return bDTO;
    }
}
