package com.airtnt.backend.address;

import java.util.List;

import com.airtnt.common.entity.Country;
import com.airtnt.common.entity.State;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StateRestController {
    
    @Autowired StateRepository repo;

    @GetMapping("/states/list_state_by_country/{id}")
    public List<State> listAll(@PathVariable("id") Integer id){
        Country country = new Country(id);
        return repo.findAllByOrderByNameAsc(country);
    }
}