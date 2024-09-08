package com.nt.domain.entity;

import com.nt.domain.entity.booking.Booking;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table
public class Hotel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String destination;
    private Integer roomsCount;
    private Integer guestsCount;

    private LocalDateTime checkIn;
    private LocalDateTime checkOut;

    private BigDecimal price;

    private Integer rating;

    @ManyToOne
    @JoinColumn(name = "id")
    private Booking booking;
}