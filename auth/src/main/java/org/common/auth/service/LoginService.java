package org.common.auth.service;

import lombok.AllArgsConstructor;
import org.common.auth.dto.LoginRequest;
import org.common.auth.model.User;
import org.common.auth.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class LoginService {

    private final UserRepository userRepository;

    public HttpStatus loginRequest(LoginRequest loginRequest){
        List<User> userList = userRepository.findAllByLogin(loginRequest.getEmailId(),loginRequest.getPassword());
        if(userList != null && userList.size() < 1){
            return HttpStatus.NOT_FOUND;
        }

        return HttpStatus.OK;
    }

}
