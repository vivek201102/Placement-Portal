package org.vivek.placementportal.controller.Admin;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.vivek.placementportal.dto.PlacedStudentRequest;
import org.vivek.placementportal.service.PlacedStudentService;
import org.vivek.placementportal.service.PlacementDriveService;

@RestController
@RequestMapping("/api/v1/admin")
@CrossOrigin("*")
@AllArgsConstructor
public class PlacedStudentController {
    private PlacedStudentService placedStudentService;
    private PlacementDriveService placementDriveService;

    @PostMapping("place-students")
    public ResponseEntity<?> placeStudents(@RequestBody PlacedStudentRequest placedStudentRequest){
        try {
            placedStudentService.register(placedStudentRequest);
            return ResponseEntity.ok(placementDriveService.completeDrive(placedStudentRequest.getPlacementDriveId()));
        }
        catch (Exception e){
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }

    @GetMapping("get-placed-students/{driveId}")
    public ResponseEntity<?> getPlacedStudentByDrive(@PathVariable int driveId){
        try {
            return ResponseEntity.ok(placedStudentService.getOffersByCompany(driveId));
        }
        catch (Exception e){
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }
}
