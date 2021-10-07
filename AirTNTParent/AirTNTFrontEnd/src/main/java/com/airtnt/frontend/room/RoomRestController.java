package com.airtnt.frontend.room;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import com.airtnt.common.entity.Category;
import com.airtnt.common.entity.Image;
import com.airtnt.common.entity.Room;
import com.airtnt.frontend.calendar.CalendarClass;
import com.airtnt.frontend.category.CategoryService;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RoomRestController {

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private RoomService roomService;

    @PostMapping("/homes")
    public String fetchRoomsByCategoryId(@RequestBody Map<String, Object> payLoad) {
        int id = Integer.parseInt(payLoad.get("catId").toString());
        int page = Integer.parseInt(payLoad.get("page").toString());

        System.out.println("id" + id);
        System.out.println("page" + page);

        Category category = categoryService.getCategoryById(id);
        JSONArray array = new JSONArray();

        // Replicate room to test
        List<Room> roomT = roomService.getRoomsByCategoryId(category, page);
        if (roomT.size() > 0) {
            for (int i = 0; i <= 50; i++) {
                roomT.add(roomT.get(i));
            }
            // Replicate room to test

            try {
                for (Room room : roomT) {
                    RoomDTO roomDTO = new RoomDTO(room.getId(), room.getImages(), room.getName(), room.getPrice(),
                            room.getPriceType(), room.getCurrency().getSymbol());

                    array.put(new JSONObject().put("id", roomDTO.getId()).put("images", roomDTO.getImages())
                            .put("name", roomDTO.getName()).put("price", roomDTO.getPrice())
                            .put("priceType", roomDTO.getPriceType())
                            .put("currencySymbol", roomDTO.getCurrencySymbol()));

                }
            } catch (JSONException e) {

            }
            return new JSONObject().put("root", array).toString();
        } else {
            return new JSONObject().put("root", new JSONArray()).toString();
        }

    }

    @GetMapping("/homes/{roomId}")
    public String getRoomImages(@PathVariable("roomId") int id) {
        Room room = roomService.getRoomById(id);
        Set<Image> roomImages = room.getImages();

        return new JSONObject().put("images", roomImages).toString();
    }

    @GetMapping("/calendar/{selectedMonth}/{selectedYear}")
    public String getCalendayByYearAndMonth(@PathVariable("selectedYear") int selectedYear,
            @PathVariable("selectedMonth") int selectedMonth) {
        List<String> daysInMonth = CalendarClass.getDaysInMonth(selectedMonth - 1, selectedYear);
        String strDaysInMonth = daysInMonth.stream().map(Object::toString).collect(Collectors.joining(" "));
        GregorianCalendar gCal = new GregorianCalendar(selectedYear, selectedMonth - 1, 1);
        int startInWeek = gCal.get(Calendar.DAY_OF_WEEK); // ngày thứ mấy trong tuần đó

        return new JSONObject().put("daysInMonth", strDaysInMonth).put("startInWeek", startInWeek).toString();
    }
}
