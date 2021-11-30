package com.airtnt.frontend.booking;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

import com.airtnt.common.entity.Booking;
import com.airtnt.common.entity.Room;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookingRepository extends CrudRepository<Booking, Integer> {

    public Booking findByCheckinDateAndCheckoutDate(Date checkinDate, Date checkoutDate);

    public List<Booking> findByRoom(Room room);

    @Query("SELECT b FROM Booking b WHERE b.room.id IN (:roomIds) AND b.bookingDate >= :startDate AND b.bookingDate <= :endDate")
    public List<Booking> getBookingsByRooms(List<Integer> roomIds, LocalDateTime startDate, LocalDateTime endDate);

}
