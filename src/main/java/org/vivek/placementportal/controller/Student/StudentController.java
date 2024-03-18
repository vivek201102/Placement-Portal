package org.vivek.placementportal.controller.Student;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.vivek.placementportal.service.StudentService;

@RestController
@RequestMapping("/api/v1/student")
@CrossOrigin("*")
@AllArgsConstructor
public class StudentController {
    private final StudentService studentService;

    @GetMapping("/hello")
    public ResponseEntity<?> hello(){
        return ResponseEntity.ok("Hello student");
    }

    @GetMapping("/get-student/{id}")
    public ResponseEntity<?> getStudent(@PathVariable String id){
        try{
            return ResponseEntity.ok(studentService.getStudent(id));
        }
        catch (Exception e){
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }
    @GetMapping("/get-student-data/{id}")
    public ResponseEntity<?> getStudentData(@PathVariable String id){
        try{
            return ResponseEntity.ok(studentService.getStudentData(id));
        }
        catch (Exception e){
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }
}