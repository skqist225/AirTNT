package com.airtnt.frontend.room;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

import com.airtnt.common.entity.Category;
import com.airtnt.common.entity.Room;
import com.airtnt.common.entity.User;

@Repository
public interface RoomRepository extends JpaRepository<Room, Integer>, JpaSpecificationExecutor<Room> {
        public List<Room> findByCategory(Category category, Pageable pageable);

        @Query("SELECT r FROM Room r JOIN r.amentities ra WHERE r.host = :host" + " AND r.name LIKE %:query%"
                        + " AND r.bedroomCount >= :bedroomCount AND r.bathroomCount >= :bathroomCount AND r.bedCount >= :bedCount"
                        + " AND ra.id IN (:amentitiesID)" + " AND r.status IN (:statusesID)")
        public Page<Room> getRoomsByHost(User host, String query, int bedroomCount, int bathroomCount, int bedCount,
                        @Param("amentitiesID") List<Integer> amentitiesID,
                        @Param("statusesID") List<Boolean> statusesID, Pageable pageable);

        @Modifying
        @Query("Update Room r set r.status = true where r.id = ?1")
        public int updateRoomStatus(Integer roomId);

        @Query("SELECT r FROM Room r WHERE r.name LIKE %?1%")
        public Page<Room> findAll(String keyword, Pageable pageable);
}
