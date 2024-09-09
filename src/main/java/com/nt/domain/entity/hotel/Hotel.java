package com.nt.domain.entity.hotel;

import com.nt.domain.entity.booking.Booking;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@Entity
@Table
@NoArgsConstructor
public class Hotel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String destination;
    private Integer availableRooms;
    private Integer guestsCount;

    private BigDecimal price;
    private Integer rating;

    @ElementCollection(targetClass = HotelCommodities.class)
    @CollectionTable(name = "hotel_commodities", joinColumns = @JoinColumn(name = "hotel_id"))
    @Enumerated(EnumType.STRING)
    private List<HotelCommodities> commodities;

    @OneToMany(mappedBy = "hotel")
    private Set<Booking> bookings;

    public Hotel(String name,
                 String destination,
                 Integer availableRooms,
                 Integer guestsCount,
                 List<HotelCommodities> commodities,
                 BigDecimal price,
                 Integer rating) {
        this.name = name;
        this.destination = destination;
        this.availableRooms = availableRooms;
        this.guestsCount = guestsCount;
        this.commodities = commodities;
        this.price = price;
        this.rating = rating;
    }
}