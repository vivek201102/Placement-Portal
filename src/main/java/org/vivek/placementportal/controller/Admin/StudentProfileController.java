package org.vivek.placementportal.controller.Admin;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.vivek.placementportal.service.*;

@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/admin")
public class StudentProfileController {
    private final StudentService studentService;
    private final StudentSkillService studentSkillService;
    private final EducationService educationService;
    private final ExperienceService experienceService;
    private final ProjectService projectService;

    @GetMapping("/get-student/{id}")
    public ResponseEntity<?> getStudent(@PathVariable String id){
        try {
            return ResponseEntity.ok(studentService.getStudent(id));
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

    @GetMapping("/get-education/{studentId}")
    public ResponseEntity<?> getEducations(@PathVariable String studentId){
        try {
            return ResponseEntity.ok(educationService.get(studentId));
        }
        catch (Exception e){
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }

    @GetMapping("/get-skills/{studentId}")
    public ResponseEntity<?> getStudentSkills(@PathVariable String studentId){
        try {
            return ResponseEntity.ok(studentSkillService.getStudentSkill(studentId));
        }
        catch (Exception e){
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }

    @GetMapping("/get-project/{studentId}")
    public ResponseEntity<?> getProjects(@PathVariable String studentId){
        try {
            return ResponseEntity.ok(projectService.getAll(studentId));
        }
        catch (Exception e){
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }
}
