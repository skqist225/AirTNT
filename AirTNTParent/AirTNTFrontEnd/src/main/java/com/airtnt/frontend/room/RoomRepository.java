package com.airtnt.frontend.room;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

import com.airtnt.common.entity.Category;
import com.airtnt.common.entity.Room;

@Repository
public interface RoomRepository extends JpaRepository<Room, Integer> {

    public List<Room> findByCategory(Category category, Pageable pageable);
}
