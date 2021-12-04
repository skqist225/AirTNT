package com.airtnt.airtntapp.user;

import java.util.List;

import javax.validation.Valid;

import com.airtnt.airtntapp.booking.BookingService;
import com.airtnt.common.entity.Booking;
import com.airtnt.common.entity.Room;
import com.airtnt.common.entity.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/user/")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private BookingService bookingService;

    @GetMapping("register")
    public String register(ModelMap model) {
        User user = new User();
        model.addAttribute("user", user);
        model.addAttribute("sexError", null);
        model.addAttribute("birthdayError", null);
        return "user/register";
    }

    @PostMapping("register")
    public String registerPost(@Valid User user, BindingResult result, ModelMap model) {
        if (user.getSex() == null || !user.getSex().toString().matches("^MALE|FEMALE|OTHER$")) {
            System.out.println(user.getSex());
            model.addAttribute("sexError", "Vui lòng chọn giới tính");
            return "register";
        }

        if (user.getBirthday() == null) {
            model.addAttribute("birthdayError", "Vui lòng chọn ngày sinh");
            return "register";
        }

        if (result.hasErrors()) {
            return "register";
        } else {
            String isOkOrDuplicated = userService.isEmailUnique(user.getId(), user.getEmail()) ? "OK" : "Duplicated";
            if (isOkOrDuplicated.equals("Duplicated")) {
                model.addAttribute("duplicateEmailError", "Email đã tồn tại");
                return "register";
            } else {
                userService.registerUser(user);
            }
        }

        return "redirect:/?categoryId=1";
    }

    @GetMapping(value = "bookings")
    public String userBookings(@AuthenticationPrincipal UserDetails userDetails,
            @RequestParam(value = "query", required = false, defaultValue = "") String query, Model model) {
        if (userDetails == null) {
            return "redirect:/login";
        }

        User customer = userService.getByEmail(userDetails.getUsername());

        List<Booking> bookings = bookingService.getBookingsByUser(customer.getId(), query);
        User user = null;
        if (userDetails != null) {
            user = userService.getByEmail(userDetails.getUsername());
            Integer[] roomIds = new Integer[user.getRooms().size()];
            int i = 0;
            for (Room r : user.getRooms())
                roomIds[i++] = r.getId();
            model.addAttribute("wishlists", roomIds);
        }

        model.addAttribute("bookings", bookings);
        model.addAttribute("includeMiddle", true);
        model.addAttribute("excludeBecomeHostAndNavigationHeader", true);
        return new String("user/bookings");
    }

}
