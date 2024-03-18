package org.vivek.placementportal.controller.Authentication;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.vivek.placementportal.dto.*;
import org.vivek.placementportal.models.Role;
import org.vivek.placementportal.service.UserService;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
@CrossOrigin("*")
public class AuthenticationController {
    private final UserService userService;

    @GetMapping("/hello")
    public ResponseEntity<?> hello(){
        return ResponseEntity.ok("Hello");
    }

    @PostMapping("/register-student")
    public ResponseEntity<?> register(@RequestBody RegistrationRequest request){
        try{
            var response = userService.register(request, Role.STUDENT_ROLE);
            if(response.isTFAEnabled())
                return ResponseEntity.ok(response);
            return ResponseEntity.accepted().build();
        }
        catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @PostMapping("/authenticate-user")
    public ResponseEntity<?> authenticate(@RequestBody AuthenticationRequest request){
        try{
            return ResponseEntity.ok(userService.authenticate(request));
        }
        catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @PostMapping("/verify-code")
    public ResponseEntity<?> verifyCode(@RequestBody VerificationRequest verificationRequest){
        return ResponseEntity.ok(userService.verifyCode(verificationRequest));
//        return ResponseEntity.ok("Success");
    }

    @GetMapping("/confirm-email")
    public ResponseEntity<?> confirmUserAccount(@RequestParam("token")String confirmationToken) {
        try{
            return userService.confirmEmail(confirmationToken);
        }
        catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @PostMapping("/reset-request")
    public ResponseEntity<?> requestReset(@RequestBody TokenRequest request){
        try {
            return userService.sendTokenForReset(request.getEmail());
        }
        catch (Exception e){
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }

    @PostMapping("/confirm-token")
    public ResponseEntity<?> confirmToken(@RequestBody TokenVerficationRequest request){
        try {
            return ResponseEntity.ok(userService.confirmToken(request));
        }
        catch (Exception e){
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }

    @PostMapping("/reset-password")
    public ResponseEntity<?> resetPassword(@RequestBody ResetPasswordRequest request){
        try{
            return ResponseEntity.ok(userService.resetPassword(request));
        }
        catch (Exception e){
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
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
}
