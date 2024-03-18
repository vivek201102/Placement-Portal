package org.vivek.placementportal.controller.Student;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.vivek.placementportal.dto.ExperienceRegisterRequest;
import org.vivek.placementportal.models.Experience;
import org.vivek.placementportal.service.ExperienceService;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/student")
@CrossOrigin("*")
public class ExperienceController {
    private final ExperienceService experienceService;

    @PostMapping("/register-experience")
    public ResponseEntity<?> registerExperience(@RequestBody ExperienceRegisterRequest request){
        try {
            return ResponseEntity.ok(experienceService.register(request));
        }
        catch (Exception e){
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }

    @PutMapping("/update-experience/{id}")
    public ResponseEntity<?> updateExperience(@RequestBody ExperienceRegisterRequest request, @PathVariable int id){
        try{
            return ResponseEntity.ok(experienceService.update(request, id));
        }
        catch (Exception e){
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }

    @GetMapping("/get-experience/{studentId}")
    public ResponseEntity<?> getExperience(@PathVariable String studentId){
        try {
            return ResponseEntity.ok(experienceService.get(studentId));
        }
        catch (Exception e){
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }

    @DeleteMapping("/delete-experience/{id}")
    public ResponseEntity<?> deleteExperience(@PathVariable int id){
        try {
            return ResponseEntity.ok(experienceService.delete(id));
        }
        catch (Exception e){
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }
}
