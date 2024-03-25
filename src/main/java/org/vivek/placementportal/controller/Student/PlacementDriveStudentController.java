package org.vivek.placementportal.controller.Student;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.vivek.placementportal.service.PlacementDriveService;

@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/student")
public class PlacementDriveStudentController {
    private final PlacementDriveService placementDriveService;

    @GetMapping("/get-drive/{id}")
    public ResponseEntity<?> get(@PathVariable int id){
        try {
            return ResponseEntity.ok(placementDriveService.get(id));
        }
        catch (Exception e){
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }
    @GetMapping("/get-drives")
    public ResponseEntity<?> getAll(){
        try{
            return ResponseEntity.ok(placementDriveService.getAllCurrent());
        }
        catch (Exception e){
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }
}
