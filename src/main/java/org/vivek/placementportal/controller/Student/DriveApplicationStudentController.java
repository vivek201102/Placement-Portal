package org.vivek.placementportal.controller.Student;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.vivek.placementportal.dto.RegisterApplicationRequest;
import org.vivek.placementportal.service.DriveApplicationService;

@RestController
@RequestMapping("/api/v1/student")
@AllArgsConstructor
public class DriveApplicationStudentController {
    private final DriveApplicationService driveApplicationService;

    @PostMapping("/register-application")
    public ResponseEntity<?> registerApplication(@RequestBody RegisterApplicationRequest request){
        try {
            return ResponseEntity.ok(driveApplicationService.register(request));
        }
        catch (Exception e){
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }



    @GetMapping("/get-applications/{studentId}")
    public ResponseEntity<?> getAllApplication(@PathVariable String studentId){
        try {
            return ResponseEntity.ok(driveApplicationService.getStudentApplication(studentId));
        }
        catch (Exception e){
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }

    @GetMapping("/get-status/{driveId}/{studentId}")
    public ResponseEntity<?> getApplicationStatus(@PathVariable int driveId, @PathVariable String studentId){
        try {
            return ResponseEntity.ok(driveApplicationService.getStatus(driveId, studentId));
        }
        catch (Exception e){
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }

    @DeleteMapping("/delete-application/{id}")
    public ResponseEntity<?> delete(@PathVariable int id){
        try {
            return ResponseEntity.ok(driveApplicationService.delete(id));
        }
        catch (Exception e){
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }
}
