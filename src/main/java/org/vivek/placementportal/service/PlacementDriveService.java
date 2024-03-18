package org.vivek.placementportal.service;

import jakarta.transaction.Transactional;
import org.vivek.placementportal.dto.RegisterDriveRequest;
import org.vivek.placementportal.dto.SendNotificationRequest;
import org.vivek.placementportal.models.PlacementDrive;

import java.util.List;

public interface PlacementDriveService {
    public PlacementDrive register(RegisterDriveRequest request);
    public PlacementDrive get(int id);
    public PlacementDrive update(PlacementDrive placementDrive, int id);
    public List<PlacementDrive> getAll();
    public List<PlacementDrive> getAllCurrent();
    public List<PlacementDrive> getUnfinished();
    public List<PlacementDrive> getFinished();
    @Transactional
    public PlacementDrive delete(int id);
    public void sendNotification(SendNotificationRequest request, int driveId);
    public PlacementDrive completeDrive(int id);
}
