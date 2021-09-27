package com.airtnt.backend.address;

import java.util.List;

import com.airtnt.common.entity.Country;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CountryService {
    @Autowired
    CountryRepository repo;

    public List<Country> listAll(){
        return repo.findALLByOrderByNameAsc();
    }

    public Country getById(Integer id){
        return repo.findById(id).get();
    }

    public Country save(Country country){
        return repo.save(country);
    }

    public void delete(Integer id){
        repo.deleteById(id);
    }

    public String checkName(Integer id, String name) {
        Country country = repo.findByName(name);

        if(country==null) return "OK";

        if(id!=null && country.getId()==id) return "OK";

        return "Duplicated";
    }
}
