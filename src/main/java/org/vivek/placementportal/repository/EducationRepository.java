package org.vivek.placementportal.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.vivek.placementportal.models.Education;

import java.util.List;

public interface EducationRepository extends JpaRepository<Education, Integer> {
    public List<Education> findAllByStudentId(int studentId);
}
