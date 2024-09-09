package com.nt.adapter.in.web.controller;

import com.nt.adapter.in.web.dto.HotelRequestDTO;
import com.nt.application.port.in.HotelUseCase;
import com.nt.domain.entity.hotel.Hotel;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/hotels")
@RequiredArgsConstructor
public class HotelController {

    private final HotelUseCase hotelService;

    @GetMapping("/search")
    public ResponseEntity<List<Hotel>> searchHotels(
            @RequestParam(required = false) String destination,
            @RequestParam(required = false) LocalDateTime checkIn,
            @RequestParam(required = false) LocalDateTime checkOut,
            @RequestParam(required = false) Integer availableRooms,
            @RequestParam(required = false) Integer guestsCount) {

        List<Hotel> hotels = hotelService.searchHotels(destination, checkIn, checkOut, availableRooms, guestsCount);
        return ResponseEntity.ok(hotels);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteHotel(@PathVariable Long id) {
        hotelService.deleteHotel(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/create")
    public ResponseEntity<Hotel> createHotel(@RequestBody HotelRequestDTO hotelRequestDTO) {
        Hotel createdHotel = hotelService.createHotel(hotelRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdHotel);
    }

    @GetMapping("/compare")
    public ResponseEntity<List<Hotel>> compareHotels(@RequestParam(required = false, defaultValue = "price") String sortBy) {
        List<Hotel> sortedHotels = hotelService.compareHotels(sortBy);
        return ResponseEntity.ok(sortedHotels);
    }

    @GetMapping
    public ResponseEntity<Integer> getHotelsCount() {
        return ResponseEntity.ok(hotelService.getHotelsCount());
    }
}
