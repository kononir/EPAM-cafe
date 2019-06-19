package com.epam.cafe.command.factory;

import com.epam.cafe.api.Command;
import com.epam.cafe.command.exception.CommandFactoryException;
import com.epam.cafe.command.impl.*;

import javax.servlet.http.HttpServletRequest;

public class CommandFactory {
    private HttpServletRequest request;

    public CommandFactory(HttpServletRequest request) {
        this.request = request;
    }

    public Command create(String commandName) throws CommandFactoryException {
        if (commandName == null) {
            throw new CommandFactoryException("Miss commands name.");
        }

        Command command;
        CommandType commandType = CommandType.valueOf(commandName.toUpperCase());
        switch (commandType) {
            case AUTHORIZE:
                command = new AuthorizeCommand(request);
                break;
            case LOGOUT:
                command = new LogoutCommand(request.getSession());
                break;
            case GET_CLIENTS:
                command = new GetClientsCommand(request.getSession());
                break;
            case GET_BONUSES_ADMIN:
                command = new GetBonusesAdminCommand(request);
                break;
            case MANAGE_CLIENT_INFORMATION:
                command = new ManageClientsInformationCommand(request);
                break;
            case SAVE_CLIENT_CHANGES:
                command = new SaveClientChangesCommand(request);
                break;
            case GET_CLIENT_MENU:
                command = new GetClientMenuCommand(request.getSession());
                break;
            case SAVE_DISH_ORDER:
                command = new SaveDishOrderCommand(request);
                break;
            case SHOW_BASKET:
                command = new ShowBasketCommand(request);
                break;
            case REMOVE_DISH_ORDER:
                command = new RemoveDishOrderCommand(request);
                break;
            case ORDER:
                command = new OrderCommand(request);
                break;
            case REMOVE_ALL_DISH_ORDER:
                command = new RemoveAllDishOrderCommand(request);
                break;
            case GET_BONUSES_CLIENT:
                command = new GetBonusesClientCommand(request);
                break;
            case DELETE_BONUS:
                command = new DeleteBonusCommand(request);
                break;
            case GET_ALL_DISHES:
                command = new GetAllDishesCommand(request.getSession());
                break;
            case MANAGE_DISH_INFORMATION:
                command = new ManageDishInformationCommand(request);
                break;
            case SAVE_DISH_CHANGES:
                command = new SaveDishChangesCommand(request);
                break;
            case DELETE_DISH:
                command = new DeleteDishCommand(request);
                break;
            case SPECIFY_NEW_DISH:
                command = new SpecifyNewDishCommand();
                break;
            case ADD_DISH:
                command = new AddDishCommand(request);
                break;
            case GET_PREVIOUS_ORDERS:
                command = new GetPreviousOrders(request.getSession());
                break;
            case RATE_ORDER:
                command = new RateOrderCommand(request);
                break;
            case LEAVE_COMMENT:
                command = new LeaveCommentCommand(request);
                break;
            default:
                throw new CommandFactoryException("Invalid commands name: " + commandType.name());
        }

        return command;
    }
}
