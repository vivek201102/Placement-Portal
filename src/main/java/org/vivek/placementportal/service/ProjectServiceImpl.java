package org.vivek.placementportal.service;

import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.vivek.placementportal.dto.ProjectRegisterRequest;
import org.vivek.placementportal.models.Project;
import org.vivek.placementportal.models.Student;
import org.vivek.placementportal.repository.ProjectRepository;
import org.vivek.placementportal.repository.StudentRepository;

import java.util.List;

@Service
@AllArgsConstructor
public class ProjectServiceImpl implements ProjectService{
    private final ProjectRepository projectRepository;
    private final StudentRepository studentRepository;

    @Override
    public Project register(ProjectRegisterRequest request) {
        Student student = studentRepository.findByUserId(request.getStudentId());
        if(student == null)
            throw new UsernameNotFoundException("Student not found");

        Project project = Project
                .builder()
                .title(request.getTitle())
                .description(request.getDescription())
                .technologies(request.getTechnologies())
                .sourceCodeUrl(request.getSourceCodeUrl())
                .url(request.getUrl())
                .student(student)
                .build();

        return projectRepository.save(project);
    }

    @Override
    public List<Project> getAll(String studentId) {
        Student student = studentRepository.findByUserId(studentId);
        return projectRepository.findAllByStudent(student);
    }

    @Override
    public Project update(ProjectRegisterRequest request, int id) {
        Project existingProject = projectRepository.findById(id).orElseThrow(()-> new UsernameNotFoundException("Project not found"));

        existingProject.setTitle(request.getTitle());
        existingProject.setDescription(request.getDescription());
        existingProject.setTechnologies(request.getTechnologies());
        existingProject.setUrl(request.getUrl());
        existingProject.setSourceCodeUrl(request.getSourceCodeUrl());

        return projectRepository.save(existingProject);
    }

    @Override
    public Project delete(int id) {
        Project project = projectRepository.findById(id).orElseThrow(() -> new UsernameNotFoundException("Project not found"));
        projectRepository.delete(project);
        return project;
    }
}
