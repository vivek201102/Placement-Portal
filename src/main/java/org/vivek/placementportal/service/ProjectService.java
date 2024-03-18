package org.vivek.placementportal.service;

import org.vivek.placementportal.dto.ProjectRegisterRequest;
import org.vivek.placementportal.models.Project;

import java.util.List;

public interface ProjectService {
    public Project register(ProjectRegisterRequest request);
    public List<Project> getAll(String studentId);
    public Project update(ProjectRegisterRequest request, int id);
    public Project delete(int id);
}
