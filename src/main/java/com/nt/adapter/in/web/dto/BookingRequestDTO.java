package com.nt.adapter.in.web.dto;

import com.nt.domain.entity.booking.BookingStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BookingRequestDTO {

    private Long hotelId;
    private LocalDateTime checkIn;
    private LocalDateTime checkOut;
    private BookingStatus status;
    private String guestName;
    private String guestContat;
    private String paymentDetail;
    private int guestsCount;
}
