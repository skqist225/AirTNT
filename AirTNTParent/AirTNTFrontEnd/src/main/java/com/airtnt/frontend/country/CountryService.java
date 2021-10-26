package com.airtnt.frontend.country;

import java.util.ArrayList;
import java.util.List;

import com.airtnt.common.entity.Country;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CountryService {
    @Autowired
    CountryRepository countryRepository;

    public List<Country> getCountries() {
        List<Country> countries = new ArrayList<>();
        Iterable<Country> itr = countryRepository.findAll();
        itr.forEach(countries::add);
        return countries;
    }

    public Country getCountryById(Integer id) {
        return countryRepository.findById(id).get();
    }
}
