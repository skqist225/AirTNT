package com.airtnt.frontend.user;

import java.io.IOException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import com.airtnt.common.entity.User;
import com.airtnt.frontend.FileUploadUtil;
import com.airtnt.frontend.security.AirtntUserDetails;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.util.StringUtils;

@Controller
@RequestMapping("/account-settings/")
public class AccountSettings {
    @Autowired
    UserService userService;

    @GetMapping("personal-info")
    public String personalInfo(ModelMap model, @AuthenticationPrincipal AirtntUserDetails userDetails) {
        String userEmail = userDetails.getUsername();
        User user = userService.getByEmail(userEmail);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        model.addAttribute("user", user);
        model.addAttribute("userBirthday", formatter.format(user.getBirthday()));
        model.addAttribute("userSex",
                user.getSex().toString() == "MALE" ? "Nam" : user.getSex().toString() == "FEMALE" ? "Nữ" : "Khác");

        // int[] days = IntStream.range(1, 100).toArray();
        LocalDate local = LocalDate.parse(user.getBirthday().toString());
        int year = local.getYear();
        int month = local.getMonthValue();
        int day = local.getDayOfMonth();

        model.addAttribute("userDayOfBirth", day);
        model.addAttribute("userMonthOfBirth", month);
        model.addAttribute("userYearOfBirth", year);

        return "account_settings/personal_info";
    }

    @PostMapping("update-personal-info")
    public String updatePersonalInfo(User user,
            @RequestParam(name = "userAvatar", required = false) MultipartFile multipartFile, RedirectAttributes ra)
            throws IOException {
        if (!multipartFile.isEmpty()) {
            String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
            User currentUser = userService.getCurrentUser(user.getId());
            currentUser.setAvatar(fileName);

            User savedUser = userService.save(currentUser, "avatar");

            String uploadDir = "../user-photos/" + savedUser.getId();

            FileUploadUtil.cleanDir(uploadDir);
            FileUploadUtil.saveFile(uploadDir, fileName, multipartFile);
        } else {
            if (user.getAvatar().isEmpty())
                user.setAvatar(null);
            userService.save(user, "avatar");
        }
        // ra.addFlashAttribute("user", savedUser);

        return "redirect:/account-settings/personal-info";
    }
}
