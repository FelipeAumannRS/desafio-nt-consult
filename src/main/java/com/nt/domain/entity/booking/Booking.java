package com.nt.domain.entity;

import jakarta.persistence.*;

import java.util.Set;

@Entity
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String guestName;
    private String guestContat;
    private String checkInDate;
    private String checkOutDate;

    @OneToMany(mappedBy = "id")
    private Set<Hotel> hotel;
}