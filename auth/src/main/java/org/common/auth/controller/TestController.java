package org.common.auth.controller;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.common.auth.dto.NotificationEmail;
import org.common.auth.dto.SignUpRequest;
import org.common.auth.service.MailService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@AllArgsConstructor
@RequestMapping("/api/auth")
public class TestController {

    private final MailService mailService;

    private final PasswordEncoder passwordEncoder;


    @PostMapping("/signUp")
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

}
