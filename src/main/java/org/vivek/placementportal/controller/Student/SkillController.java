package org.vivek.placementportal.controller.Student;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.vivek.placementportal.dto.RegisterStudentSkillRequest;
import org.vivek.placementportal.dto.SkillRegisterRequest;
import org.vivek.placementportal.service.SkillService;
import org.vivek.placementportal.service.StudentSkillService;

@RestController
@RequestMapping("/api/v1/student")
@AllArgsConstructor
@CrossOrigin("*")
public class SkillController {
    private final SkillService skillService;
    private final StudentSkillService studentSkillService;

    @PostMapping("/register-skill")
    public ResponseEntity<?> addSkill(@RequestBody SkillRegisterRequest request){
        try{
            return ResponseEntity.ok(skillService.register(request));
        }
        catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @GetMapping("/get-skills")
    public ResponseEntity<?> getAllSkills(){
        try {
            return ResponseEntity.ok(skillService.getAll());
        }
        catch (Exception e){
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }

    @GetMapping("/get-by-skill-name/{skillName}")
    public ResponseEntity<?> getSkillByName(@PathVariable String skillName){
        try {
            return ResponseEntity.ok(skillService.findByName(skillName));
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

    @PostMapping("/register-student-skill")
    public ResponseEntity<?> registerStudentSkill(@RequestBody RegisterStudentSkillRequest request){
        try {
            return ResponseEntity.ok(studentSkillService.register(request));
        }
        catch (Exception e){
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }

    @DeleteMapping("delete-student-skill/{id}")
    public ResponseEntity<?> deleteStudentSkill(@PathVariable int id){
        try {
            return ResponseEntity.ok(studentSkillService.delete(id));
        }
        catch (Exception e){
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }
}
