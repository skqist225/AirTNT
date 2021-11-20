package com.airtnt.frontend.hosting;

import com.airtnt.common.entity.User;
import com.airtnt.frontend.user.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class VerifyController {

    @Autowired
    UserService userService;

    @GetMapping(value = "verify-listing")
    public String verifyListing(@AuthenticationPrincipal UserDetails userDetails, Model model) {
        // String userName = userDetails.getUsername();
        // User user = userService.getByEmail(userName);
        // model.addAttribute("userName", user.getFullName());
        // model.addAttribute("userAvatar", user.getAvatarPath());
        model.addAttribute("excludeBecomeHostAndNavigationHeader", true);
        return new String("hosting/verify_listing");
    }

}
