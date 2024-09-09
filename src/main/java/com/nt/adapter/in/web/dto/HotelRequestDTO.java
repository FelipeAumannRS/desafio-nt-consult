package com.nt.adapter.in.web.dto;

import com.nt.domain.entity.hotel.HotelCommodities;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class HotelRequestDTO {

    private String name;
    private String destination;
    private Integer availableRooms;
    private Integer guestsCount;
    private BigDecimal price;
    private Integer rating;
    private List<HotelCommodities> commodities;
}
