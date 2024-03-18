package org.vivek.placementportal.service;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.vivek.placementportal.dto.RegisterDriveRequest;
import org.vivek.placementportal.dto.SendNotificationRequest;
import org.vivek.placementportal.models.DriveApplication;
import org.vivek.placementportal.models.PlacementDrive;
import org.vivek.placementportal.models.Student;
import org.vivek.placementportal.repository.DriveApplicationRepository;
import org.vivek.placementportal.repository.PlacementDriveRepository;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

@Service
@AllArgsConstructor
public class PlacementDriveServiceImpl implements PlacementDriveService{
    private final PlacementDriveRepository placementDriveRepository;
    private final EmailService emailService;
    private final DriveApplicationRepository driveApplicationRepository;

    @Override
    public PlacementDrive register(RegisterDriveRequest request) {
        PlacementDrive placementDrive = PlacementDrive
                .builder()
                .companyName(request.getCompanyName())
                .description(request.getDescription())
                .CTC(request.getCTC())
                .jobRole(request.getJobRole())
                .criteria(request.getCriteria())
                .qualification(request.getQualification())
                .createdAt(new Date())
                .deadlineForApplication(request.getDeadlineForApplication())
                .status("ON GOING")
                .build();

        return placementDriveRepository.save(placementDrive);
    }

    @Override
    public PlacementDrive get(int id) {
        return placementDriveRepository.findById(id).orElseThrow(() -> new UsernameNotFoundException("Drive not found with Id: " + id));
    }

    @Override
    public PlacementDrive update(PlacementDrive placementDrive, int id) {
        PlacementDrive existingDrive = placementDriveRepository.findById(id).orElseThrow(() -> new UsernameNotFoundException("Drive not found with Id: " + id));

        existingDrive.setCompanyName(placementDrive.getCompanyName());
        existingDrive.setCTC(placementDrive.getCTC());
        existingDrive.setDescription(placementDrive.getDescription());
        existingDrive.setCriteria(placementDrive.getCriteria());
        existingDrive.setJobRole(placementDrive.getJobRole());
        existingDrive.setDeadlineForApplication(placementDrive.getDeadlineForApplication());
        existingDrive.setQualification(placementDrive.getQualification());

        placementDriveRepository.save(existingDrive);
        return existingDrive;
    }

    @Override
    public List<PlacementDrive> getAll() {
        return placementDriveRepository.findAll();
    }

    @Override
    public List<PlacementDrive> getAllCurrent() {
        Date today = new Date();
        return placementDriveRepository.findByDeadlineForApplicationGreaterThan(today);
    }

    @Override
    public List<PlacementDrive> getUnfinished(){
        return placementDriveRepository.findAllByStatusAndDeadlineForApplicationLessThan("ON GOING", new Date());
    }

    @Override
    public List<PlacementDrive> getFinished() {
        return placementDriveRepository.findAllByStatus("COMPLETED");
    }

    @Override

    public PlacementDrive delete(int id) {
        PlacementDrive placementDrive = placementDriveRepository.findById(id).orElseThrow(() -> new UsernameNotFoundException("Drive not found with Id: " + id));
        List<DriveApplication> driveApplications = driveApplicationRepository.findAllByPlacementDriveId(id);
        SendNotificationRequest request = SendNotificationRequest
                .builder()
                .subject("Placement drive of " + placementDrive.getCompanyName() + " cancelled")
                .text("Unfortunately due to some reason's " + placementDrive.getCompanyName() + " has cancelled their placement drive on campus due that reason your application has been removed")
                .build();
        sendNotification(request, id);
        driveApplicationRepository.deleteAll(driveApplications);
        placementDriveRepository.delete(placementDrive);
        return placementDrive;
    }

    @Override
    public void sendNotification(SendNotificationRequest request, int driveId) {
        List<DriveApplication> driveApplications = driveApplicationRepository.findAllByPlacementDriveId(driveId);
        List<String> recipients = driveApplications.stream().map(data -> data.getStudent().getUser().getEmail()).toList();

        String[] to = recipients.toArray(new String[0]);

        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setSubject(request.getSubject());
        simpleMailMessage.setText(request.getText());
        simpleMailMessage.setTo("admin@ddu.ac.in");
        simpleMailMessage.setBcc(to);

        emailService.sendEmail(simpleMailMessage);
    }

    @Override
    public PlacementDrive completeDrive(int id) {
        PlacementDrive placementDrive = placementDriveRepository.findById(id).orElseThrow(() -> new UsernameNotFoundException("Placement drive not found"));
        placementDrive.setStatus("COMPLETED");
        placementDriveRepository.save(placementDrive);

        List<DriveApplication> driveApplications = driveApplicationRepository.findAllByPlacementDriveId(id);
        driveApplications.stream().map(obj -> { obj.setStatus("DONE");  return obj;}).collect(Collectors.toList());
        driveApplicationRepository.saveAll(driveApplications);
        return placementDrive;
    }
}
