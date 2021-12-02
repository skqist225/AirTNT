package com.airtnt.frontend.booking;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

import com.airtnt.common.entity.Booking;
import com.airtnt.common.entity.Room;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Integer> {

    public Booking findByCheckinDateAndCheckoutDate(Date checkinDate, Date checkoutDate);

    public List<Booking> findByRoom(Room room);

    @Query("SELECT b FROM Booking b WHERE b.customer.id = :customerId AND b.room.name LIKE %:query% OR CONCAT(b.customer.firstName, ' ', b.customer.lastName) LIKE %:query% ORDER BY b.bookingDate DESC")
    public List<Booking> getByCustomer(Integer customerId, String query);

    @Query("SELECT b FROM Booking b WHERE b.room.id IN (:roomIds) AND b.bookingDate >= :startDate AND b.bookingDate <= :endDate")
    public List<Booking> getBookingsByRooms(Integer[] roomIds, LocalDateTime startDate, LocalDateTime endDate);

    @Query("SELECT b FROM Booking b WHERE b.room.id IN (:roomIds)")
    public List<Booking> getBookingsByRooms(Integer[] roomIds);

    @Query("SELECT b FROM Booking b WHERE b.room.id IN (:roomIds) AND b.room.name LIKE %:query%")
    public Page<Booking> getBookingsByRooms(Integer[] roomIds, String query, Pageable pageable);

}
