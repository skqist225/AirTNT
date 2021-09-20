package com.airtnt.frontend.room;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.airtnt.common.entity.Room;
import com.airtnt.frontend.category.CategoryService;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RoomRestController {

    @Autowired
    private CategoryService categoryService;

    @PostMapping("/homes")
    public String checkDuplicateEmail(@RequestBody Map<String, Object> payLoad) {
        int id = Integer.parseInt(payLoad.get("catId").toString());

        Set<Room> rooms = categoryService.getRoomsByCategoryId(id);
        JSONArray array = new JSONArray();

        // Replicate room to test
        List<Room> roomT = new ArrayList<>(rooms);
        for (int i = 0; i <= 10; i++) {
            roomT.add(roomT.get(i));
        }
        // Replicate room to test

        try {
            for (Room room : roomT) {
                RoomDTO roomDTO = new RoomDTO(room.getId(), room.getImages(), room.getName(), room.getPrice(),
                        room.getPriceType(), room.getCurrency().getSymbol());

                array.put(new JSONObject().put("id", roomDTO.getId()).put("images", roomDTO.getImages())
                        .put("name", roomDTO.getName()).put("price", roomDTO.getPrice())
                        .put("priceType", roomDTO.getPriceType()).put("currencySymbol", roomDTO.getCurrencySymbol()));

            }
        } catch (JSONException e) {

        }

        return new JSONObject().put("root", array).toString();
    }
}
