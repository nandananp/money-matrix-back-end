package com.cashcontrol.cashcontrol.controller;

import com.cashcontrol.cashcontrol.model.request.UserRegistrationRequest;
import com.cashcontrol.cashcontrol.model.response.LevelInstructionResponse;
import com.cashcontrol.cashcontrol.model.response.SuccessResponse;
import com.cashcontrol.cashcontrol.model.response.UserGameInfoDetailResponse;
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


    //register user
    @PostMapping("/register")
    public SuccessResponse registerUser(@RequestBody UserRegistrationRequest registrationRequest){

        return userService.registerUser(registrationRequest);
    }

    //login



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

    //continue the existing game with user id













}
