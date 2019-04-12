package com.epam.cafe.api;

import com.epam.cafe.command.exceptions.CommandExecutingException;

public interface Command {
    String execute() throws CommandExecutingException;
}
