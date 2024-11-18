package com.cashcontrol.cashcontrol.controller;

import com.cashcontrol.cashcontrol.model.request.UserRegistrationRequest;
import com.cashcontrol.cashcontrol.model.response.LevelInstructionResponse;
import com.cashcontrol.cashcontrol.model.response.SuccessResponse;
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




}
