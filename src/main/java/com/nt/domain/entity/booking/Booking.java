package com.nt.domain.entity.booking;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.nt.domain.entity.hotel.Hotel;
import com.nt.domain.entity.hotel.HotelCommodities;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@Entity
@Table
@NoArgsConstructor
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String guestName;
    private String guestContat;
    private LocalDateTime checkIn;
    private LocalDateTime checkOut;

    private String paymentDetail;

    @ManyToOne
    @JoinColumn(name = "hotel_id")
    @JsonIgnore
    private Hotel hotel;

    @Enumerated(EnumType.STRING)
    private BookingStatus status;

    public Booking(LocalDateTime checkIn,
                   LocalDateTime checkOut,
                   Hotel hotel,
                   BookingStatus status) {
        this.checkIn = checkIn;
        this.checkOut = checkOut;
        this.hotel = hotel;
        this.status = status;
    }
}