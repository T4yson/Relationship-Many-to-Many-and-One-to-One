package com.example.AnaliseEstrutura.service;

import com.example.AnaliseEstrutura.dto.employee.EmployeeProjectResponse;
import com.example.AnaliseEstrutura.dto.employee.EmployeeRequest;
import com.example.AnaliseEstrutura.dto.employee.EmployeeResponse;
import com.example.AnaliseEstrutura.dto.employee.EmployeeWithSeatResponse;
import com.example.AnaliseEstrutura.mapper.EmployeeMapper;
import com.example.AnaliseEstrutura.model.Employee;
import com.example.AnaliseEstrutura.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private EmployeeMapper employeeMapper;

    public EmployeeResponse save(EmployeeRequest request) {
        Employee entity = employeeMapper.toEntity(request);
        Employee saved = employeeRepository.save(entity);
        return employeeMapper.toResponse(saved);
    }

    public List<EmployeeResponse> findAll() {
        return employeeRepository.findAll().stream()
                .map(employeeMapper::toResponse)
                .collect(Collectors.toList());
    }

    public EmployeeWithSeatResponse findByIdWithSeat(Long id) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Employee not found"));
        return employeeMapper.toEmployeeWithSeatResponse(employee);
    }

    public List<EmployeeProjectResponse> findEmployeesWithMultipleProjects() {
        return  employeeRepository.findEmployeesWithMultipleProjects().stream()
                .map(employeeMapper:: toEmployeeProjectResponse)
                .collect(Collectors.toList());
    }

    public void delete(Long id) {
        employeeRepository.delete(id);
    }
}
