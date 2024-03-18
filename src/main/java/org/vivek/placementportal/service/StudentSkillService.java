package org.vivek.placementportal.service;

import org.vivek.placementportal.dto.RegisterStudentSkillRequest;
import org.vivek.placementportal.models.Skill;
import org.vivek.placementportal.models.StudentSkill;

import java.util.Set;

public interface StudentSkillService {
    public StudentSkill register(RegisterStudentSkillRequest request);
    public Set<StudentSkill> getStudentSkill(String studentId);
    public StudentSkill delete(int id);
}
