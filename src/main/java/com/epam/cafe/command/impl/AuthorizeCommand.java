package com.epam.cafe.command.impl;

import com.epam.cafe.api.Command;
import com.epam.cafe.command.exceptions.CommandExecutingException;
import com.epam.cafe.entitie.user.User;
import com.epam.cafe.entitie.user.UserRole;
import com.epam.cafe.logic.UserService;
import com.epam.cafe.logic.exception.UserAuthorizationException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Optional;

public class AuthorizeCommand implements Command {
    private HttpServletRequest request;

    public AuthorizeCommand(HttpServletRequest request) {
        this.request = request;
    }

    @Override
    public String execute() throws CommandExecutingException {
        UserRole role;

        try {
            HttpSession session = request.getSession();
            Optional<User> receivedUser = Optional.ofNullable((User) session.getAttribute("user"));
            if (!receivedUser.isPresent()) {
                String login = request.getParameter("login");
                String password = request.getParameter("password");

                UserService userService = new UserService();

                receivedUser = userService.authorize(login, password);
            }

            if (receivedUser.isPresent()) {
                User user = receivedUser.get();
                session.setAttribute("user", user);
                role = user.getRole();
            } else {
                role = UserRole.ANONYMOUS;
            }
        } catch (UserAuthorizationException e) {
            throw new CommandExecutingException(e);
        }

        return findPageByRole(role);
    }

    private String findPageByRole(UserRole userRole) throws CommandExecutingException {
        String page;

        switch (userRole) {
            case CLIENT:
                page = "/view/user.jsp";
                break;
            case ADMINISTRATOR:
                page = "/view/admin.jsp";
                break;
            case ANONYMOUS:
                page = "/view/authorization.jsp";
                break;
            default:
                throw new CommandExecutingException(new EnumConstantNotPresentException(UserRole.class, userRole.name()));
        }

        return page;
    }
}
