package org.vivek.placementportal.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.vivek.placementportal.dto.*;
import org.vivek.placementportal.models.ConfirmationToken;
import org.vivek.placementportal.models.Role;
import org.vivek.placementportal.models.User;
import org.vivek.placementportal.repository.ConfirmationTokenRepository;
import org.vivek.placementportal.repository.UserRepository;
import org.vivek.placementportal.security.JwtServices;
import org.vivek.placementportal.security.tfa.TwoFactorAuthenticationService;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final JwtServices jwtServices;
    private final AuthenticationManager authenticationManager;
    private final StudentService studentService;
    private final ConfirmationTokenRepository confirmationTokenRepository;
    private final EmailService emailService;
    private final TwoFactorAuthenticationService twoFactorAuthenticationService;
    @Value("${java.baseUrl}")
    private String baseUrl;
    @Override
    public AuthenticationResponse register(RegistrationRequest request, Role role) {
        // Creating user object
        User user = User.builder()
                .id(request.getId())
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .email(request.getEmail())
                .phone(request.getPhone())
                .address(request.getAddress())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(role)
                .isTFAEnabled(request.isTFAEnabled())
                .isVerified(false)
                .build();

        // Generate secret
        if(request.isTFAEnabled()){
            user.setSecret(twoFactorAuthenticationService.genrateNewSecret());
        }
        userRepository.save(user);
        if(role.name().equals("STUDENT_ROLE")){
            studentService.register(user);
        }
        // Generating JWT token
        var jwtToken = "";
        if(role.name().equals("STUDENT_ROLE")){
            jwtToken = jwtServices.generateToken(user);
        }

        // Generating token
        ConfirmationToken confirmationToken = new ConfirmationToken(user);
        confirmationTokenRepository.save(confirmationToken);

        // Sending email to verify email id
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setSubject("Confirm your email address");
        simpleMailMessage.setTo(user.getEmail());
        simpleMailMessage.setText(
                "Dear "+ user.getFirstName() + " " + user.getLastName() + "," + "\n" +
                        "\n" +
                        "Thank you for registering on our College Placement Portal. To complete your registration and gain access to our portal's features, we need to verify your email address.\n" +
                        "\n" +
                        "Please click on the link below to verify your email address:\n" +
                        "\n" +
                        baseUrl + "/api/v1/auth/confirm-email?token=" + confirmationToken.getConfirmationToken() + "\n" +
                        "\n" +
                        "If you have trouble clicking the link, please copy and paste the following URL into your browser:\n" +
                        "\n" +
                        "Once your email address is verified, you'll be able to log in to your account and start using our placement portal to explore job opportunities, upload your resume, and more.\n" +
                        "\n" +
                        "Thank you for choosing our College Placement Portal. If you have any questions or need assistance, please don't hesitate to contact us.\n" +
                        "\n" +
                        "Best regards,\n" +
                        "Dharmsinh Desai University"
        );
        emailService.sendEmail(simpleMailMessage);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .role(user.getRole().name())
                .isTFAEnabled(user.isTFAEnabled())
                .secretImageUri(twoFactorAuthenticationService.generateQRCodeImgURI(user.getSecret()))
                .build();
    }

    @Override
    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getId(),
                        request.getPassword()
                )
        );
        var user = userRepository.findById(request.getId()).orElseThrow();
        if(user.isTFAEnabled()){
            return AuthenticationResponse
                    .builder()
                    .token("")
                    .role("")
                    .email(user.getEmail())
                    .isTFAEnabled(true)
                    .build();
        }
        var jwtToken = jwtServices.generateToken(user);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .role(user.getRole().name())
                .isTFAEnabled(user.isTFAEnabled())
                .build();
    }

    @Override
    public ResponseEntity<String> confirmEmail(String confirmationToken) {
        ConfirmationToken token = confirmationTokenRepository.findConfirmationTokenByConfirmationToken(confirmationToken);

        if(token != null){
            User user = userRepository.findById(token.getUser().getId()).orElseThrow(() -> new UsernameNotFoundException("User not found"));
            user.setVerified(true);
            userRepository.save(user);
            confirmationTokenRepository.delete(token);
            return ResponseEntity.ok("Email verified successfully");
        }

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Could not find Email id");
    }

    @Override
    public ResponseEntity<String> sendTokenForReset(String email){
        try {
            User user = userRepository.findByEmail(email);
            if(user == null){
                throw new UsernameNotFoundException("User do not exist with email");
            }

            ConfirmationToken confirmationToken = new ConfirmationToken(user);
            confirmationTokenRepository.save(confirmationToken);

            SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
            simpleMailMessage.setTo(email);
            simpleMailMessage.setSubject("Reset you password");
            simpleMailMessage.setText(" Dear User,\n Please find token to reset your password:\\n" + "Token for reset you password:  " + confirmationToken.getConfirmationToken()
                    + "\nIf you did not request a password reset, please ignore `this email.\\n\\n"
                     + "Sincerely,\nDharmsin Desai University");
            emailService.sendEmail(simpleMailMessage);

            return ResponseEntity.ok("Email send successfully");
        }
        catch (Exception e){
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }

    @Override
    public ResponseEntity<?> confirmToken(TokenVerficationRequest request) {
        try{
            ConfirmationToken token = confirmationTokenRepository.findConfirmationTokenByConfirmationToken(request.getToken());
            if(token != null){
                User user = userRepository.findById(token.getUser().getId()).orElseThrow(() -> new UsernameNotFoundException("User does not exist"));

                if(user.getEmail().equals(request.getEmail()))
                    return ResponseEntity.ok(true);
            }
            return ResponseEntity.ok(false);
        }
        catch (Exception e){
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }

    @Override
    public AuthenticationResponse verifyCode(VerificationRequest verificationRequest) {
        User user = userRepository.findByEmail(verificationRequest.getEmail());
        if(user == null)
            throw new UsernameNotFoundException("User do not exist");

        if(twoFactorAuthenticationService.isOTPNotValid(user.getSecret(), verificationRequest.getCode())){
            throw new BadCredentialsException("Code is not correct");
        }
        var jwtToken = jwtServices.generateToken(user);
        return AuthenticationResponse
                .builder()
                .isTFAEnabled(true)
                .role(user.getRole().name())
                .token(jwtToken)
                .build();
    }

    @Override
    public ResponseEntity<String> resetPassword(ResetPasswordRequest request) {
        ConfirmationToken token = confirmationTokenRepository.findConfirmationTokenByConfirmationToken(request.getToken());

        if(token != null){
            User user = userRepository.findById(token.getUser().getId()).orElseThrow(() -> new UsernameNotFoundException("User not found"));
            if(user.getEmail().equals(request.getEmail()))
            {
                user.setPassword(passwordEncoder.encode(request.getPassword()));
                userRepository.save(user);

                confirmationTokenRepository.delete(token);
                return ResponseEntity.ok("Password reset successfully");
            }
            else{
                return ResponseEntity.badRequest().body("Invalid user!!");
            }
        }

        return ResponseEntity.badRequest().body("Could not find account");
    }
}
