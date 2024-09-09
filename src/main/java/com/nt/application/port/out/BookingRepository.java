package com.nt.application.port.out;

import com.nt.domain.entity.booking.Booking;
import com.nt.domain.entity.hotel.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface BookingRepository extends JpaRepository<Booking, Long> {

    List<Booking> findByHotel(Hotel hotel);
}
