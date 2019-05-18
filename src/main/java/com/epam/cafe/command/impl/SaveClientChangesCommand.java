package com.epam.cafe.command.impl;

import com.epam.cafe.api.Command;
import com.epam.cafe.api.service.UserService;
import com.epam.cafe.entitie.user.User;
import com.epam.cafe.service.UserServiceImpl;
import com.epam.cafe.service.exception.ServiceException;

import javax.servlet.http.HttpServletRequest;

public class SaveClientChangesCommand extends AbstractCommand implements Command {
    private static final String PAGE = "/view/page/administrator/user_table.jsp";

    private HttpServletRequest request;

    public SaveClientChangesCommand(HttpServletRequest request) {
        this.request = request;
    }

    @Override
    public String execute() throws ServiceException {
        User client = findCurrentClientByRequest(request);

        Boolean clientIsBanned = Boolean.valueOf(request.getParameter("clientIsBanned"));
        client.setBanned(clientIsBanned);

        Integer clientScore = Integer.valueOf(request.getParameter("clientScore"));
        client.setScore(clientScore);

        UserService service = new UserServiceImpl();
        service.updateUser(client);

        return PAGE;
    }
}
