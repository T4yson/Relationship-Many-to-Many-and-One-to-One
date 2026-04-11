package com.example.AnaliseEstrutura.controller;

import com.example.AnaliseEstrutura.dto.seat.SeatRequest;
import com.example.AnaliseEstrutura.dto.seat.SeatResponse;
import com.example.AnaliseEstrutura.service.SeatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/seats")
public class SeatController {

    @Autowired
    private SeatService seatService;

    @PostMapping
    public ResponseEntity<SeatResponse> create(@RequestBody SeatRequest request) {
        return new ResponseEntity<>(seatService.save(request), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<SeatResponse>> findAll() {
        return ResponseEntity.ok(seatService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<SeatResponse> findById(@PathVariable Long id) {
        return ResponseEntity.ok(seatService.findById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<SeatResponse> update(@PathVariable Long id, @RequestBody SeatRequest request) {
        return ResponseEntity.ok(seatService.update(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        seatService.delete(id);
        return ResponseEntity.noContent().build();
    }
}