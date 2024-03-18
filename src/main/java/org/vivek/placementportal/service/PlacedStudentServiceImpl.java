package org.vivek.placementportal.service;

import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.vivek.placementportal.dto.PlacedStudentRequest;
import org.vivek.placementportal.dto.StudentOfferRequest;
import org.vivek.placementportal.models.PlacedStudent;
import org.vivek.placementportal.models.PlacementDrive;
import org.vivek.placementportal.models.Student;
import org.vivek.placementportal.repository.PlacedStudentRepository;
import org.vivek.placementportal.repository.PlacementDriveRepository;
import org.vivek.placementportal.repository.StudentRepository;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class PlacedStudentServiceImpl implements PlacedStudentService{
    private PlacedStudentRepository placedStudentRepository;
    private StudentRepository studentRepository;
    private PlacementDriveRepository placementDriveRepository;
    @Override
    public void register(PlacedStudentRequest request) {
        PlacementDrive placementDrive = placementDriveRepository.findById(request.getPlacementDriveId()).orElseThrow(() -> new UsernameNotFoundException("Placement drive not found"));

        List<PlacedStudent> placedStudents = new ArrayList<PlacedStudent>();

        for(StudentOfferRequest studentOfferRequest: request.getOffers()){
            Student student = studentRepository.findByUserId(studentOfferRequest.getId());
            if(student == null){
                throw new UsernameNotFoundException("Student not found");
            }
            student.setPlaced(true);
            studentRepository.save(student);

            PlacedStudent placedStudent = PlacedStudent
                    .builder()
                    .offerAmount(studentOfferRequest.getOffer())
                    .placementDrive(placementDrive)
                    .student(student)
                    .build();

            placedStudents.add(placedStudent);
        }
        placedStudentRepository.saveAll(placedStudents);
    }

    @Override
    public List<PlacedStudent> getStudentOffer(String studentId) {
        Student student = studentRepository.findByUserId(studentId);
        if(student == null)
            throw new UsernameNotFoundException("Student not found");

        return placedStudentRepository.findAllByStudent(student);
    }
}
