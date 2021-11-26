package com.airtnt.frontend.manage_room;

import com.airtnt.common.entity.Room;
import com.airtnt.frontend.country.CountryService;
import com.airtnt.frontend.privacy.PrivacyTypeService;
import com.airtnt.frontend.room.RoomService;
import com.airtnt.frontend.room_group.RoomGroupService;
import com.airtnt.frontend.room_type.RoomTypeService;

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

    @Autowired
    private CountryService countryService;

    @Autowired
    private RoomGroupService roomGroupService;

    @Autowired
    private RoomTypeService roomTypeService;

    @Autowired
    private PrivacyTypeService privacyTypeService;

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
        model.addAttribute("countries", countryService.getCountries());

        model.addAttribute("roomGroup", roomGroupService.getRoomGroups());
        model.addAttribute("roomType", roomTypeService.getRoomTypes());
        model.addAttribute("privacyType", privacyTypeService.getPrivacyType());

        return new String("manage_space/manage_your_space");
    }

}
