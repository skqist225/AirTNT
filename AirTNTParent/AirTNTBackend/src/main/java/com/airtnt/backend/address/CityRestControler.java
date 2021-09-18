package com.airtnt.backend.address;

import java.util.Iterator;
import java.util.List;

import com.airtnt.common.entity.City;
import com.airtnt.common.entity.Country;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CityRestControler {
    
    @Autowired CityRepository repo;

    @GetMapping("/cities/list_by_states_and_country/{stateId}/{countryId}")
    public List<City> listAll(
            @PathVariable("stateId") Integer stateId,
            @PathVariable("countryId") Integer countryId
        ){
            Country country = new Country(countryId);
            List<City> listCities = repo.findAllByOrderByNameAsc(country);

            Iterator<City> it = listCities.iterator();

            while(it.hasNext()){
                City c = it.next();
                if(c.getState().getId() != stateId) it.remove();
            }
            return listCities;
    }
}
