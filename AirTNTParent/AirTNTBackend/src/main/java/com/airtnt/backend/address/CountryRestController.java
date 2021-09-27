package com.airtnt.backend.address;
import java.util.List;

import com.airtnt.common.entity.Country;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class CountryRestController {
    
    @Autowired CountryService service;

    @GetMapping("/countries/list")
    public List<Country> listAll(){
        return service.listAll();
    }
    
    @GetMapping("/countries/{id}")
    public Country getById(
        @PathVariable("id") Integer id
    ){
        return service.getById(id);
    }

    @PostMapping("/countries/save")
    public String save(
        @RequestParam(name="id", required=false) Integer id,
        @RequestParam("name") String name,
        @RequestParam("code") String code
    ) {
        Country country;
        if(id!=null) country = new Country(id, name, code);
        else country = new Country(name, code);
        Country saved = service.save(country);
        return String.valueOf(saved.getId());
    }

    @DeleteMapping("/countries/delete/{id}")
    public void delete(@PathVariable("id") Integer id){
        service.delete(id);
    }

    @PostMapping("/countries/check_name")
    public String checkName(@Param("id") Integer id,
        @Param("name") String name
    ){
        return service.checkName(id, name);
    }
}
