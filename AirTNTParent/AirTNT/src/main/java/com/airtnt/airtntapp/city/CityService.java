package com.airtnt.airtntapp.city;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import com.airtnt.common.entity.City;
import com.airtnt.common.entity.State;

@Service
public class CityService {
    @Autowired
    CityRepository cityRepository;

    public City getCityByName(String cityName) {
        City city = cityRepository.findByName(cityName);

        return city;
    }

    public City addCity(String cityName, State state) {
        City c = new City();
        c.setName(cityName);
        c.setState(state);
        City savedState = cityRepository.save(c);

        return savedState;
    }

    public List<City> listAll() {
        return (List<City>) cityRepository.findAll();
    }
}
