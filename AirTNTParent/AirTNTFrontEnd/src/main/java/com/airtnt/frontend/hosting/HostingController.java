package com.airtnt.frontend.hosting;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Month;
import java.time.Year;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.List;

import com.airtnt.common.entity.Amentity;
import com.airtnt.common.entity.Room;
import com.airtnt.common.entity.User;
import com.airtnt.frontend.amentity.AmentityService;
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
@RequestMapping("/hosting/")
public class HostingController {

    @Autowired
    private UserService userService;

    @Autowired
    private AmentityService amentityService;

    @Autowired
    private RoomService roomService;

    @GetMapping(value = "")
    public String index(Model model) {
        model.addAttribute("excludeBecomeHostAndNavigationHeader", true);
        return new String("hosting/index");
    }

    @GetMapping(value = "listings")
    public String listings(@AuthenticationPrincipal UserDetails userDetails, Model model) {
        String userName = userDetails.getUsername();
        User user = userService.getByEmail(userName);
        model.addAttribute("userName", user.getFullName());
        model.addAttribute("userEmail", user.getEmail());
        model.addAttribute("userAvatar", user.getAvatarPath());
        List<Room> rooms = roomService.getRoomsByHostId(user, 1);
        model.addAttribute("roomsLength", rooms.size());
        model.addAttribute("rooms", rooms);

        List<Amentity> amentities = amentityService.getAllAmentities();
        model.addAttribute("amentities", amentities);
        return new String("hosting/listings");
    }

    // @GetMapping("manage-your-space/{userId}/details")
    // public SomeData getMethodName(@RequestParam String param) {
    // return new SomeData();
    // }

}
