package org.vivek.placementportal.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.vivek.placementportal.models.DriveApplication;
import org.vivek.placementportal.models.PlacementDrive;
import org.vivek.placementportal.models.Student;

import java.util.List;

public interface DriveApplicationRepository extends JpaRepository<DriveApplication, Integer> {
    public List<DriveApplication> findAllByStudent(Student student);
    public List<DriveApplication> findAllByPlacementDriveId(int id);
    public DriveApplication findByPlacementDriveAndStudent(PlacementDrive placementDrive, Student student);
    public List<DriveApplication> findAllByStatus(String status);
    public List<DriveApplication> findAllByStudentAndStatus(Student student, String status);
    public List<DriveApplication> findAllByPlacementDriveIdAndStatus(int placementDriveId, String status);
    public List<DriveApplication> findAllByStudentPlacementStatusAndStatus(String studentPlacementStatus, String status);
}
