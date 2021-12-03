package com.airtnt.frontend.address;

import java.util.List;

import com.airtnt.common.entity.City;
import com.airtnt.common.entity.State;
import com.airtnt.frontend.city.CityRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CityRestControler {

    @Autowired
    CityRepository repo;

    @GetMapping("/cities/list_by_state/{stateId}")
    public List<City> listAll(@PathVariable("stateId") Integer stateId) {
        State state = new State(stateId);
        return repo.findByStateOrderByNameAsc(state);
    };

    @PostMapping("/cities/save")
    public City save(@RequestBody City city) {
        return repo.save(city);
    }

    @DeleteMapping("/cities/delete/{id}")
    public void delete(@PathVariable Integer id) {
        repo.deleteById(id);
    }

    @PostMapping("/cities/check_name")
    public String checkName(@Param("id") Integer id,
            @Param("name") String name) {
        City city = repo.findByName(name);

        if (city == null)
            return "OK";

        if (id != null && city.getId() == id)
            return "OK";

        return "Duplicated";
    }
}
