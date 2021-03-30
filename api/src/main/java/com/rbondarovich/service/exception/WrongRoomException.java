package com.rbondarovich.service.exception;

public class WrongRoomException extends RuntimeException {

    public WrongRoomException(String message) {
        super(message);
    }
}
