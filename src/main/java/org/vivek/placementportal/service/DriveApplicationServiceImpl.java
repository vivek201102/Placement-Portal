package org.vivek.placementportal.service;

import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.vivek.placementportal.dto.RegisterApplicationRequest;
import org.vivek.placementportal.models.DriveApplication;
import org.vivek.placementportal.models.PlacementDrive;
import org.vivek.placementportal.models.Student;
import org.vivek.placementportal.repository.DriveApplicationRepository;
import org.vivek.placementportal.repository.PlacementDriveRepository;
import org.vivek.placementportal.repository.StudentRepository;

import java.util.Date;
import java.util.List;

@Service
@AllArgsConstructor
public class DriveApplicationServiceImpl implements DriveApplicationService {
    private final DriveApplicationRepository driveApplicationRepository;
    private final PlacementDriveRepository placementDriveRepository;
    private final StudentRepository studentRepository;

    @Override
    public DriveApplication register(RegisterApplicationRequest request) {
        PlacementDrive placementDrive = placementDriveRepository.findById(request.getDriveId()).orElseThrow(() -> new UsernameNotFoundException("Placement drive not found"));

        Student student = studentRepository.findByUserId(request.getStudentId());
        if(student == null){
            throw new UsernameNotFoundException("Student not found");
        }

        DriveApplication driveApplication = DriveApplication
                .builder()
                .placementDrive(placementDrive)
                .student(student)
                .appliedAt(new Date())
                .status("APPLIED")
                .studentPlacementStatus(request.getStudentStatus())
                .build();

        return driveApplicationRepository.save(driveApplication);
    }

    @Override
    public DriveApplication updateStatus(int id, String status) {
        DriveApplication driveApplication = driveApplicationRepository.findById(id).orElseThrow(()-> new UsernameNotFoundException("Application not found"));

        driveApplication.setStatus(status);
        return driveApplicationRepository.save(driveApplication);
    }

    @Override
    public DriveApplication delete(int id) {
        DriveApplication driveApplication = driveApplicationRepository.findById(id).orElseThrow(() -> new UsernameNotFoundException("Application not found"));
        driveApplicationRepository.delete(driveApplication);
        return driveApplication;
    }

    @Override
    public List<DriveApplication> getStudentApplication(String studentId) {
        Student student = studentRepository.findByUserId(studentId);
        return driveApplicationRepository.findAllByStudent(student);
    }

    @Override
    public List<DriveApplication> getDriveApplications(int driveId) {
        return driveApplicationRepository.findAllByPlacementDriveIdAndStatus(driveId, "APPROVED");
    }

    @Override
    public DriveApplication getStatus(int driveId, String studentId) {
        Student student = studentRepository.findByUserId(studentId);
        PlacementDrive placementDrive = placementDriveRepository.findById(driveId).orElseThrow(()-> new UsernameNotFoundException("Placement Drive not found"));
        if(student == null)
            throw new UsernameNotFoundException("Student not found");

        return driveApplicationRepository.findByPlacementDriveAndStudent(placementDrive, student);
    }

    @Override
    public List<DriveApplication> getPendingApplications() {
        return driveApplicationRepository.findAllByStudentPlacementStatusAndStatus("NOT PLACED","APPLIED");
    }

    @Override
    public List<DriveApplication> getPendingApplicationsOfPlaced() {
        return driveApplicationRepository.findAllByStudentPlacementStatusAndStatus("PLACED","APPLIED");
    }

    @Override
    public List<DriveApplication> getAll() {
        return driveApplicationRepository.findAll();
    }

    @Override
    public List<DriveApplication> getStudentPendingApplication(String studentId) {
        Student student = studentRepository.findByUserId(studentId);
        return driveApplicationRepository.findAllByStudentAndStatus(student, "APPLIED");
    }
}
