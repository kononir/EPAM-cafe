package com.epam.cafe.command.impl;

import com.epam.cafe.api.Command;
import com.epam.cafe.api.service.UserService;
import com.epam.cafe.entitie.user.User;
import com.epam.cafe.entitie.user.UserRole;
import com.epam.cafe.service.UserServiceImpl;
import com.epam.cafe.service.exception.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Optional;

public class AuthorizeCommand extends AbstractCommand implements Command {
    private HttpServletRequest request;

    public AuthorizeCommand(HttpServletRequest request) {
        this.request = request;
    }

    @Override
    public String execute() throws ServiceException {
        UserRole role;

        HttpSession session = request.getSession();
        Optional<User> receivedUser = Optional.ofNullable((User) session.getAttribute("user"));
        if (!receivedUser.isPresent()) {
            String login = request.getParameter("login");
            String password = request.getParameter("password");

            UserService userService = new UserServiceImpl();
            receivedUser = userService.authorize(login, password);
        }

        if (receivedUser.isPresent()) {
            User user = receivedUser.get();
            session.setAttribute("user", user);
            role = user.getRole();
        } else {
            role = UserRole.ANONYMOUS;
        }

        return findPageByRole(role);
    }

    private String findPageByRole(UserRole userRole) {
        String page;

        switch (userRole) {
            case CLIENT:
                page = "/view/page/client/client.jsp";
                break;
            case ADMINISTRATOR:
                page = "/view/page/administrator/admin.jsp";
                break;
            case ANONYMOUS:
                page = "/view/page/general/authorization.jsp";
                break;
            default:
                throw new EnumConstantNotPresentException(UserRole.class, userRole.name());
        }

        return page;
    }
}
