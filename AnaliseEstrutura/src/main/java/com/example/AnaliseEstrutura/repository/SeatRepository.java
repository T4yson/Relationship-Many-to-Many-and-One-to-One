package com.example.AnaliseEstrutura.repository;

import com.example.AnaliseEstrutura.model.Seat;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SeatRepository extends JpaRepository<Seat, Long> {
}
