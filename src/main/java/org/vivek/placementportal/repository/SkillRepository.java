package org.vivek.placementportal.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.vivek.placementportal.models.Skill;
import org.vivek.placementportal.models.Student;

import java.util.List;
import java.util.Set;

public interface SkillRepository extends JpaRepository<Skill, Integer> {
    public Skill findByName(String skillName);
}
