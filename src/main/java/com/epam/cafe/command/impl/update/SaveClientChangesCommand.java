package com.epam.cafe.command.impl.update;

import com.epam.cafe.api.Command;
import com.epam.cafe.api.service.UserService;
import com.epam.cafe.command.impl.AbstractCommand;
import com.epam.cafe.entitie.user.User;
import com.epam.cafe.service.impl.UserServiceImpl;
import com.epam.cafe.service.ServiceException;

import javax.servlet.http.HttpServletRequest;

public class SaveClientChangesCommand extends AbstractCommand implements Command {
    private static final String PAGE = "/view/page/administrator/user_table.jsp";

    private HttpServletRequest request;

    public SaveClientChangesCommand(HttpServletRequest request) {
        this.request = request;
    }

    @Override
    public String execute() throws ServiceException {
        User client = findClient(request);

        Boolean clientIsBanned = Boolean.valueOf(request.getParameter("clientIsBanned"));
        client.setBanned(clientIsBanned);

        Integer clientScore = Integer.valueOf(request.getParameter("clientScore"));
        client.setScore(clientScore);

        UserService service = new UserServiceImpl();
        service.updateUser(client);

        return PAGE;
    }
}
