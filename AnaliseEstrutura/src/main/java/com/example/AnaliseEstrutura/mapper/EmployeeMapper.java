package com.example.AnaliseEstrutura.mapper;

import com.example.AnaliseEstrutura.dto.employee.EmployeeProjectResponse;
import com.example.AnaliseEstrutura.dto.employee.EmployeeRequest;
import com.example.AnaliseEstrutura.dto.employee.EmployeeResponse;
import com.example.AnaliseEstrutura.dto.employee.EmployeeWithSeatResponse;
import com.example.AnaliseEstrutura.dto.seat.SeatResponse;
import com.example.AnaliseEstrutura.model.Employee;
import com.example.AnaliseEstrutura.model.Seat;
import org.springframework.stereotype.Component;

@Component
public class EmployeeMapper {

    public Employee toEntity(EmployeeRequest request) {
        if (request == null) return null;

        Employee employee = new Employee();
        employee.setName(request.name());
        return employee;
    }

    public EmployeeResponse toResponse(Employee employee) {
        if (employee == null) return null;

        return new EmployeeResponse(employee.getId(), employee.getName());
    }

    public EmployeeWithSeatResponse toEmployeeWithSeatResponse(Employee employee) {
        if (employee == null) return null;

        EmployeeResponse employeeResponse = toResponse(employee);

        SeatResponse seatResponse = null;
        if (employee.getSeat() != null) {
            seatResponse = new SeatResponse(employee.getSeat().getId(),employee.getSeat().getCod());
        }
        return new EmployeeWithSeatResponse(employeeResponse, seatResponse);
    }

    public EmployeeProjectResponse toEmployeeProjectResponse(Employee employee) {
        if (employee == null) return null;

        EmployeeResponse employeeResponse = toResponse(employee);

        var projectResponses = employee.getProjects().stream()
                .map(project -> new com.example.AnaliseEstrutura.dto.project.ProjectResponse(project.getId(), project.getName()))
                .toList();

        return new EmployeeProjectResponse(employeeResponse, projectResponses);
    }
}
