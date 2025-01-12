package com.cashcontrol.cashcontrol.model.response;


import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class LoginResponse {

    private String token;
    private String message;

    public LoginResponse(String message, String token) {
        this.message = message;
        this.token = token;
    }
}
