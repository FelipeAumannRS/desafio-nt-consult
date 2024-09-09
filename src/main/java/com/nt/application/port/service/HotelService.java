package com.nt.application.port.service;

import com.nt.adapter.in.web.dto.HotelRequestDTO;
import com.nt.application.port.in.HotelUseCase;
import com.nt.application.port.out.HotelRepository;
import com.nt.domain.entity.hotel.Hotel;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class HotelService implements HotelUseCase {

    private final HotelRepository hotelRepository;

    @Override
    public List<Hotel> searchHotels(String location, LocalDateTime checkIn, LocalDateTime checkOut, Integer availableRooms, Integer guestsCount) {
        log.info("Searching Hotels...");
        if (checkIn == null && checkOut == null && location == null && availableRooms == null && guestsCount == null)
            return hotelRepository.findAll();

        return hotelRepository.findHotels(
                location,
                checkIn,
                checkOut,
                availableRooms,
                guestsCount
        );
    }

    @Override
    public void deleteHotel(Long id) {
        log.info("Deleting Hotel with id {}", id);
        hotelRepository.deleteById(id);
    }

    @Override
    public Hotel createHotel(HotelRequestDTO hotelRequestDTO) {
        log.info("Creating new Hotel...");
        return hotelRepository.save(new Hotel(
                hotelRequestDTO.getName(),
                hotelRequestDTO.getDestination(),
                hotelRequestDTO.getAvailableRooms(),
                hotelRequestDTO.getGuestsCount(),
                hotelRequestDTO.getCommodities(),
                hotelRequestDTO.getPrice(),
                hotelRequestDTO.getRating()
        ));
    }

    @Override
    public List<Hotel> compareHotels(String sortBy) {
        log.info("Comparing Hotels by {}", sortBy);

        List<Hotel> hotels = hotelRepository.findAll();
        switch (sortBy.toLowerCase()) {
            case "price":
                hotels.sort(Comparator.comparing(Hotel::getPrice));
                break;
            case "destination":
                hotels.sort(Comparator.comparing(Hotel::getDestination));
                break;
            case "rating":
                hotels.sort(Comparator.comparing(Hotel::getRating).reversed());
                break;
            case "commodities":
                hotels.sort(Comparator.comparing(h -> h.getCommodities().size()));
                break;
            default:
                throw new IllegalArgumentException("Invalid sorting field: " + sortBy);
        }
        return hotels;
    }

    @Override
    public int getHotelsCount() {
        return hotelRepository.findAll().size();
    }
}