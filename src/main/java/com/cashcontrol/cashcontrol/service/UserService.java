package com.cashcontrol.cashcontrol.service;

import com.cashcontrol.cashcontrol.constants.UserConstants;
import com.cashcontrol.cashcontrol.entity.user.User;
import com.cashcontrol.cashcontrol.exception.InvalidCredentialException;
import com.cashcontrol.cashcontrol.exception.InvalidRequestException;
import com.cashcontrol.cashcontrol.exception.ResourceNotFoundException;
import com.cashcontrol.cashcontrol.model.request.EventRequest;
import com.cashcontrol.cashcontrol.model.request.LoginRequest;
import com.cashcontrol.cashcontrol.model.request.UserRegistrationRequest;
import com.cashcontrol.cashcontrol.model.response.LoginResponse;
import com.cashcontrol.cashcontrol.model.response.SuccessResponse;
import com.cashcontrol.cashcontrol.model.response.UserGameInfoDetailResponse;
import com.cashcontrol.cashcontrol.service.core.SecurityUtil;
import com.cashcontrol.cashcontrol.service.jwt.JWTService;
import com.cashcontrol.cashcontrol.service.repoHandler.UserRepoHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;
@Slf4j
@Service
public class UserService {

    @Autowired
    private UserRepoHandler userRepoHandler;
    @Autowired
    private JWTService jwtService;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    private LevelInfoService levelInfoService;
    @Autowired
    private GameStartService gameStartService;
    @Autowired
    private EventService eventService;
    @Autowired
    private FinancialReportService financialReportService;



    //this method is used for user registration
    public SuccessResponse registerUser(UserRegistrationRequest registrationRequest) throws InvalidRequestException {
        Optional<User> existingUser = userRepoHandler.findByUsername(registrationRequest.getUsername());
        if (existingUser.isPresent()){
            throw new InvalidRequestException("username already exist");
        }
        User user = new User();
        UUID uuid = UUID.randomUUID();
        user.setUserId(uuid);
        user.setUsername(registrationRequest.getUsername());
        user.setPassword(bCryptPasswordEncoder.encode(registrationRequest.getPassword()));
        return userRepoHandler.saveUser(user);
    }

    public LoginResponse loginUser(LoginRequest loginRequest) {
        User user = userRepoHandler.findByUsername(loginRequest.getUsername())
                .orElseThrow(() -> new InvalidCredentialException("Invalid credentials"));
        try {
            // Match the raw password with the encoded password
            if (bCryptPasswordEncoder.matches(loginRequest.getPassword(), user.getPassword())) {
                Authentication authenticate = authenticationManager.authenticate(
                        new UsernamePasswordAuthenticationToken(String.valueOf(user.getUserId()), loginRequest.getPassword()));

                if (authenticate.isAuthenticated()) {
                    String token = jwtService.generateToken(user);
                    return new LoginResponse("Success", token);
                } else {
                    throw new BadCredentialsException("NOT AUTHENTICATED !!!");
                }
            } else {
                throw new BadCredentialsException("Invalid credentials");
            }
        } catch (Exception e) {
            throw new RuntimeException("Login failed", e);
        }

    }


    public UserGameInfoDetailResponse startGame() {
        //user id can be fetched from spring boot security context
        String userId = SecurityUtil.currentUserId();
        //validate the user
        User user = userRepoHandler.findUserByUserId(userId);
        if (user == null){
            log.info("Exception: user not exist in the system for userId : {} ",userId);
            throw new ResourceNotFoundException(UserConstants.USER_NOT_FOUND);
        }
        return gameStartService.gameInitiation(user.getUserId());
    }

    public UserGameInfoDetailResponse checkGameStatus() {
        //user id can be fetched from spring boot security context
        String userId = SecurityUtil.currentUserId();
        User user = userRepoHandler.findUserByUserId(userId);
        if (user == null){
            log.info("Exception: user not exist in the system for userId : {} ",userId);
            throw new ResourceNotFoundException(UserConstants.USER_NOT_FOUND);
        }
        return gameStartService.checkGameStatus(user);
    }

    public SuccessResponse eventDecision(EventRequest eventRequest) throws InvalidRequestException {
        //user id can be fetched from spring boot security context
        String userId = SecurityUtil.currentUserId();
        User user = userRepoHandler.findUserByUserId(userId);
        if (user == null){
            log.info("Exception: user not exist in the system for userId : {} ",userId);
            throw new ResourceNotFoundException(UserConstants.USER_NOT_FOUND);
        }
        return eventService.eventDecision(userId,eventRequest);
    }

    public SuccessResponse updateLiabilityDetailsOfUser(String liabilityId) throws InvalidRequestException {
        //validate the user
        String userId = SecurityUtil.currentUserId();
        User user = userRepoHandler.findUserByUserId(userId);
        if (user == null){
            log.info("Exception: user not exist in the system for userId : {} ",userId);
            throw new ResourceNotFoundException(UserConstants.USER_NOT_FOUND);
        }
        return financialReportService.updateLiabilityDetailsOfUser(userId,liabilityId);

    }
}


