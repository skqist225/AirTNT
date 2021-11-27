package com.airtnt.frontend.user;

import java.io.IOException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.airtnt.common.entity.Address;
import com.airtnt.common.entity.City;
import com.airtnt.common.entity.Country;
import com.airtnt.common.entity.State;
import com.airtnt.common.entity.User;
import com.airtnt.frontend.FileUploadUtil;
import com.airtnt.frontend.city.CityService;
import com.airtnt.frontend.country.CountryService;
import com.airtnt.frontend.security.AirtntUserDetails;
import com.airtnt.frontend.state.StateService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.password.PasswordEncoder;
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

    @Autowired
    CountryService countryService;

    @Autowired
    StateService stateService;

    @Autowired
    CityService cityService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("personal-info")
    public String personalInfo(ModelMap model, @AuthenticationPrincipal AirtntUserDetails userDetails) {
        String userEmail = userDetails.getUsername();
        User user = userService.getByEmail(userEmail);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        List<Country> countries = countryService.getCountries();
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

        model.addAttribute("countries", countries);

        return "account_settings/personal_info";
    }

    @PostMapping("update-personal-info")
    public String updatePersonalInfo(User user,
            @RequestParam(name = "userAvatar", required = false) MultipartFile userAvatar,
            @RequestParam(name = "updatedField") String updatedField,
            @RequestParam(name = "newPassword", required = false) String newPassword,
            @RequestParam(name = "userDayOfBirth", required = false) Integer userDayOfBirth,
            @RequestParam(name = "userMonthOfBirth", required = false) Integer userMonthOfBirth,
            @RequestParam(name = "userYearOfBirth", required = false) Integer userYearOfBirth,
            @RequestParam(name = "userCountryName", required = false) int countryId,
            @RequestParam(name = "userStateName", required = false) String stateName,
            @RequestParam(name = "userCityName", required = false) String cityName, HttpServletRequest request,
            RedirectAttributes ra) throws IOException {
        User currentUser = userService.getCurrentUser(user.getId());
        User savedUser = null;

        if (updatedField.equals("avatar")) {
            if (!userAvatar.isEmpty()) {
                String fileName = StringUtils.cleanPath(userAvatar.getOriginalFilename());

                currentUser.setAvatar(fileName);

                savedUser = userService.save(currentUser, "avatar");

                String uploadDir = "../user-photos/" + savedUser.getId();

                FileUploadUtil.cleanDir(uploadDir);
                FileUploadUtil.saveFile(uploadDir, fileName, userAvatar);
            } else {
                if (user.getAvatar().isEmpty())
                    user.setAvatar(null);
                savedUser = userService.save(user, "avatar");
            }
        }

        if (updatedField.equals("password")) {
            currentUser.setPassword(passwordEncoder.encode(newPassword));
            savedUser = userService.save(currentUser, "password");
        }

        if (updatedField.equals("firstNameAndLastName")) {
            currentUser.setFirstName(user.getFirstName());
            currentUser.setLastName(user.getLastName());
            savedUser = userService.save(currentUser, "firstNameAndLastName");
        }

        if (updatedField.equals("birthday")) {
            currentUser.setBirthday(LocalDate.of(userYearOfBirth, userMonthOfBirth, userDayOfBirth));
            savedUser = userService.save(currentUser, "birthday");
        }

        if (updatedField.equals("email")) {
            currentUser.setEmail(user.getEmail());
            savedUser = userService.save(currentUser, "email");
        }

        if (updatedField.equals("phoneNumber")) {
            currentUser.setPhoneNumber(user.getPhoneNumber());
            savedUser = userService.save(currentUser, "phoneNumber");
        }

        if (updatedField.equals("address")) {
            Country country = countryService.getCountryById(countryId);
            State state = stateService.getStateByName(stateName);
            City city = cityService.getCityByName(cityName);
            if (state == null) { // does not exist in db
                System.out.println("state is not existed");
                String stateCode = request.getParameter(stateName);
                state = stateService.addState(stateName, stateCode, country);
            }

            if (city == null) {
                System.out.println("city is not existed");
                String cityCode = request.getParameter(cityName);
                city = cityService.addCity(cityName, state);
            }
            String aprtNoAndStreet = request.getParameter("address.aprtNoAndStreet");
            Address newAddress = new Address(country, state, city, aprtNoAndStreet);
            currentUser.setAddress(newAddress);
            savedUser = userService.save(currentUser, "address");
        }

        ra.addFlashAttribute("user", savedUser);
        return "redirect:/account-settings/personal-info";
    }
}
