package org.vivek.placementportal.service;

import org.vivek.placementportal.dto.RegisterApplicationRequest;
import org.vivek.placementportal.models.DriveApplication;

import java.util.List;

public interface DriveApplicationService {
    public DriveApplication register(RegisterApplicationRequest request);
    public DriveApplication updateStatus(int id, String status);
    public DriveApplication delete(int id);
    public List<DriveApplication> getStudentApplication(String studentId);
    public List<DriveApplication> getDriveApplications(int driveId);
    public DriveApplication getStatus(int driveId, String studentId);
    public List<DriveApplication> getPendingApplications();
    public List<DriveApplication> getAll();
    public List<DriveApplication> getStudentPendingApplication(String studentId);
    public List<DriveApplication> getPendingApplicationsOfPlaced();
}
