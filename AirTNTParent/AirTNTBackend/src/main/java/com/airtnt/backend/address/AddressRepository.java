package com.airtnt.backend.address;

import com.airtnt.common.entity.Address;

import org.springframework.data.repository.CrudRepository;

public interface AddressRepository extends CrudRepository<Address, Integer> {
    
}
