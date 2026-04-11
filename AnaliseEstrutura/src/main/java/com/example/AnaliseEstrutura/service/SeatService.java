package com.example.AnaliseEstrutura.service;

import com.example.AnaliseEstrutura.dto.seat.SeatRequest;
import com.example.AnaliseEstrutura.dto.seat.SeatResponse;
import com.example.AnaliseEstrutura.mapper.SeatMapper;
import com.example.AnaliseEstrutura.model.Seat;
import com.example.AnaliseEstrutura.repository.SeatRepository; // Lembre-se de criar esta interface!
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SeatService {

    @Autowired
    private SeatRepository seatRepository;

    @Autowired
    private SeatMapper mapper;

    public SeatResponse save(SeatRequest request) {
        Seat seat = mapper.toEntity(request);
        Seat savedSeat = seatRepository.save(seat);
        return mapper.toResponse(savedSeat);
    }

    public SeatResponse update(Long id, SeatRequest request) {
        Seat seat = mapper.toEntity(request);
        seat.setId(id);
        Seat savedSeat = seatRepository.save(seat);
        return mapper.toResponse(savedSeat);
    }

    public List<SeatResponse> findAll() {
        return seatRepository.findAll().stream()
                .map(mapper::toResponse)
                .collect(Collectors.toList());
    }

    public SeatResponse findById(Long id) {
        return seatRepository.findById(id)
                .map(mapper::toResponse)
                .orElseThrow(() -> new RuntimeException("Seat not found"));
    }

    public void delete(Long id) {
        seatRepository.deleteById(id);
    }
}
