package com.example.AnaliseEstrutura.repository;

import com.example.AnaliseEstrutura.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeRepository extends JpaRepository<Employee, Long> {
}
