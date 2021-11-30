package com.airtnt.frontend.booking;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import com.airtnt.common.entity.Booking;
import com.airtnt.common.entity.Room;
import com.airtnt.common.entity.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookingService {

    @Autowired
    private BookingRepository bookingRepository;

    public Booking createBooking(String checkin, String checkout, Room room, int numberOfDays, float siteFee,
            User customer) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        Date checkinDate = sdf.parse(checkin);
        Date checkoutDate = sdf.parse(checkout);

        Booking isBooked = bookingRepository.findByCheckinDateAndCheckoutDate(checkinDate, checkoutDate);
        if (isBooked != null) {
            return null;
        }

        Booking booking = Booking.builder().checkinDate(checkinDate).checkoutDate(checkoutDate)
                .pricePerDay(room.getPrice()).numberOfDays(numberOfDays).siteFee(siteFee).room(room).customer(customer)
                .bookingDate(LocalDateTime.now()).totalFee(numberOfDays * room.getPrice()).isComplete(true).build();

        Booking savedBooking = bookingRepository.save(booking);

        return savedBooking;
    }

    public List<BookedDate> getBookedDate(Room room) throws ParseException {
        List<BookedDate> bookedDates = new ArrayList<>();
        List<Booking> bookingsList = new ArrayList<>();
        Iterator<Booking> bookings = bookingRepository.findByRoom(room).iterator();
        bookings.forEachRemaining(bookingsList::add);

        for (int i = 0; i < bookingsList.size(); i++) {
            Date checkinDate = bookingsList.get(i).getCheckinDate();
            Date checkoutDate = bookingsList.get(i).getCheckoutDate();

            if (checkinDate != null & checkoutDate != null) {
                String[] checkinDate2 = checkinDate.toString().split("T")[0].split(" ")[0].split("-");
                String[] checkoutDate2 = checkoutDate.toString().split("T")[0].split(" ")[0].split("-");

                bookedDates
                        .add(new BookedDate(checkinDate2[2] + "/" + checkinDate2[1] + "/" +
                                checkinDate2[0],
                                checkoutDate2[2] + "/" + checkoutDate2[1] + "/" + checkoutDate2[0]));
            }
        }
        return bookedDates;
    }

    public List<Booking> getBookingsByRooms(List<Integer> rooms, LocalDateTime startDate, LocalDateTime endDate) {
        List<Booking> bookings = bookingRepository.getBookingsByRooms(rooms, startDate, endDate);
        return bookings;
    }

}
