package com.airtnt.frontend.room_group;

import com.airtnt.common.entity.RoomGroup;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoomGroupRepository extends CrudRepository<RoomGroup, Integer> {

}
