package com.airtnt.backend.address;

import java.util.List;

import com.airtnt.common.entity.City;
import com.airtnt.common.entity.State;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CityRestControler {
    
    @Autowired CityRepository repo;

    @GetMapping("/cities/list_by_state/{stateId}")
    public List<City> listAll(
            @PathVariable("stateId") Integer stateId
        ){
            State state = new State(stateId);
            return repo.findByStateOrderByNameAsc(state);
        }
}
