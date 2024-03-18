package org.vivek.placementportal.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.vivek.placementportal.dto.EducationRegisterRequest;
import org.vivek.placementportal.models.Education;
import org.vivek.placementportal.models.Student;
import org.vivek.placementportal.repository.EducationRepository;
import org.vivek.placementportal.repository.StudentRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EducationServiceImpl implements EducationService{
    private final EducationRepository educationRepository;
    private final StudentRepository studentRepository;
    @Override
    public Education register(EducationRegisterRequest request) {
        Student student = studentRepository.findByUserId(request.getStudentId());
        if(student == null)
            throw new UsernameNotFoundException("Student not found");

        Education education = Education
                .builder()
                .degree(request.getDegree())
                .fieldOfStudy(request.getFieldOfStudy())
                .fromDate(request.getFromDate())
                .toDate(request.getToDate())
                .institute(request.getInstitute())
                .result(request.getResult())
                .student(student)
                .build();
        return educationRepository.save(education);
    }

    @Override
    public Education update(EducationRegisterRequest request, int id) {
        Education existingEducation = educationRepository.findById(id).orElseThrow(() -> new UsernameNotFoundException("Education not found with id: " + id));

        existingEducation.setDegree(request.getDegree());
        existingEducation.setFieldOfStudy(request.getFieldOfStudy());
        existingEducation.setInstitute(request.getInstitute());
        existingEducation.setFromDate(request.getFromDate());
        existingEducation.setToDate(request.getToDate());
        existingEducation.setResult(request.getResult());


        return educationRepository.save(existingEducation);
    }

    @Override
    public Education delete(int id) {
        Education education = educationRepository.findById(id).orElseThrow(() -> new UsernameNotFoundException("Education not found"));
        educationRepository.delete(education);
        return education;
    }

    @Override
    public List<Education> get(String studentId) {
        Student student = studentRepository.findByUserId(studentId);

        return educationRepository.findAllByStudentId(student.getId());
    }
}
