package com.example.AnaliseEstrutura.dto.employee;

import com.example.AnaliseEstrutura.dto.project.ProjectResponse;

import java.util.List;

public record EmployeeProjectResponse(
        EmployeeResponse employee,
        List<ProjectResponse> project
){
}
