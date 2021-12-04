package com.airtnt.airtntapp.user;

import java.util.HashMap;
import java.util.Map;

import com.airtnt.airtntapp.security.AirtntUserDetails;
import com.airtnt.common.entity.User;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserRestController {
    @Autowired
    UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    private Map<String, String> checkConstraint = new HashMap<>();

    @GetMapping("/user/avatar")
    public String getUserAvatar(@AuthenticationPrincipal AirtntUserDetails userDetails) {
        if (userDetails == null)
            return "/images/default_user_avatar.png";

        User user = userService.getByEmail(userDetails.getUsername());
        return user.getAvatarPath();
    }

    @PostMapping("/user/check-password-constraint")
    public String checkPasswordConstaint(@RequestBody Map<String, String> payLoad,
            @AuthenticationPrincipal AirtntUserDetails userDetails) {
        Integer userId = Integer.parseInt(payLoad.get("userId").toString());
        User currentUser = userService.getCurrentUser(userId);
        String oldPassword = payLoad.get("oldPassword").toString();
        String newPassword = payLoad.get("newPassword").toString();

        checkConstraint.put("oldPasswordError", "Vui lòng nhập mật khẩu cũ.");
        checkConstraint.put("newPasswordError", "Vui lòng nhập mật khẩu mới.");

        JSONObject jsonObject = new JSONObject();

        if (oldPassword.isEmpty() && newPassword.isEmpty()) {
            jsonObject.put("oldPasswordError", checkConstraint.get("oldPasswordError")).put("newPasswordError",
                    checkConstraint.get("newPasswordError"));

            return jsonObject.toString();
        }
        if (oldPassword.isEmpty()) {
            jsonObject.put("oldPasswordError", checkConstraint.get("oldPasswordError"));
            return jsonObject.toString();
        }
        if (newPassword.isEmpty()) {
            jsonObject.put("newPasswordError", checkConstraint.get("newPasswordError"));
            return jsonObject.toString();
        }

        if (!passwordEncoder.matches(oldPassword, currentUser.getPassword())) {
            jsonObject.put("oldPasswordError", "Mật khẩu cũ không hợp lệ!!!");
            return jsonObject.toString();
        }

        if (newPassword.length() < 8) {
            jsonObject.put("newPasswordError", "Mật khẩu mới phải ít nhất 8 kí tự.");
            return jsonObject.toString();
        }

        return jsonObject.put("status", "OK").toString();
    }

    @PostMapping("/user/check-first-name-and-last-name-constraint")
    public String checkFirstNameAndLastNameConstraint(@RequestBody Map<String, String> payLoad) {

        String firstName = payLoad.get("firstName").toString();
        String lastName = payLoad.get("lastName").toString();

        System.out.println(firstName);
        System.out.println(lastName);

        JSONObject jsonObject = new JSONObject();

        checkConstraint.put("firstNameError", "Vui lòng nhập tên.");
        checkConstraint.put("lastNameError", "Vui lòng nhập họ.");

        if (firstName.isEmpty() && lastName.isEmpty()) {
            jsonObject.put("firstNameError", checkConstraint.get("firstNameError")).put("lastNameError",
                    checkConstraint.get("lastNameError"));

            return jsonObject.toString();
        }
        if (firstName.isEmpty()) {
            jsonObject.put("firstNameError", checkConstraint.get("firstNameError"));
            return jsonObject.toString();
        }
        if (lastName.isEmpty()) {
            jsonObject.put("lastNameError", checkConstraint.get("lastNameError"));
            return jsonObject.toString();
        }

        return jsonObject.put("status", "OK").toString();
    }

    @PostMapping("/user/check-email-constraint")
    public String checkEmailConstraint(@RequestBody Map<String, String> payLoad,
            @AuthenticationPrincipal AirtntUserDetails userDetails) {
        String email = payLoad.get("email").toString();
        Integer userId = Integer.parseInt(payLoad.get("userId").toString());

        return userService.isEmailUnique(userId, email) ? "OK" : "Duplicated";
    }
}
