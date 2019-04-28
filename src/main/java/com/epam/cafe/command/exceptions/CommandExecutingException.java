package com.epam.cafe.command.exceptions;

public class CommandExecutingException extends Exception {

    public CommandExecutingException(String message, Throwable cause) {
        super(message, cause);
    }
}
