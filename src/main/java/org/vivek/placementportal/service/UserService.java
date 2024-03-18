package org.vivek.placementportal.service;

import org.springframework.http.ResponseEntity;
import org.vivek.placementportal.dto.*;
import org.vivek.placementportal.models.Role;

public interface UserService {
    public AuthenticationResponse register(RegistrationRequest request, Role role);
    public AuthenticationResponse authenticate(AuthenticationRequest request);
    public ResponseEntity<String> confirmEmail(String confirmationToken);
    public ResponseEntity<String> resetPassword(ResetPasswordRequest request);
    public ResponseEntity<String> sendTokenForReset(String email);
    public ResponseEntity<?> confirmToken(TokenVerficationRequest request);

    public AuthenticationResponse verifyCode(VerificationRequest verificationRequest);
}
