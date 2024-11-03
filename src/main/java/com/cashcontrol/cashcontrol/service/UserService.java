package com.cashcontrol.cashcontrol.service;

import com.cashcontrol.cashcontrol.entity.User;
import com.cashcontrol.cashcontrol.entity.MutualFund;
import com.cashcontrol.cashcontrol.model.request.UserRegistrationRequest;
import com.cashcontrol.cashcontrol.model.response.SuccessResponse;
import com.cashcontrol.cashcontrol.service.repoHandler.UserRepoHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UserService {

    @Autowired
    private UserRepoHandler userRepoHandler;


    //this method is used for user registration
    public SuccessResponse registerUser(UserRegistrationRequest registrationRequest) {

        User user = new User();
        UUID uuid = UUID.randomUUID();
        user.setUserId(uuid);
        user.setUsername(registrationRequest.getUsername());
        user.setPassword(registrationRequest.getPassword());

        return userRepoHandler.saveUser(user);
    }

}


