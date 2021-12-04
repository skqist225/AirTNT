package com.airtnt.airtntapp.user;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import com.airtnt.airtntapp.FileUploadUtil;
import com.airtnt.airtntapp.booking.BookingService;
import com.airtnt.airtntapp.city.CityService;
import com.airtnt.airtntapp.country.CountryService;
import com.airtnt.airtntapp.security.AirtntUserDetails;
import com.airtnt.airtntapp.state.StateService;
import com.airtnt.common.entity.Address;
import com.airtnt.common.entity.Booking;
import com.airtnt.common.entity.City;
import com.airtnt.common.entity.Country;
import com.airtnt.common.entity.Room;
import com.airtnt.common.entity.State;
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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.util.StringUtils;

@Controller
@RequestMapping("/user/")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private BookingService bookingService;

    @Autowired
    private CountryService countryService;

    @Autowired
    private StateService stateService;

    @Autowired
    private CityService cityService;

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
            model.addAttribute("sexError", "Vui lòng chọn giới tính");
            return "user/register";
        }

        if (user.getBirthday() == null) {
            model.addAttribute("birthdayError", "Vui lòng chọn ngày sinh");
            return "user/register";
        }

        if (result.hasErrors()) {
            return "user/register";
        } else {
            String isOkOrDuplicated = userService.isEmailUnique(user.getId(), user.getEmail()) ? "OK" : "Duplicated";
            if (isOkOrDuplicated.equals("Duplicated")) {
                model.addAttribute("duplicateEmailError", "Email đã tồn tại");
                return "user/register";
            } else {
                userService.registerUser(user);
            }
        }

        return "redirect:/login";
    }

    @GetMapping(value = "bookings")
    public String userBookings(@AuthenticationPrincipal UserDetails userDetails,
            @RequestParam(value = "query", required = false, defaultValue = "") String query, Model model) {
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
        if (user == null)
            model.addAttribute("user", null);
        else
            model.addAttribute("user", user.getFullName());

        model.addAttribute("bookings", bookings);
        model.addAttribute("includeMiddle", true);
        model.addAttribute("excludeBecomeHostAndNavigationHeader", true);
        return new String("user/bookings");
    }

    @GetMapping("personal-info")
    public String personalInfo(ModelMap model, @AuthenticationPrincipal AirtntUserDetails userDetails) {
        String userEmail = userDetails.getUsername();
        User user = userService.getByEmail(userEmail);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        List<Country> countries = countryService.getCountries();
        List<State> states = stateService.listAll();
        List<City> cities = cityService.listAll();

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
        model.addAttribute("states", states);
        model.addAttribute("cities", cities);

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
            @RequestParam(name = "userCountryName", required = false) Integer countryId,
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
            userService.encodePassword(currentUser);
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
                // String cityCode = request.getParameter(cityName);
                city = cityService.addCity(cityName, state);
            }
            String aprtNoAndStreet = request.getParameter("address.aprtNoAndStreet");
            Address newAddress = new Address(country, state, city, aprtNoAndStreet);
            currentUser.setAddress(newAddress);
            savedUser = userService.save(currentUser, "address");
        }

        ra.addFlashAttribute("user", savedUser);
        return "redirect:/user/personal-info";
    }
}
