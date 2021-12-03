package com.airtnt.frontend.amentity;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.airtnt.common.entity.AmentityCategory;

@Repository
public interface AmentityCategoryRepository extends CrudRepository<AmentityCategory, Integer> {

}
