package org.vivek.placementportal.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.vivek.placementportal.models.Experience;

import java.util.List;

public interface ExperienceRepository extends JpaRepository<Experience, Integer> {
    public List<Experience> findAllByStudentId(int studentId);
}
