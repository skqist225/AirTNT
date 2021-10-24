package com.airtnt.frontend.user;

import javax.validation.Valid;

import com.airtnt.common.entity.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user/")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("register")
    public String register(ModelMap model) {
        User user = new User();
        model.addAttribute("user", user);
        model.addAttribute("sexError", null);
        model.addAttribute("birthdayError", null);
        return "register";
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

        return "index";
    }

}
