package com.example.AnaliseEstrutura.repository;

import com.example.AnaliseEstrutura.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    
    @Query("SELECT e FROM Employee e WHERE size(e.projects) > 1")
    List<Employee> findEmployeesWithMultipleProjects();

    void delete(Long id);
}
