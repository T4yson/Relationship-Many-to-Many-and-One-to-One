package com.example.AnaliseEstrutura.dto.employee;

import com.example.AnaliseEstrutura.dto.seat.SeatResponse;

public record EmployeeWithSeatResponse (
        EmployeeResponse employee,
        SeatResponse seat
){
}
