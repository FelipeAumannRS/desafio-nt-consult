package com.nt.configuration.kafka;

import com.nt.adapter.in.web.dto.BookingRequestDTO;
import com.nt.application.port.out.BookingRepository;
import com.nt.application.port.out.HotelRepository;
import com.nt.domain.entity.booking.Booking;
import com.nt.domain.entity.booking.BookingStatus;
import com.nt.domain.entity.hotel.Hotel;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.KafkaListener;

@Slf4j
@Configuration
@RequiredArgsConstructor
public class KafkaConsumerConfiguration {

    private final HotelRepository hotelRepository;
    private final BookingRepository bookingRepository;

    @KafkaListener(topics = "booking-notification", groupId = "nt-group")
    public void receiveNotification(BookingRequestDTO bookingRequestDTO) {
        Booking booking = bookingRepository.findById(bookingRequestDTO.getHotelId())
                .orElseThrow(() -> new IllegalArgumentException("Booking not found"));

        Hotel hotel = booking.getHotel();

        booking.setGuestName(bookingRequestDTO.getGuestName());
        booking.setGuestContat(bookingRequestDTO.getGuestContat());
        booking.setPaymentDetail(bookingRequestDTO.getPaymentDetail());
        booking.setStatus(BookingStatus.CONFIRMED);

        hotel.setGuestsCount(hotel.getGuestsCount() + bookingRequestDTO.getGuestsCount());
        hotel.setAvailableRooms(hotel.getAvailableRooms() - 1);

        hotelRepository.save(hotel);

        log.info("Booking status: {}", booking.getStatus());
    }
}