package com.airtnt.backend.room;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.airtnt.common.entity.Room;

@Repository
public interface RoomRepository extends JpaRepository<Room, Integer> {

}
