package org.vivek.placementportal.controller.Admin;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.vivek.placementportal.dto.CompleteDriveRequest;
import org.vivek.placementportal.dto.RegisterDriveRequest;
import org.vivek.placementportal.dto.SendNotificationRequest;
import org.vivek.placementportal.models.PlacementDrive;
import org.vivek.placementportal.service.PlacementDriveService;

@RestController
@RequestMapping("/api/v1/admin")
@CrossOrigin("*")
@RequiredArgsConstructor
public class PlacementDriveController {
    private final PlacementDriveService placementDriveService;

    @PostMapping("/register-drive")
    public ResponseEntity<?> register(@RequestBody RegisterDriveRequest request){
        try{
            return ResponseEntity.ok(placementDriveService.register(request));
        }
        catch (Exception e){
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }

    @PutMapping("/update-drive/{id}")
    public ResponseEntity<?> update(@PathVariable int id, @RequestBody PlacementDrive placementDrive){
        try {
            return ResponseEntity.ok(placementDriveService.update(placementDrive, id));
        }
        catch (Exception e){
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }

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
            return ResponseEntity.ok(placementDriveService.getAll());
        }
        catch (Exception e){
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }

    @GetMapping("/get-drives-current")
    public ResponseEntity<?> getAllCurrent(){
        try{
            return ResponseEntity.ok(placementDriveService.getAllCurrent());
        }
        catch (Exception e){
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }

    @GetMapping("/get-unfinished-drives")
    public ResponseEntity<?> getUnfinished(){
        try {
            return ResponseEntity.ok(placementDriveService.getUnfinished());
        }
        catch (Exception e){
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }

    @GetMapping("/get-finished-drives")
    public ResponseEntity<?> getFinished(){
        try {
            return ResponseEntity.ok(placementDriveService.getFinished());
        }
        catch (Exception e){
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }

    @DeleteMapping("/delete-drive/{id}")
    public ResponseEntity<?> delete(@PathVariable int id){
        try {
            return ResponseEntity.ok(placementDriveService.delete(id));
        }
        catch (Exception e){
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }

    @PostMapping("/send-notifications/{driveId}")
    public ResponseEntity<?> sendNotifications(@PathVariable int driveId, @RequestBody SendNotificationRequest request){
        try {
            placementDriveService.sendNotification(request, driveId);
            return ResponseEntity.ok("success");
        }
        catch (Exception e){
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }

    @PutMapping("/complete-drive/{driveId}")
    public ResponseEntity<?> completeDrive(@PathVariable int driveId, @RequestBody CompleteDriveRequest request){
        try {
            return ResponseEntity.ok("");
        }
        catch (Exception e){
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }
}
