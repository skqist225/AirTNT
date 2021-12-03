package com.airtnt.frontend.address;

import java.util.List;

import com.airtnt.common.entity.City;
import com.airtnt.common.entity.State;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CityRepository extends CrudRepository<City, Integer> {
    public List<City> findAllByOrderByNameAsc();

    public List<City> findByStateOrderByNameAsc(State state);

    public City findByName(String name);
}
