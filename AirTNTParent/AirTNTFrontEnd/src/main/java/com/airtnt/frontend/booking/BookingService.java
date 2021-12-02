package com.airtnt.frontend.booking;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import com.airtnt.common.entity.Booking;
import com.airtnt.common.entity.Room;
import com.airtnt.common.entity.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class BookingService {

    public final int MAX_BOOKING_PER_FETCH_BY_HOST = 10;

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
                .bookingDate(LocalDateTime.now()).totalFee(numberOfDays * room.getPrice()).isComplete(false).build();

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

    public List<Booking> getBookingsByRoom(Room room) {
        List<Booking> bookings = bookingRepository.findByRoom(room);
        return bookings;
    }

    public List<Booking> getBookingsByRooms(Integer[] rooms, LocalDateTime startDate, LocalDateTime endDate) {
        List<Booking> bookings = bookingRepository.getBookingsByRooms(rooms, startDate, endDate);
        return bookings;
    }

    public List<Booking> getBookingsByRooms(Integer[] roomIds) {
        List<Booking> bookings = bookingRepository.getBookingsByRooms(roomIds);
        return bookings;
    }

    public List<Booking> getBookingsByUser(Integer customerId, String query) {
        List<Booking> bookings = bookingRepository.getByCustomer(customerId, query);
        return bookings;
    }

    public Page<Booking> getBookingsByRooms(Integer[] roomIds, int pageNumber, Map<String, String> filters) {

        String sortField = filters.get("sortField");
        String sortDir = filters.get("sortDir");
        String query = filters.get("query");
        Integer bookingId = -1;
        if (query.matches("\\d+")) {
            System.out.println(query);
            bookingId = Integer.parseInt(query);
        }
        System.out.println(bookingId);

        Sort sort = Sort.by(sortField);
        if (sortField.equals("room-name")) {
            sort = Sort.by("room.name");
        }
        if (sortField.equals("customer-fullName")) {
            Sort sortByCustomerFirstName = Sort.by("customer.firstName");
            Sort sortByCustomerLastName = Sort.by("customer.lastName");

            sort = sortByCustomerFirstName.and(sortByCustomerLastName);
        }

        sort = sortDir.equals("asc") ? sort.ascending() : sort.descending();
        Pageable pageable = PageRequest.of(pageNumber - 1, MAX_BOOKING_PER_FETCH_BY_HOST, sort); // pase base 0
        Page<Booking> bookings = bookingRepository.getBookingsByRooms(roomIds, query, pageable);
        return bookings;
    }

    @Transactional
    public Booking cancelBooking(Integer bookingId) {
        LocalDateTime cancelDate = LocalDateTime.now();
        Booking cancelledBooking = bookingRepository.findById(bookingId).get();

        cancelledBooking.setCancelDate(cancelDate);
        cancelledBooking.setRefund(true);
        if (cancelledBooking.isComplete())
            cancelledBooking.setRefundPaid(cancelledBooking.getTotalFee() - cancelledBooking.getSiteFee());
        else
            cancelledBooking.setRefundPaid(cancelledBooking.getTotalFee());

        Booking updatedRecord = bookingRepository.save(cancelledBooking);

        return updatedRecord;
    }
}
