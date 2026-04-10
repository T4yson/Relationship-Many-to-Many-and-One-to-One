package com.example.AnaliseEstrutura.controller;

import com.example.AnaliseEstrutura.dto.employee.EmployeeProjectResponse;
import com.example.AnaliseEstrutura.dto.employee.EmployeeRequest;
import com.example.AnaliseEstrutura.dto.employee.EmployeeResponse;
import com.example.AnaliseEstrutura.dto.employee.EmployeeWithSeatResponse;
import com.example.AnaliseEstrutura.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @PostMapping
    public ResponseEntity<EmployeeResponse> create(@RequestBody EmployeeRequest request) {
        return new ResponseEntity<>(employeeService.save(request), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<EmployeeResponse>> findAll() {
        return ResponseEntity.ok(employeeService.findAll());
    }

    @GetMapping("/{id}/seat")
    public ResponseEntity<EmployeeWithSeatResponse> findByIdWithSeat(@PathVariable Long id) {
        return ResponseEntity.ok(employeeService.findByIdWithSeat(id));
    }

    @GetMapping("/multi-projects")
    public ResponseEntity<List<EmployeeProjectResponse>> findWithMultipleProjects() {
        return ResponseEntity.ok(employeeService.findEmployeesWithMultipleProjects());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        employeeService.delete(id);
        return ResponseEntity.noContent().build();
    }
}