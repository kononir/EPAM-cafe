package com.epam.web.epam.cafe.command;

import com.epam.web.epam.cafe.command.exceptions.CommandExecutingException;

public interface Command {
    String execute() throws CommandExecutingException;
}
