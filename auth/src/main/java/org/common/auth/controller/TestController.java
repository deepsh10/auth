package org.common.auth.controller;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.common.auth.dto.NotificationEmail;
import org.common.auth.dto.SignUpRequest;
import org.common.auth.model.User;
import org.common.auth.service.AuthenticationRequestService;
import org.common.auth.service.MailService;
import org.common.auth.service.UserSignUpSerivce;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@AllArgsConstructor
@Slf4j
@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@RequestMapping("/api/auth")
public class TestController {

    private final MailService mailService;

    private final PasswordEncoder passwordEncoder;
    private final AuthenticationRequestService authenticationRequestService;
    private final UserSignUpSerivce userSignUpSerivce;

    @PostMapping("/sign")
    public ResponseEntity<String> signUp(@RequestBody SignUpRequest signUpRequest)
    {
        System.out.println(signUpRequest);
        System.out.println("Encoding your password" + passwordEncoder.encode(signUpRequest.getPassword()));

        NotificationEmail notificationEmail = new NotificationEmail();
        notificationEmail.setMessage("Account Created");
        notificationEmail.setRecipient(signUpRequest.getEmailId());
        notificationEmail.setSubject("Auth Notification");
        mailService.sendMail(notificationEmail);
        return new ResponseEntity<>("User Registered successfully", HttpStatus.OK);
    }

    @PostMapping("/generateToken")
    public ResponseEntity<String> generateToken(@RequestParam String email)
    {
        log.info("Generate Token");

        authenticationRequestService.generateToken(email);

        return new ResponseEntity<>("Token Sent",HttpStatus.OK);
    }

    @PostMapping(value = "/signUp",consumes = "application/json")
    public ResponseEntity<String> signUp(@RequestBody User user){
        HttpStatus httpStatus = userSignUpSerivce.signUp(user);
        return new ResponseEntity<>("Updated",httpStatus);
    }

}
