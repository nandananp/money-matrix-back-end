package com.cashcontrol.cashcontrol.exception;



import org.apache.coyote.BadRequestException;

import java.io.Serial;

public class InvalidRequestException extends BadRequestException {

    @Serial
    private static final long serialVersionUID = 1L;

    public InvalidRequestException(String message) {
        super(message);
    }
}
