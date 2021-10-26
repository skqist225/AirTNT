package com.airtnt.frontend.country;

import com.airtnt.common.entity.Country;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CountryRepository extends CrudRepository<Country, Integer> {

}
