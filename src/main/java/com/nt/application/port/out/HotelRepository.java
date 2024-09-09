package com.nt.application.port.out;

import com.nt.domain.entity.hotel.Hotel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

public interface HotelRepository extends JpaRepository<Hotel, Long> {

    @Query(value = "SELECT h.* FROM hotel h " +
            "LEFT JOIN booking b ON h.id = b.hotel_id " +
            "LEFT JOIN hotel_commodities c ON h.id = c.hotel_id " +
            "WHERE (:destination IS NULL OR h.destination LIKE CONCAT('%', :destination, '%')) AND " +
            "(:checkIn IS NULL OR b.check_in >= :checkIn) AND " +
            "(:checkOut IS NULL OR b.check_out <= :checkOut) AND " +
            "(:availableRooms IS NULL OR h.available_rooms >= :availableRooms) AND " +
            "(:guestsCount IS NULL OR h.guests_count >= :guestsCount)", nativeQuery = true)
    List<Hotel> findHotels(@Param("destination") String destination,
                           @Param("checkIn") LocalDateTime checkIn,
                           @Param("checkOut") LocalDateTime checkOut,
                           @Param("availableRooms") Integer availableRooms,
                           @Param("guestsCount") Integer guestsCount);
}