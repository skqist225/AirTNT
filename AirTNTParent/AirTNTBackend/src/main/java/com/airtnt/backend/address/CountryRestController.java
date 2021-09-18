package com.airtnt.backend.address;

import java.util.List;

import com.airtnt.common.entity.Country;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class CountryRestController {
    
    @Autowired CountryRepository repo;

    @GetMapping("/countries/list")
    public List<Country> listAll(){
        return repo.findALLByOrderByNameAsc();
    }
    
}
