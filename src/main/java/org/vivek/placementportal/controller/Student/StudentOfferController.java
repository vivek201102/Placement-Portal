package org.vivek.placementportal.controller.Student;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.vivek.placementportal.service.PlacedStudentService;

@RestController
@RequestMapping("/api/v1/student")
@CrossOrigin("*")
@AllArgsConstructor
public class StudentOfferController {
    private PlacedStudentService placedStudentService;

    @GetMapping("get-student-offer/{studentId}")
    public ResponseEntity<?> getStudentOffer(@PathVariable String studentId){
        try {
            return ResponseEntity.ok(placedStudentService.getStudentOffer(studentId));
        }
        catch (Exception e){
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }
}
