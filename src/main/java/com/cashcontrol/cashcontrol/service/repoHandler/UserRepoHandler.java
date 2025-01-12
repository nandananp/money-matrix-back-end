package com.cashcontrol.cashcontrol.service.repoHandler;

import com.cashcontrol.cashcontrol.constants.AdminConstants;
import com.cashcontrol.cashcontrol.entity.user.User;
import com.cashcontrol.cashcontrol.model.response.SuccessResponse;
import com.cashcontrol.cashcontrol.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class UserRepoHandler {

    @Autowired
    private UserRepository userRepository;

    public SuccessResponse saveUser(User user) {

        userRepository.save(user);

        SuccessResponse successResponse = new SuccessResponse();
        successResponse.setMessage(AdminConstants.USER_REGISTRATION_SUCCESS_RESPONSE);
        return successResponse;
    }

    public User findUserByUserId(String userId) {
        return userRepository.findByUserId(UUID.fromString(userId));
    }

    public Optional<User> findByUsername(String username){
        return userRepository.findByUsername(username);
    }
}
