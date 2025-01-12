package com.cashcontrol.cashcontrol.controller;

import com.cashcontrol.cashcontrol.exception.InvalidRequestException;
import com.cashcontrol.cashcontrol.model.request.EventRequest;
import com.cashcontrol.cashcontrol.model.request.LoginRequest;
import com.cashcontrol.cashcontrol.model.request.UserRegistrationRequest;
import com.cashcontrol.cashcontrol.model.response.*;
import com.cashcontrol.cashcontrol.service.EventService;
import com.cashcontrol.cashcontrol.service.LevelInfoService;
import com.cashcontrol.cashcontrol.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/user")
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private LevelInfoService levelInfoService;
    @Autowired
    private EventService eventService;


    //register user
    @PostMapping("/register")
    public SuccessResponse registerUser(@RequestBody UserRegistrationRequest registrationRequest) throws InvalidRequestException {

        return userService.registerUser(registrationRequest);
    }

    //login
    @PostMapping("/login")
    public LoginResponse loginUser(@RequestBody LoginRequest loginRequest){
        return userService.loginUser(loginRequest);
    }


    //fetch all instruction
    @GetMapping("/info/levels")
    private List<LevelInstructionResponse> getMeInstruction(){
        return levelInfoService.getMeFullInstruction();
    }

    //fetch instruction of a particular level
    @GetMapping("/info/levels/{id}")
    private List<LevelInstructionResponse> getMeInstructionOfLevel(@PathVariable(value = "id")String id){
        return levelInfoService.getMeFullInstructionOfLevel(id);
    }

    @PostMapping("/start/game/{userId}")
    public UserGameInfoDetailResponse startGame(@PathVariable(value = "userId")String userId){
        return userService.startGame(userId);
    }

    //check previous game status with user id -> if present enable the continue button as well with start button
    @GetMapping("/game/status/{userId}")
    public UserGameInfoDetailResponse checkGameStatus(@PathVariable(value = "userId")String userId){
        return userService.checkGameStatus(userId);
    }

    @GetMapping("/game/next-event")
    public EventResponse getGameNextEvent() throws InvalidRequestException {
        return eventService.nextEvent();
    }

    @PostMapping("/game/event-decision/{userId}")
    public SuccessResponse eventDecision(@PathVariable(value = "userId")String userId,
                                         @RequestBody EventRequest eventRequest) throws InvalidRequestException {
        return userService.eventDecision(userId,eventRequest);
    }














}
