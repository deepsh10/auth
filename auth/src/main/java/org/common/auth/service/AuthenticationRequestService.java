package org.common.auth.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.common.auth.dto.NotificationEmail;
import org.common.auth.dto.AuthenticationRequest;
import org.common.auth.repository.AuthenticationRequestRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.Instant;
import java.util.UUID;

@Service
@AllArgsConstructor
@Slf4j
public class AuthenticationRequestService {

    private final AuthenticationRequestRepository authenticationRequestRepository;
    private final MailService mailService;

    @Transactional
    public void generateToken(String email){

        String uuid= UUID.randomUUID().toString();

        AuthenticationRequest auth = new AuthenticationRequest();
        auth.setEmailId(email);
        auth.setUuid(uuid);
        auth.setCreatedInstance(Instant.now());

        NotificationEmail notificationEmail = new NotificationEmail();
        notificationEmail.setMessage("Your URN is: "+uuid);
        notificationEmail.setRecipient(email);
        notificationEmail.setSubject("URN for your sign up in excellence standard");
        mailService.sendMail(notificationEmail);

        authenticationRequestRepository.save(auth);
        log.info("Saved");
    }

}
