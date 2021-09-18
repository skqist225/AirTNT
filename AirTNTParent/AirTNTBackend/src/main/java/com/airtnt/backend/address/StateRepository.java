package com.airtnt.backend.address;

import java.util.List;

import com.airtnt.common.entity.Country;
import com.airtnt.common.entity.State;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StateRepository extends CrudRepository<State, Integer> {
    public List<State> findAllByOrderByNameAsc(Country country);
}
