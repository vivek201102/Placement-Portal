package org.vivek.placementportal.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.vivek.placementportal.models.Project;
import org.vivek.placementportal.models.Student;

import java.util.List;

public interface ProjectRepository extends JpaRepository<Project, Integer> {
    public List<Project> findAllByStudent(Student student);
}
