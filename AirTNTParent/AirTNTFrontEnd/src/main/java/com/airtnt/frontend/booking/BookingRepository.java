package com.airtnt.frontend.booking;

import java.util.Date;
import java.util.List;

import com.airtnt.common.entity.Booking;
import com.airtnt.common.entity.Room;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookingRepository extends CrudRepository<Booking, Integer> {

    public Booking findByCheckinDateAndCheckoutDate(Date checkinDate, Date checkoutDate);

    public List<Booking> findByRoom(Room room);
}
