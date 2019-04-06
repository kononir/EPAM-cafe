package com.epam.web.epam.cafe.command.impl;

import com.epam.web.epam.cafe.command.Command;
import com.epam.web.epam.cafe.command.exceptions.CommandExecutingException;
import com.epam.web.epam.cafe.entitie.user.UserRole;
import com.epam.web.epam.cafe.logic.UserService;
import com.epam.web.epam.cafe.logic.exception.UserAuthorizationException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class AuthorizeCommand implements Command {
    private HttpServletRequest request;

    // Лучше передавать сюда уже полученные из реквеста параметры (в виде класса)
    public AuthorizeCommand(HttpServletRequest request) {
        this.request = request;
    }

    @Override
    public String execute() throws CommandExecutingException {
        HttpSession session = request.getSession();
        UserRole role = (UserRole) session.getAttribute("role");

        try {
            if (role == null) {
                String login = request.getParameter("login");
                String password = request.getParameter("password");

                UserService userService = new UserService();

                role = userService.authorize(login, password);

                session.setAttribute("role", role);
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
