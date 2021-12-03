package com.airtnt.frontend.privacy;

import com.airtnt.common.entity.RoomPrivacy;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PrivacyTypeRepository extends CrudRepository<RoomPrivacy, Integer> {

}
