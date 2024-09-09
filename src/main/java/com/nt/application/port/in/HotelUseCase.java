package com.nt.application.port.in;

import com.nt.adapter.in.web.dto.HotelRequestDTO;
import com.nt.domain.entity.hotel.Hotel;

import java.time.LocalDateTime;
import java.util.List;

public interface HotelUseCase {

    List<Hotel> searchHotels(String destination, LocalDateTime checkIn, LocalDateTime checkOut, Integer roomsCount, Integer guestsCount);
    void deleteHotel(Long id);
    Hotel createHotel(HotelRequestDTO hotelRequestDTO);
    List<Hotel> compareHotels(String sortBy);
    int getHotelsCount();
}
