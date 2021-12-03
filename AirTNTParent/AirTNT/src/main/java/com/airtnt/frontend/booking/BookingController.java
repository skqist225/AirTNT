package com.airtnt.frontend.booking;

import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.airtnt.common.entity.Booking;
import com.airtnt.common.entity.Room;
import com.airtnt.common.entity.User;
import com.airtnt.frontend.room.RoomService;
import com.airtnt.frontend.user.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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

    @GetMapping(value = "listings/{pageNumber}")
    public String listings(@AuthenticationPrincipal UserDetails userDetails,
            @PathVariable("pageNumber") Integer pageNumber,
            @RequestParam(name = "booking_date_month", required = false, defaultValue = "") String bookingDateMonth,
            @RequestParam(name = "booking_date_year", required = false, defaultValue = "") String bookingDateYear,
            @RequestParam(name = "totalFee", required = false, defaultValue = "0") String totalFee,
            @RequestParam(name = "query", required = false, defaultValue = "") String query,
            @RequestParam(name = "sort_dir", required = false, defaultValue = "asc") String sortDir,
            @RequestParam(name = "sort_field", required = false, defaultValue = "id") String sortField,
            @RequestParam(name = "bookingDate", required = false, defaultValue = "") String bookingDate,
            @RequestParam(name = "isComplete", required = false, defaultValue = "") String isComplete,
            Model model) throws ParseException {
        if (userDetails == null) {
            return "redirect:/login";
        }

        User host = userService.getByEmail(userDetails.getUsername());
        List<Room> rooms = roomService.getRoomsByHostId(host);
        Integer[] roomIds = new Integer[rooms.size()];
        for (int i = 0; i < rooms.size(); i++) {
            roomIds[i] = rooms.get(i).getId();
        }
        Map<String, String> filters = new HashMap<>();
        filters.put("sortField", sortField);
        filters.put("sortDir", sortDir);
        filters.put("query", query);
        filters.put("isComplete", isComplete);
        filters.put("bookingDate", bookingDate);
        filters.put("bookingDateMonth", bookingDateMonth);
        filters.put("bookingDateYear", bookingDateYear);
        filters.put("totalFee", totalFee);

        Page<Booking> bookings = bookingService.getBookingsByRooms(roomIds, pageNumber, filters);
        model.addAttribute("bookings", bookings);
        model.addAttribute("includeMiddle", true);
        model.addAttribute("excludeBecomeHostAndNavigationHeader", true);
        model.addAttribute("totalBookings", bookings.getTotalElements());

        return new String("booking/listings");
    }

    @GetMapping(value = "/booking/{bookingId}/cancel")
    public String getMethodName(@PathVariable("bookingId") Integer bookingId,
            @AuthenticationPrincipal UserDetails userDetails, RedirectAttributes redirectAttributes) {
        if (userDetails == null) {
            return "redirect:/login";
        }

        Booking booking = bookingService.cancelBooking(bookingId);
        if (booking != null)
            redirectAttributes.addFlashAttribute("cancelMessage", "Hủy đặt phòng thành công");
        else
            redirectAttributes.addAttribute("cancelMessage", "Hủy đặt phòng thất bại");

        return "redirect:/user/bookings";
    }

    @GetMapping(value = "/booking/{bookingId}/approved")
    public String approveBooking(@PathVariable("bookingId") Integer bookingId,
            @AuthenticationPrincipal UserDetails userDetails, RedirectAttributes redirectAttributes) {
        if (userDetails == null) {
            return "redirect:/login";
        }
        User requestedUser = userService.getByEmail(userDetails.getUsername());
        Booking booking = bookingService.getBookingById(bookingId);
        User host = booking.getRoom().getHost();
        if (!host.getId().equals(requestedUser.getId())) {
            return "redirect:/";
        }

        if (bookingService.approveBooking(booking) != null)
            redirectAttributes.addFlashAttribute("approveMessage", "Duyệt lịch đặt phòng thành công");
        else
            redirectAttributes.addAttribute("approveMessage", "Duyệt lịch đặt phòng thất bại");

        return "redirect:/book/listings/1";
    }

}
