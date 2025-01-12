package com.cashcontrol.cashcontrol.exception;

import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.io.Serial;

public class InvalidCredentialException extends UsernameNotFoundException {

    @Serial
    private static final long serialVersionUID = 1L;
    public InvalidCredentialException(String msg) {
        super(msg);
    }
}
