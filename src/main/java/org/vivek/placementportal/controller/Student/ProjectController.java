package org.vivek.placementportal.controller.Student;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.vivek.placementportal.dto.ProjectRegisterRequest;
import org.vivek.placementportal.service.ProjectService;

@RestController
@RequestMapping("/api/v1/student")
@AllArgsConstructor
@CrossOrigin("*")
public class ProjectController {
    private final ProjectService projectService;

    @PostMapping("/register-project")
    public ResponseEntity<?> registerProject(@RequestBody ProjectRegisterRequest request){
        try{
            return ResponseEntity.ok(projectService.register(request));
        }
        catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @PutMapping("/update-project/{id}")
    public ResponseEntity<?> updateProject(@RequestBody ProjectRegisterRequest request, @PathVariable int id){
        try {
            return ResponseEntity.ok(projectService.update(request, id));
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

    @DeleteMapping("/delete-project/{id}")
    public ResponseEntity<?> deleteProject(@PathVariable int id){
        try {
            return ResponseEntity.ok(projectService.delete(id));
        }
        catch (Exception e){
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }
}
