package org.common.auth.service;

import lombok.AllArgsConstructor;
import org.common.auth.dto.AuthenticationRequest;
import org.common.auth.model.User;
import org.common.auth.repository.AuthenticationRequestRepository;
import org.common.auth.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class UserSignUpSerivce {

    private final UserRepository userRepository;
    private final AuthenticationRequestRepository authenticationRequestRepository;

    public HttpStatus signUp(User user){

        List<AuthenticationRequest> authenticationRequestList = authenticationRequestRepository.findAllByURN(user.getEmailId(),user.getUrn());
        if(authenticationRequestList != null){
            System.out.println(authenticationRequestList);
        }

        if(authenticationRequestList.size() != 1)
        {
            return HttpStatus.NOT_FOUND;
        }

        userRepository.save(user);

        return HttpStatus.ACCEPTED;
    }

}
