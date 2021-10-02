package com.airtnt.backend.room;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RoomRestController {
    @Autowired RoomService service;

    @PostMapping("/rooms/checkName")
    public String checkName(@Param("id") Integer id, @Param("name") String name){
        return service.isNameUnique(id, name) ? "OK" : "Duplicated";
    }
}
