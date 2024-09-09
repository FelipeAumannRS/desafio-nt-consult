package com.nt.application.port.service;

import com.nt.adapter.in.web.dto.BookingRequestDTO;
import com.nt.application.port.in.BookingUseCase;
import com.nt.application.port.out.BookingRepository;
import com.nt.application.port.out.HotelRepository;
import com.nt.configuration.kafka.KafkaProducerService;
import com.nt.domain.entity.booking.Booking;
import com.nt.domain.entity.booking.BookingStatus;
import com.nt.domain.entity.hotel.Hotel;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class BookingService implements BookingUseCase {

    private final HotelRepository hotelRepository;
    private final BookingRepository bookingRepository;
    private final KafkaProducerService kafkaProducerService;

    @Override
    public Booking createBooking(BookingRequestDTO bookingRequestDTO) {
        Hotel hotel = hotelRepository.findById(bookingRequestDTO.getHotelId())
                .orElseThrow(() -> new IllegalArgumentException("Invalid hotel ID"));
        Booking booking = new Booking(
                bookingRequestDTO.getCheckIn(),
                bookingRequestDTO.getCheckOut(),
                hotel,
                bookingRequestDTO.getStatus()
        );
        return bookingRepository.save(booking);
    }

    @Override
    public void bookingCheckIn(BookingRequestDTO bookingRequestDTO) {
        log.info("Reserving Hotel room {}", bookingRequestDTO.getHotelId());

        Booking booking = bookingRepository.findById(bookingRequestDTO.getHotelId())
                .orElseThrow(() -> new IllegalArgumentException("Booking not found"));
        kafkaProducerService.sendNotification(bookingRequestDTO);

        booking.setStatus(BookingStatus.PENDING_PAYMENT);
        bookingRepository.save(booking);
        log.info("Booking status: {}", booking.getStatus());
    }

    @Override
    public List<Booking> getBookingsByHotel(Long hotelId) {
        Hotel hotel = hotelRepository.findById(hotelId)
                .orElseThrow(() -> new IllegalArgumentException("Hotel not found"));
        return bookingRepository.findByHotel(hotel);
    }

    @Override
    public int getBookingsCount() {
        return bookingRepository.findAll().size();
    }
}