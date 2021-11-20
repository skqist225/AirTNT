package com.airtnt.frontend.host;

import java.util.ArrayList;
import java.util.List;

import com.airtnt.common.entity.Amentity;
import com.airtnt.common.entity.RoomGroup;
import com.airtnt.common.entity.RoomType;
import com.airtnt.common.entity.User;
import com.airtnt.frontend.amentity.AmentityService;
import com.airtnt.frontend.country.CountryService;
import com.airtnt.frontend.room_group.RoomGroupService;
import com.airtnt.frontend.room_type.RoomTypeService;
import com.airtnt.frontend.user.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/become-a-host/")
public class HostController {

    @Autowired
    RoomGroupService roomGroupService;

    @Autowired
    RoomTypeService roomTypeService;

    @Autowired
    UserService userService;

    @Autowired
    AmentityService amentityService;

    @Autowired
    CountryService countryService;

    @GetMapping("")
    public String index() {
        return "become_host/index";
    }

    @GetMapping("property-type-group")
    public String roomGroupSelect(Model model) {
        List<RoomGroup> roomGroups = roomGroupService.getRoomGroups();
        model.addAttribute("roomGroups", roomGroups);
        return "become_host/property_type_group";
    }

    @GetMapping("property-type")
    public String roomTypeSelect(Model model) {
        List<RoomType> roomTypes = roomTypeService.getRoomTypes();
        model.addAttribute("roomTypes", roomTypes);
        return "become_host/property_type";
    }

    @GetMapping("privacy-type")
    public String privaceTypeSelect(Model model) {
        List<String> privacyTypes = new ArrayList<>();
        privacyTypes.add("Toàn bộ nhà");
        privacyTypes.add("Phòng riêng");
        privacyTypes.add("Phòng chung");
        model.addAttribute("privacyTypes", privacyTypes);

        return "become_host/privacy_type";
    }

    @GetMapping("location")
    public String locationSelect(Model model, @AuthenticationPrincipal UserDetails userDetails) {
        String userName = userDetails.getUsername();
        User user = userService.getByEmail(userName);
        model.addAttribute("userAvatar", user.getAvatarPath());
        model.addAttribute("userName", user.getFullName());
        model.addAttribute("countries", countryService.getCountries());
        return "become_host/location";
    }

    @GetMapping("room-info")
    public String roomInfoSelect(Model model, @AuthenticationPrincipal UserDetails userDetails) {
        // String userName = userDetails.getUsername();
        // User user = userService.getByEmail(userName);
        // model.addAttribute("userAvatar", user.getAvatarPath());
        // model.addAttribute("userName", user.getFullName());
        return "become_host/room_info";
    }

    @GetMapping("amenities")
    public String amenitiesSelect(Model model) {
        List<Amentity> prominentAmentities = amentityService.getAmentities("prominent");
        List<Amentity> favoriteAmentities = amentityService.getAmentities("favorite");
        List<Amentity> safeAmentities = amentityService.getAmentities("safe");

        model.addAttribute("prominentAmentities", prominentAmentities);
        model.addAttribute("favoriteAmentities", favoriteAmentities);
        model.addAttribute("safeAmentities", safeAmentities);
        return "become_host/amenities";
    }

    @GetMapping("photos")
    public String roomImagesSelect(Model model, @AuthenticationPrincipal UserDetails userDetails) {
        model.addAttribute("userName", userDetails.getUsername());
        return "become_host/photos";
    }

    @GetMapping("title")
    public String titleSelect(Model model) {
        return "become_host/title";
    }

    @GetMapping("description")
    public String descriptionSelect(Model model) {
        return "become_host/description";
    }

    @GetMapping("price")
    public String priceSelect(Model model) {
        return "become_host/price";
    }

    @GetMapping(value = "preview")
    public String previewRoom(@AuthenticationPrincipal UserDetails userDetails, Model model) {
        String userName = userDetails.getUsername();
        User user = userService.getByEmail(userName);
        model.addAttribute("userAvatar", user.getAvatarPath());
        model.addAttribute("userName", user.getFullName());
        model.addAttribute("host", user.getId());
        return "become_host/preview";
    }

    @GetMapping(value = "publish-celebration")
    public String publishCelebration(@AuthenticationPrincipal UserDetails userDetails, Model model) {
        String userName = userDetails.getUsername();
        User user = userService.getByEmail(userName);
        model.addAttribute("userName", user.getFullName());
        return "become_host/publish_celebration";
    }

}
