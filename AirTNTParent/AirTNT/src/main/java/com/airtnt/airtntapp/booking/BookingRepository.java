package com.airtnt.airtntapp.booking;

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

        @Query("SELECT b FROM Booking b WHERE b.customer.id = :customerId AND b.room.name LIKE %:query% AND CONCAT(b.customer.firstName, ' ', b.customer.lastName) LIKE %:query% ORDER BY b.bookingDate DESC")
        public List<Booking> getByCustomer(Integer customerId, String query);

        @Query("SELECT b FROM Booking b WHERE b.room.id IN (:roomIds) AND b.bookingDate >= :startDate AND b.bookingDate <= :endDate")
        public List<Booking> getBookingsByRooms(Integer[] roomIds, LocalDateTime startDate, LocalDateTime endDate);

        @Query("SELECT b FROM Booking b WHERE b.room.id IN (:roomIds)")
        public List<Booking> getBookingsByRooms(Integer[] roomIds);

        @Query("SELECT b FROM Booking b WHERE b.room.id IN (:roomIds) AND b.room.name LIKE %:query% AND b.bookingDate <= :bookingDate AND b.bookingDate >= :bookingDate2 AND b.totalFee >= :totalFee AND b.isComplete IN (:isCompleteLst) AND b.isRefund IN (:isCancelledLst)")
        public Page<Booking> getBookingsByRooms(Integer[] roomIds, String query, List<Boolean> isCompleteLst,
                        List<Boolean> isCancelledLst, LocalDateTime bookingDate, LocalDateTime bookingDate2,
                        Float totalFee,
                        Pageable pageable);

        @Query("SELECT b FROM Booking b WHERE b.room.id IN (:roomIds) AND b.room.name LIKE %:query% AND year(b.bookingDate)=:year AND month(b.bookingDate)=:month AND b.isComplete IN (:isCompleteLst) AND b.isRefund IN (:isCancelledLst)")
        public Page<Booking> getBookingsByRooms(Integer[] roomIds, String query, List<Boolean> isCompleteLst,
                        List<Boolean> isCancelledLst, Integer year, Integer month,
                        Pageable pageable);

        @Query("SELECT b FROM Booking b WHERE b.room.id IN (:roomIds) AND b.room.name LIKE %:query% AND (b.isComplete = :isComplete OR b.isRefund = :isCancelled)")
        public Page<Booking> getBookingsByRooms(Integer[] roomIds, String query, Boolean isComplete,
                        Boolean isCancelled,
                        Pageable pageable);

        @Query("SELECT b FROM Booking b WHERE b.room.id IN (:roomIds) AND b.id = :bookingId")
        public Page<Booking> getBookingsByRooms(Integer[] roomIds, Integer bookingId, Pageable pageable);

}
