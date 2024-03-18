package org.vivek.placementportal.service;

import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.vivek.placementportal.dto.RegisterStudentSkillRequest;
import org.vivek.placementportal.models.Skill;
import org.vivek.placementportal.models.Student;
import org.vivek.placementportal.models.StudentSkill;
import org.vivek.placementportal.repository.SkillRepository;
import org.vivek.placementportal.repository.StudentRepository;
import org.vivek.placementportal.repository.StudentSkillRepository;

import java.util.Set;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class StudentSkillServiceImpl implements StudentSkillService{
    private final StudentSkillRepository studentSkillRepository;
    private final StudentRepository studentRepository;
    private final SkillRepository skillRepository;

    @Override
    public StudentSkill register(RegisterStudentSkillRequest request) {
        Student student = studentRepository.findByUserId(request.getStudentId());
        Skill skill = skillRepository.findById(request.getSkillId()).orElseThrow(()->new UsernameNotFoundException("Skill not registered"));
        if(student == null)
            throw new UsernameNotFoundException("Student not found");
        StudentSkill studentSkill = studentSkillRepository.findByStudentAndSkill(student, skill);
        if(studentSkill == null){
            studentSkill = StudentSkill
                    .builder()
                    .skill(skill)
                    .student(student)
                    .build();
            studentSkillRepository.save(studentSkill);
        }
        return studentSkill;
    }

    @Override
    public Set<StudentSkill> getStudentSkill(String studentId) {
        Student student = studentRepository.findByUserId(studentId);
        if(student == null)
            throw new UsernameNotFoundException("Student not found");
        return studentSkillRepository.findAllByStudent(student);
    }

    @Override
    public StudentSkill delete(int id) {
        StudentSkill studentSkill = studentSkillRepository.findById(id).orElseThrow(() -> new UsernameNotFoundException("Not found"));
        studentSkillRepository.delete(studentSkill);
        return studentSkill;
    }
}
