package org.vivek.placementportal.controller.Admin;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.vivek.placementportal.service.DriveApplicationService;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/admin")
public class DriveApplicationAdminController {
    private final DriveApplicationService driveApplicationService;

    @PutMapping("/approve-application/{id}")
    public ResponseEntity<?> approveApplication(@PathVariable int id){
        try {
            return ResponseEntity.ok(driveApplicationService.updateStatus(id, "APPROVED"));
        }
        catch (Exception e){
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }

    @PutMapping("/reject-application/{id}")
    public ResponseEntity<?> rejectApplication(@PathVariable int id){
        try {
            return ResponseEntity.ok(driveApplicationService.updateStatus(id, "REJECTED"));
        }
        catch (Exception e){
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }

    @GetMapping("/get-applications/{driveId}")
    public ResponseEntity<?> getAllApplication(@PathVariable int driveId){
        try {
            return ResponseEntity.ok(driveApplicationService.getDriveApplications(driveId));
        }
        catch (Exception e){
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }

    @GetMapping("/get-all-applications/{driveId}")
    public ResponseEntity<?> getAllDriveApplication(@PathVariable int driveId){
        try {
            return ResponseEntity.ok(driveApplicationService.getAllDriveApplicationByDrive(driveId));
        }
        catch (Exception e){
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }

    @GetMapping("/get-pending-application")
    public ResponseEntity<?> getAllPendingApplication(){
        try {
            return ResponseEntity.ok(driveApplicationService.getPendingApplications());
        }
        catch (Exception e){
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }

    @GetMapping("/get-pending-application-placed")
    public ResponseEntity<?> getAllPendingApplicationOfPlaced(){
        try {
            return ResponseEntity.ok(driveApplicationService.getPendingApplicationsOfPlaced());
        }
        catch (Exception e){
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }
}
