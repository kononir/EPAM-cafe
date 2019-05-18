package com.epam.cafe.command.impl;

import com.epam.cafe.api.Command;
import com.epam.cafe.api.service.UserService;
import com.epam.cafe.entitie.user.User;
import com.epam.cafe.service.UserServiceImpl;
import com.epam.cafe.service.exception.ServiceException;

import javax.servlet.http.HttpSession;
import java.util.List;

public class GetClientsCommand extends AbstractCommand implements Command {
    private static final String PAGE = "/view/page/administrator/user_table.jsp";

    private HttpSession session;

    public GetClientsCommand(HttpSession session) {
        this.session = session;
    }

    @Override
    public String execute() throws ServiceException {
        UserService service = new UserServiceImpl();
        List<User> clients = service.getClients();
        session.setAttribute("clients", clients);

        return PAGE;
    }
}
