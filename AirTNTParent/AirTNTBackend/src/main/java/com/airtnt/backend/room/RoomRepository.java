package com.airtnt.backend.room;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.airtnt.common.entity.Room;

@Repository
public interface RoomRepository extends JpaRepository<Room, Integer> {
    public Room findByName(String name);

    public Long countById(Integer id);

    @Query("SELECT u FROM Room u WHERE CONCAT(u.id, '', u.name, ' ', u.description, ' ', u.category) LIKE %?1%")
    public Page<Room> findAll(String keyword, Pageable pageable);

    @Query("UPDATE Room u SET u.status = ?2 WHERE u.id = ?1")
    @Modifying
    public void updateStatus(Integer id, boolean status);
}
