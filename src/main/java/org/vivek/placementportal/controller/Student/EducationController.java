package org.vivek.placementportal.controller.Student;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.vivek.placementportal.dto.EducationRegisterRequest;
import org.vivek.placementportal.models.Education;
import org.vivek.placementportal.service.EducationService;

@RestController
@RequestMapping("/api/v1/student")
@AllArgsConstructor
@CrossOrigin("*")
public class EducationController {
    private final EducationService educationService;

    @PostMapping("/register-education")
    public ResponseEntity<?> registerEducation(@RequestBody EducationRegisterRequest request){
        try{
            return ResponseEntity.ok(educationService.register(request));
        }
        catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @PutMapping("/update-education/{id}")
    public ResponseEntity<?> updateEducation(@RequestBody EducationRegisterRequest request, @PathVariable int id){
        try {
            return ResponseEntity.ok(educationService.update(request, id));
        }
        catch (Exception e){
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }

    @GetMapping("/get-education/{studentId}")
    public ResponseEntity<?> getEducations(@PathVariable String studentId){
        try {
            return ResponseEntity.ok(educationService.get(studentId));
        }
        catch (Exception e){
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }

    @DeleteMapping("/delete-education/{id}")
    public ResponseEntity<?> deleteEducation(@PathVariable int id){
        try {
            return ResponseEntity.ok(educationService.delete(id));
        }
        catch (Exception e){
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }
}
