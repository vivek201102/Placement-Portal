package org.vivek.placementportal.service;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.vivek.placementportal.dto.PlacedStudentRequest;
import org.vivek.placementportal.dto.StudentOfferRequest;
import org.vivek.placementportal.models.PlacedStudent;
import org.vivek.placementportal.models.PlacementDrive;
import org.vivek.placementportal.models.Student;
import org.vivek.placementportal.models.User;
import org.vivek.placementportal.repository.PlacedStudentRepository;
import org.vivek.placementportal.repository.PlacementDriveRepository;
import org.vivek.placementportal.repository.StudentRepository;
import org.vivek.placementportal.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class PlacedStudentServiceImpl implements PlacedStudentService{
    private PlacedStudentRepository placedStudentRepository;
    private StudentRepository studentRepository;
    private PlacementDriveRepository placementDriveRepository;
    private UserRepository userRepository;
    private EmailService emailService;
    @Override
    public void register(PlacedStudentRequest request) {
        PlacementDrive placementDrive = placementDriveRepository.findById(request.getPlacementDriveId()).orElseThrow(() -> new UsernameNotFoundException("Placement drive not found"));
        StringBuilder message = new StringBuilder("List of selected student in " + placementDrive.getCompanyName() + " are:\n");

        List<PlacedStudent> placedStudents = new ArrayList<PlacedStudent>();

        for(StudentOfferRequest studentOfferRequest: request.getOffers()){
            Student student = studentRepository.findByUserId(studentOfferRequest.getId());
            if(student == null){
                throw new UsernameNotFoundException("Student not found");
            }

            message.append(student.getUser().getFirstName()).append(" ").append(student.getUser().getLastName()).append("\n");

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

        message.append("Congratulations to all selected students");

        String[] emails = userRepository.findAll().stream().map(User::getEmail).toList().toArray(new String[0]);
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setSubject("Result");
        simpleMailMessage.setCc(emails);
        simpleMailMessage.setText(message.toString());
        emailService.sendEmail(simpleMailMessage);


        placedStudentRepository.saveAll(placedStudents);
    }

    @Override
    public List<PlacedStudent> getStudentOffer(String studentId) {
        Student student = studentRepository.findByUserId(studentId);
        if(student == null)
            throw new UsernameNotFoundException("Student not found");

        return placedStudentRepository.findAllByStudent(student);
    }

    @Override
    public List<PlacedStudent> getOffersByCompany(int driveId){
        return placedStudentRepository.findAllByPlacementDriveId(driveId);
    }

    @Override
    public List<PlacedStudent> getAllOffers() {
        return placedStudentRepository.findAll();
    }

    @Override
    public Long getHighestOffer() {
        return placedStudentRepository.findTopByOrderByOfferAmountDesc().getOfferAmount();
    }
}
