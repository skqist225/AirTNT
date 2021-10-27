package com.airtnt.frontend.room_type;

import com.airtnt.common.entity.RoomType;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoomTypeRepository extends CrudRepository<RoomType, Integer> {

}
