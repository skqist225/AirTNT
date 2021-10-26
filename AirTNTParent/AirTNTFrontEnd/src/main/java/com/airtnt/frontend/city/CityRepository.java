package com.airtnt.frontend.city;

import com.airtnt.common.entity.City;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CityRepository extends CrudRepository<City, Integer> {
    public City findByName(String cityName);
}
