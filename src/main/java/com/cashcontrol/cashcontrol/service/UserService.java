package com.cashcontrol.cashcontrol.service;

import com.cashcontrol.cashcontrol.constants.UserConstants;
import com.cashcontrol.cashcontrol.entity.user.User;
import com.cashcontrol.cashcontrol.exception.ResourceNotFoundException;
import com.cashcontrol.cashcontrol.model.request.UserRegistrationRequest;
import com.cashcontrol.cashcontrol.model.response.SuccessResponse;
import com.cashcontrol.cashcontrol.model.response.UserGameInfoDetailResponse;
import com.cashcontrol.cashcontrol.service.repoHandler.UserRepoHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;
@Slf4j
@Service
public class UserService {

    @Autowired
    private UserRepoHandler userRepoHandler;
    @Autowired
    private LevelInfoService levelInfoService;
    @Autowired
    private GameStartService gameStartService;


    //this method is used for user registration
    public SuccessResponse registerUser(UserRegistrationRequest registrationRequest) {

        User user = new User();
        UUID uuid = UUID.randomUUID();
        user.setUserId(uuid);
        user.setUsername(registrationRequest.getUsername());
        user.setPassword(registrationRequest.getPassword());

        return userRepoHandler.saveUser(user);
    }



    public UserGameInfoDetailResponse startGame(String userId) {
        //validate the user
        User user = userRepoHandler.findUserByUserId(userId);
        if (user == null){
            log.info("Exception: user not exist in the system for userId : {} ",userId);
            throw new ResourceNotFoundException(UserConstants.USER_NOT_FOUND);
        }
        return gameStartService.gameInitiation(user.getUserId());
    }
}


