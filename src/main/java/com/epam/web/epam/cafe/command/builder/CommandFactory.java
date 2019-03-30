package com.epam.web.epam.cafe.command.builder;

import com.epam.web.epam.cafe.command.Command;
import com.epam.web.epam.cafe.command.exceptions.InvalidCommandNameException;
import com.epam.web.epam.cafe.command.impl.AuthorizeCommand;
import com.epam.web.epam.cafe.command.impl.EmptyCommand;
import com.epam.web.epam.cafe.command.impl.LogoutCommand;

import javax.servlet.http.HttpServletRequest;

public class CommandFactory {
    private HttpServletRequest request;

    public CommandFactory(HttpServletRequest request) {
        this.request = request;
    }

    public Command build(String commandName) throws InvalidCommandNameException {
        if (commandName == null) {
            throw new InvalidCommandNameException("Miss commands name");
        }

        Command command;
        CommandType commandType = CommandType.valueOf(commandName.toUpperCase());
        switch (commandType) {
            case AUTHORIZE:
                command = new AuthorizeCommand(request);
                break;
            case LOGOUT:
                command = new LogoutCommand(request);
                break;
            case EMPTY:
                command = new EmptyCommand();
                break;
            default:
                throw new EnumConstantNotPresentException(CommandType.class, commandType.name());
        }

        return command;
    }
}
