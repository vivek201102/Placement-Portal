package org.vivek.placementportal.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.vivek.placementportal.dto.ExperienceRegisterRequest;
import org.vivek.placementportal.models.Experience;
import org.vivek.placementportal.models.Student;
import org.vivek.placementportal.repository.ExperienceRepository;
import org.vivek.placementportal.repository.StudentRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ExperienceServiceImpl implements ExperienceService{
    private final StudentRepository studentRepository;
    private final ExperienceRepository experienceRepository;

    @Override
    public Experience register(ExperienceRegisterRequest request) {
        Student student = studentRepository.findByUserId(request.getStudentId());
        if(student == null)
            throw new UsernameNotFoundException("User not found");

        Experience experience = Experience
                .builder()
                .title(request.getTitle())
                .organization(request.getOrganization())
                .fromDate(request.getFromDate())
                .toDate(request.getToDate())
                .description(request.getDescription())
                .student(student)
                .build();

        return experienceRepository.save(experience);
    }

    @Override
    public Experience update(ExperienceRegisterRequest experience, int id) {
        Experience existingExperience = experienceRepository.findById(id).orElseThrow(() -> new UsernameNotFoundException("Experience does not exist with id: " + id));
        // Update each property
        existingExperience.setTitle(experience.getTitle());
        existingExperience.setOrganization(experience.getOrganization());
        existingExperience.setFromDate(experience.getFromDate());
        existingExperience.setToDate(experience.getToDate());
        existingExperience.setDescription(experience.getDescription());
        // Update in database
        experienceRepository.save(existingExperience);
        return existingExperience;
    }

    @Override
    public List<Experience> get(String studentId) {
        Student student = studentRepository.findByUserId(studentId);
        if(student == null)
            throw new UsernameNotFoundException("Student not found");
        return experienceRepository.findAllByStudentId(student.getId());
    }

    @Override
    public Experience delete(int id) {
        Experience experience = experienceRepository.findById(id).orElseThrow(() -> new UsernameNotFoundException("Experience not found"));
        experienceRepository.delete(experience);
        return experience;
    }
}
