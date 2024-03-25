package org.vivek.placementportal.controller.statistic;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.vivek.placementportal.service.DriveApplicationService;
import org.vivek.placementportal.service.PlacedStudentService;
import org.vivek.placementportal.service.PlacementDriveService;
import org.vivek.placementportal.service.StudentService;

@RestController
@RequestMapping("/api/v1/statistic")
@AllArgsConstructor
public class StatisticController {
    private final StudentService studentService;
    private final PlacementDriveService placementDriveService;
    private final DriveApplicationService driveApplicationService;
    private final PlacedStudentService placedStudentService;

    @GetMapping("get-student-count")
    public ResponseEntity<?> studentCount(){
        try {
            return ResponseEntity.ok(studentService.getAll().size());
        }
        catch (Exception e){
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }

    @GetMapping("get-drive-count")
    public ResponseEntity<?> driveCount(){
        try {
            return ResponseEntity.ok(placementDriveService.getAll().size());
        }
        catch (Exception e){
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }

    @GetMapping("get-application-count")
    public ResponseEntity<?> applicationCount(){
        try {
            return ResponseEntity.ok(driveApplicationService.getAll().size());
        }
        catch (Exception e){
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }

    @GetMapping("get-pending-application-count")
    public ResponseEntity<?> pendingApplicationCount(){
        try {
            return ResponseEntity.ok(driveApplicationService.getPendingApplications().size());
        }
        catch (Exception e){
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }

    @GetMapping("get-students-application-count/{studentId}")
    public ResponseEntity<?> studentsApplicationCount(@PathVariable String studentId){
        try {
            return ResponseEntity.ok(driveApplicationService.getStudentApplication(studentId).size());
        }
        catch (Exception e){
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }

    @GetMapping("get-students-pending-application-count/{studentId}")
    public ResponseEntity<?> studentsPendingApplicationCount(@PathVariable String studentId){
        try {
            return ResponseEntity.ok(driveApplicationService.getStudentPendingApplication(studentId).size());
        }
        catch (Exception e){
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }

    @GetMapping("get-offer-number")
    public ResponseEntity<?> getOfferNumber(){
        try {
            return ResponseEntity.ok(placedStudentService.getAllOffers().size());
        }
        catch (Exception e){
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }

    @GetMapping("get-highest-offer")
    public ResponseEntity<?> getHighestOffer(){
        try {
            return ResponseEntity.ok(placedStudentService.getHighestOffer());
        }
        catch (Exception e){
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }
}
