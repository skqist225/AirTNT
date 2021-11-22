package com.airtnt.frontend.room;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

import com.airtnt.common.entity.Category;
import com.airtnt.common.entity.Room;
import com.airtnt.common.entity.User;

@Repository
public interface RoomRepository extends JpaRepository<Room, Integer> {
    public List<Room> findByCategory(Category category, Pageable pageable);

    public List<Room> findByHost(User host, Pageable pageable);

    @Modifying
    @Query("Update Room r set r.status = true where r.id = ?1")
    public int updateRoomStatus(Integer roomId);
}
