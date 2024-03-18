package org.vivek.placementportal.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.vivek.placementportal.models.Skill;
import org.vivek.placementportal.models.Student;
import org.vivek.placementportal.models.StudentSkill;

import java.util.Set;

public interface StudentSkillRepository extends JpaRepository<StudentSkill, Integer> {
    public Set<StudentSkill> findAllByStudent(Student student);
    public StudentSkill findByStudentAndSkill(Student student, Skill skill);
}
