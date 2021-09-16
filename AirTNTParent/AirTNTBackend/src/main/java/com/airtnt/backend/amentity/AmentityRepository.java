package com.airtnt.backend.amentity;

import com.airtnt.common.entity.Amentity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AmentityRepository extends CrudRepository<Amentity, Integer> {

}
