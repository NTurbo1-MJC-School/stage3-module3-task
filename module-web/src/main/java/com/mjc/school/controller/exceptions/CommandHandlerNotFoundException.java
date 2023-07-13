package com.mjc.school.controller.exceptions;

public class CommandHandlerNotFoundException extends RuntimeException{
    public CommandHandlerNotFoundException(String message) {
        super(message);
    }
}
