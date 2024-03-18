package org.vivek.placementportal.controller.Admin;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.vivek.placementportal.dto.RegistrationRequest;
import org.vivek.placementportal.models.Role;
import org.vivek.placementportal.service.StudentService;
import org.vivek.placementportal.service.UserService;


@RestController
@RequestMapping("/api/v1/admin")
@RequiredArgsConstructor
public class AdminController {
    private final UserService userService;
    private final StudentService studentService;

    @GetMapping("/hello")
    public ResponseEntity<?> hello(){
        return ResponseEntity.ok("Hello Admin");
    }

    @PostMapping("/register-admin")
    public ResponseEntity<?> registerAdmin(@RequestBody RegistrationRequest request){
        try {
            return ResponseEntity.ok(userService.register(request, Role.ADMIN_ROLE));
        }
        catch (Exception e){
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }

    @GetMapping("/get-students")
    public ResponseEntity<?> getStudents(){
        try {
            return ResponseEntity.ok(studentService.getAll());
        }
        catch (Exception e){
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }

    @DeleteMapping("/delete-student/{id}")
    public ResponseEntity<?> deleteStudent(@PathVariable String id){
        try {
            return ResponseEntity.ok(studentService.delete(id));
        }
        catch (Exception e){
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }
}
