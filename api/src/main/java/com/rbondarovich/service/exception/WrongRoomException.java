package com.rbondarovich.service.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.OK)
public class WrongRoomException extends RuntimeException {

    public WrongRoomException(String message) {
        super(message);
    }
}
