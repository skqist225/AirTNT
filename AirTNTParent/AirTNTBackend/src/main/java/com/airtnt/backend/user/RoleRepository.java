package com.airtnt.backend.user;

import com.airtnt.common.entity.Role;

import org.springframework.data.repository.CrudRepository;

public interface RoleRepository extends CrudRepository<Role, Integer> {
    
}
