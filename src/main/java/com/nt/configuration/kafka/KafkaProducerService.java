package com.nt.configuration.kafka;

import com.nt.adapter.in.web.dto.BookingRequestDTO;
import com.nt.domain.entity.booking.Booking;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class KafkaProducerService {

    private final KafkaTemplate<String, BookingRequestDTO> kafkaTemplate;

    public void sendNotification(BookingRequestDTO booking) {
        kafkaTemplate.send("booking-notification", booking);
    }
}