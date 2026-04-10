package com.example.AnaliseEstrutura.repository;

import com.example.AnaliseEstrutura.model.Project;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectRepository extends JpaRepository<Project, Long> {
}
