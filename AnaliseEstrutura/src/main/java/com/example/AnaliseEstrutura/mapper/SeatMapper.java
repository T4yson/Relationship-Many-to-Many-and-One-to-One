package com.example.AnaliseEstrutura.mapper;

import com.example.AnaliseEstrutura.dto.seat.SeatRequest;
import com.example.AnaliseEstrutura.dto.seat.SeatResponse;
import com.example.AnaliseEstrutura.model.Seat;
import org.springframework.stereotype.Component;

@Component
public class SeatMapper {

    public Seat toEntity(SeatRequest request) {
        if (request == null) return null;
        Seat seat = new Seat();
        seat.setCod(request.cod());
        return seat;
    }

    public SeatResponse toResponse(Seat seat) {
        if (seat == null) return null;
        return new SeatResponse(seat.getId(), seat.getCod());
    }
}
