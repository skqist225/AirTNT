package com.airtnt.frontend.city;

import java.util.List;

import com.airtnt.common.entity.City;
import com.airtnt.common.entity.State;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CityRepository extends CrudRepository<City, Integer> {
    public City findByName(String cityName);

    public List<City> findAllByOrderByNameAsc();

    public List<City> findByStateOrderByNameAsc(State state);
}
