package com.airtnt.backend.address;

import java.util.List;

import com.airtnt.common.entity.Country;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CountryRepository extends CrudRepository<Country, Integer> {
    public List<Country> findALLByOrderByNameAsc();

    public Country findByName(String name);
}
