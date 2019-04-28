package com.epam.cafe.command.impl;

import com.epam.cafe.api.Command;
import com.epam.cafe.command.exceptions.CommandExecutingException;
import com.epam.cafe.entitie.user.User;
import com.epam.cafe.logic.UserService;
import com.epam.cafe.logic.exception.ServiceException;

import javax.servlet.http.HttpSession;
import java.util.List;

public class GetClientsCommand implements Command {
    private static final String PAGE = "/view/user_table.jsp";

    private HttpSession session;

    public GetClientsCommand(HttpSession session) {
        this.session = session;
    }

    @Override
    public String execute() throws CommandExecutingException {
        try {
            UserService service = new UserService();
            List<User> clients = service.getClients();
            session.setAttribute("clients", clients);
        } catch (ServiceException e) {
            throw new CommandExecutingException("Error when execute get clients command.", e);
        }

        return PAGE;
    }
}
