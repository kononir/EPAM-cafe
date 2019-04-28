package com.epam.cafe.command.factory;

import com.epam.cafe.api.Command;
import com.epam.cafe.command.exceptions.CommandCreatingException;
import com.epam.cafe.command.impl.*;

import javax.servlet.http.HttpServletRequest;

public class CommandFactory {
    private HttpServletRequest request;

    public CommandFactory(HttpServletRequest request) {
        this.request = request;
    }

    public Command create(String commandName) throws CommandCreatingException {
        if (commandName == null) {
            throw new CommandCreatingException("Miss commands name.");
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
            case GET_CLIENTS:
                command = new GetClientsCommand(request.getSession());
                break;
            case GET_BONUSES:
                command = new GetBonusesCommand(request);
                break;
            case SAVE_CLIENT_CHANGES:
                command = new SaveClientChangesCommand(request);
                break;
            default:
                throw new CommandCreatingException("Invalid commands name: " + commandType.name());
        }

        return command;
    }
}
