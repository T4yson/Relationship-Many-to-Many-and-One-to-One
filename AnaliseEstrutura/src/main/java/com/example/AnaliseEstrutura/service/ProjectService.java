package com.example.AnaliseEstrutura.service;

import com.example.AnaliseEstrutura.dto.project.ProjectRequest;
import com.example.AnaliseEstrutura.dto.project.ProjectResponse;
import com.example.AnaliseEstrutura.mapper.ProjectMapper;
import com.example.AnaliseEstrutura.model.Project;
import com.example.AnaliseEstrutura.repository.ProjectRepository; // Lembre-se de criar esta interface!
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProjectService {

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private ProjectMapper mapper;

    public ProjectResponse save(ProjectRequest request) {
        Project project = mapper.toEntity(request);
        Project savedProject = projectRepository.save(project);
        return mapper.toResponse(savedProject);
    }

    public ProjectResponse update(Long id, ProjectRequest request) {
        Project project = mapper.toEntity(request);
        project.setId(id);
        Project savedProject = projectRepository.save(project);
        return mapper.toResponse(savedProject);
    }

    public List<ProjectResponse> findAll() {
        return projectRepository.findAll().stream()
                .map(mapper::toResponse)
                .collect(Collectors.toList());
    }

    public ProjectResponse findById(Long id) {
        return projectRepository.findById(id)
                .map(mapper::toResponse)
                .orElseThrow(() -> new RuntimeException("Project not found"));
    }

    public void delete(Long id) {
        projectRepository.deleteById(id);
    }
}