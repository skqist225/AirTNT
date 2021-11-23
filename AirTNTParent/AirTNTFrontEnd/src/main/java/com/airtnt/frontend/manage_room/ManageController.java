package com.airtnt.frontend.manage_room;

import com.airtnt.common.entity.Room;
import com.airtnt.frontend.room.RoomService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class ManageController {

    @Autowired
    private RoomService roomService;

    @GetMapping(value = "/manage-your-space/{roomId}/details")
    public String getMethodName(@PathVariable("roomId") Integer roomId,
            @AuthenticationPrincipal UserDetails userDetails, Model model) {
        if (userDetails == null) {
            return "redirect:/login";
        }

        Room room = roomService.getRoomById(roomId);
        model.addAttribute("room", room);
        model.addAttribute("firstDescription", room.getDescription().split(",")[0].toLowerCase());
        model.addAttribute("secondDescription", room.getDescription().split(",")[1].toLowerCase());
        model.addAttribute("userName", room.getHost().getEmail());

        return new String("manage_space/manage_your_space");
    }

}
