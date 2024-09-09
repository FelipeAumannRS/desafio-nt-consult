package com.nt.adapter.in.web.controller;

import com.nt.adapter.in.web.dto.BookingRequestDTO;
import com.nt.application.port.in.BookingUseCase;
import com.nt.domain.entity.booking.Booking;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/bookings")
public class BookingController {

    private final BookingUseCase bookingService;

    @PostMapping("/create")
    public ResponseEntity<Booking> create(@RequestBody BookingRequestDTO bookingRequestDTO) {
        Booking booking = bookingService.createBooking(bookingRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(booking);
    }

    @PostMapping("/reserve")
    public ResponseEntity<String> makeCheckIn(@RequestBody BookingRequestDTO bookingRequestDTO) {
        bookingService.bookingCheckIn(bookingRequestDTO);
        return ResponseEntity.ok("Making a booking...");
    }

    @GetMapping("/list")
    public ResponseEntity<List<Booking>> listBookingsByHotel(@RequestParam Long hotelId) {
        List<Booking> bookings = bookingService.getBookingsByHotel(hotelId);
        return ResponseEntity.ok(bookings);
    }

    @GetMapping
    public ResponseEntity<Integer> getHotelsCount() {
        return ResponseEntity.ok(bookingService.getBookingsCount());
    }
}
