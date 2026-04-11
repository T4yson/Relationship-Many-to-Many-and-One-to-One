package com.example.AnaliseEstrutura.mapper;

import com.example.AnaliseEstrutura.dto.project.ProjectRequest;
import com.example.AnaliseEstrutura.dto.project.ProjectResponse;
import com.example.AnaliseEstrutura.model.Project;
import org.springframework.stereotype.Component;

@Component
public class ProjectMapper {

    public Project toEntity(ProjectRequest request) {
        if (request == null) return null;
        Project project = new Project();
        project.setName(request.name());
        return project;
    }

    public ProjectResponse toResponse(Project project) {
        if (project == null) return null;
        return new ProjectResponse(project.getId(), project.getName());
    }
}