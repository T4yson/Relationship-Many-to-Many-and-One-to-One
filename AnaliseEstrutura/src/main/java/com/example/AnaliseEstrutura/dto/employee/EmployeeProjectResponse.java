package com.example.AnaliseEstrutura.dto.employee;

import java.util.List;

public record EmployeeProjectResponse (
        EmployeeResponse employee,
        List<ProjectResponse> project
){
}
