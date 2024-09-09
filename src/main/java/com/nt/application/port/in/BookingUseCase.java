package com.nt.application.port.in;

import com.nt.adapter.in.web.dto.BookingRequestDTO;
import com.nt.domain.entity.booking.Booking;

import java.util.List;

public interface BookingUseCase {

    Booking createBooking(BookingRequestDTO bookingRequestDTO);
    List<Booking> getBookingsByHotel(Long hotelId);
    int getBookingsCount();
    void bookingCheckIn(BookingRequestDTO bookingRequestDTO);
}
