package com.cashcontrol.cashcontrol.controller;

import com.cashcontrol.cashcontrol.model.request.UserRegistrationRequest;
import com.cashcontrol.cashcontrol.model.response.SuccessResponse;
import com.cashcontrol.cashcontrol.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/user")
public class UserController {

    @Autowired
    private UserService userService;


    @PostMapping("/register")
    public SuccessResponse registerUser(@RequestBody UserRegistrationRequest registrationRequest){

        return userService.registerUser(registrationRequest);
    }

//    @PostMapping("/mutualfund/user2")
//    public SuccessResponse mutualfundUser1(@RequestBody UserMutualfundRequest MutualfundRequest){
//        return userService.mutualfundUser1(MutualfundRequest);
//    }

}
