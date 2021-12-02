package com.airtnt.frontend.room;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import com.airtnt.common.entity.Amentity;
import com.airtnt.common.entity.Category;
import com.airtnt.common.entity.City;
import com.airtnt.common.entity.Country;
import com.airtnt.common.entity.Currency;
import com.airtnt.common.entity.Image;
import com.airtnt.common.entity.PriceType;
import com.airtnt.common.entity.Room;
import com.airtnt.common.entity.RoomGroup;
import com.airtnt.common.entity.RoomPrivacy;
import com.airtnt.common.entity.RoomType;
import com.airtnt.common.entity.Rule;
import com.airtnt.common.entity.State;
import com.airtnt.common.entity.StayType;
import com.airtnt.common.entity.User;
import com.airtnt.frontend.calendar.CalendarClass;
import com.airtnt.frontend.city.CityService;
import com.airtnt.frontend.rule.RuleService;
import com.airtnt.frontend.state.StateService;
import com.airtnt.frontend.user.UserService;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RoomRestController {

    @Autowired
    private RoomService roomService;

    @Autowired
    private UserService userService;

    @Autowired
    private RuleService ruleService;

    @Autowired
    private StateService stateService;

    @Autowired
    private CityService cityService;

    @GetMapping("/calendar/{selectedMonth}/{selectedYear}")
    public String getCalendayByYearAndMonth(@PathVariable("selectedYear") int selectedYear,
            @PathVariable("selectedMonth") int selectedMonth) {
        List<String> daysInMonth = CalendarClass.getDaysInMonth(selectedMonth - 1, selectedYear);
        String strDaysInMonth = daysInMonth.stream().map(Object::toString).collect(Collectors.joining(" "));
        GregorianCalendar gCal = new GregorianCalendar(selectedYear, selectedMonth - 1, 1);
        int startInWeek = gCal.get(Calendar.DAY_OF_WEEK); // ngày thứ mấy trong tuần đó
        return new JSONObject().put("daysInMonth", strDaysInMonth).put("startInWeek", startInWeek).toString();
    }

    @PostMapping(value = "/room/save")
    public String roomSave(@AuthenticationPrincipal UserDetails userDetails, @ModelAttribute RoomPostDTO payload)
            throws IOException {
        Set<Rule> rules = new HashSet<>();
        Set<Amentity> amentities = new HashSet<>();
        Set<Image> images = new HashSet<>();

        Iterator<Rule> itr = ruleService.listAllRule();
        while (itr.hasNext()) {
            rules.add(itr.next());
        }

        for (int i = 0; i < payload.getAmentities().length; i++) {
            amentities.add(new Amentity(payload.getAmentities()[i]));
        }

        for (int i = 0; i < payload.getImages().length; i++) {
            images.add(new Image(payload.getImages()[i]));
        }
        PriceType pt = payload.getPriceType() == "PER_NIGHT" ? PriceType.PER_NIGHT : PriceType.PER_WEEK;
        StayType st = payload.getStayType() == "DAY" ? StayType.DAY : StayType.WEEK;
        Country country = new Country(payload.getCountry());

        // check if state exist
        State state = stateService.getStateByName(payload.getState());
        if (state != null) { // exist

        } else {
            state = new State(payload.getState(), country);
        }

        // check if city exist
        City city = cityService.getCityByName(payload.getCity());
        if (city != null) {

        } else {
            city = new City(payload.getCity(), state);
        }

        Room room = Room.builder().name(payload.getName()).accomodatesCount(payload.getAccomodatesCount())
                .bathroomCount(payload.getBathroomCount()).bedCount(payload.getBedCount())
                .bedroomCount(payload.getBedroomCount()).description(payload.getDescription()).amentities(amentities)
                .images(images).latitude(payload.getLatitude()).longitude(payload.getLongitude())
                .price(payload.getPrice()).priceType(pt).minimumStay(payload.getMinimumStay()).stayType(st).city(city)
                .state(state).country(country).rules(rules).host(new User(payload.getHost()))
                .roomGroup(new RoomGroup(payload.getRoomGroup())).roomType(new RoomType(payload.getRoomType()))
                .host(new User(payload.getHost())).category(new Category(payload.getCategory()))
                .currency(new Currency(payload.getCurrency())).privacyType(new RoomPrivacy(payload.getPrivacyType()))
                .thumbnail(images.iterator().next().getImage()).street(payload.getStreet()).status(false).build();

        Room savedRoom = roomService.save(room);

        /* MOVE IMAGE TO FOLDER */
        String uploadDir = "../room_images/" + userDetails.getUsername() + "/" + savedRoom.getId();
        String source = "../room_images/" + userDetails.getUsername() + "/";
        Path sourcePath = Paths.get(source);
        Path targetPath = Files.createDirectories(Paths.get(uploadDir));
        for (String imageName : payload.getImages()) {
            Files.move(sourcePath.resolve(imageName), targetPath.resolve(imageName),
                    StandardCopyOption.REPLACE_EXISTING);
        }

        return new JSONObject().put("status", "OK").put("roomId", savedRoom.getId()).toString();
    }

    @PostMapping("/room/verify-phone")
    public String verifyPhoneForRoom(@RequestBody Map<String, Integer> payload) {
        Integer roomId = payload.get("roomId");
        Room room = roomService.getRoomById(roomId);
        int isUpdated = userService.verifyPhoneNumber(room.getHost().getId());
        if (isUpdated == 1)
            return "success";
        else
            return "failure";
    }

    @GetMapping(value = "add-to-wishlists/{roomId}")
    public String addToWishLists(@AuthenticationPrincipal UserDetails userDetails,
            @PathVariable("roomId") Integer roomId) {
        Room room = roomService.getRoomById(roomId);
        User user = userService.getByEmail(userDetails.getUsername());

        user.addToWishLists(room);
        User savedUser = userService.save(user);

        if (savedUser != null)
            return "success";
        return "failure";
    }

    @GetMapping(value = "remove-from-wishlists/{roomId}")
    public String removeFromWishLists(@AuthenticationPrincipal UserDetails userDetails,
            @PathVariable("roomId") Integer roomId) {
        Room room = roomService.getRoomById(roomId);
        User user = userService.getByEmail(userDetails.getUsername());

        user.removeFromWishLists(room);
        User savedUser = userService.save(user);

        if (savedUser != null)
            return "success";
        return "failure";
    }
}
