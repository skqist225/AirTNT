package com.airtnt.frontend.booking;

import java.text.ParseException;

import com.airtnt.common.entity.Booking;
import com.airtnt.common.entity.Room;
import com.airtnt.common.entity.User;
import com.airtnt.frontend.room.RoomService;
import com.airtnt.frontend.user.UserService;
import com.stripe.param.SourceCreateParams.Redirect;

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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/book/")
public class BookingController {

    @Autowired
    private RoomService roomService;

    @Autowired
    private UserService userService;

    @Autowired
    private BookingService bookingService;

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

    @GetMapping(value = "success-booking")
    public String booking(@AuthenticationPrincipal UserDetails userDetails, Model model) throws ParseException {
        User user = userService.getByEmail(userDetails.getUsername());
        model.addAttribute("username", user.getFullName());
        return new String("booking/success");
    }

    @GetMapping(value = "listings")
    public String listings(Model model) throws ParseException {
        return new String("booking/listings");
    }

    @GetMapping(value = "/booking/{bookingId}/cancel")
    public String getMethodName(@PathVariable("bookingId") Integer bookingId, RedirectAttributes redirectAttributes) {
        Booking booking = bookingService.cancelBooking(bookingId);

        if (booking != null) {
            redirectAttributes.addFlashAttribute("cancelMessage", "Hủy đặt phòng thành công");
        } else
            redirectAttributes.addAttribute("cancelMessage", "Hủy đặt phòng thất bại");

        return "redirect:/user/bookings";
    }

}
