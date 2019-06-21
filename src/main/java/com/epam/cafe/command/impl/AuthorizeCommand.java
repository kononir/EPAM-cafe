package com.epam.cafe.command.impl;

import com.epam.cafe.api.Command;
import com.epam.cafe.api.service.UserService;
import com.epam.cafe.entitie.Language;
import com.epam.cafe.entitie.user.User;
import com.epam.cafe.entitie.user.UserRole;
import com.epam.cafe.service.UserServiceImpl;
import com.epam.cafe.service.exception.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Optional;

public class AuthorizeCommand extends AbstractCommand implements Command {
    private static final Language LANGUAGE = Language.ENGLISH;

    private HttpServletRequest request;

    public AuthorizeCommand(HttpServletRequest request) {
        this.request = request;
    }

    @Override
    public String execute() throws ServiceException {
        String login = request.getParameter("login");
        String password = request.getParameter("password");

        UserService userService = new UserServiceImpl();
        Optional<User> receivedUser = userService.authorize(login, password);

        HttpSession session = request.getSession();

        UserRole role;
        if (receivedUser.isPresent()) {
            User user = receivedUser.get();
            role = handleUser(user);
        } else {
            session.setAttribute("error", "Wrong login or password!");
            role = UserRole.ANONYMOUS;
        }

        return findPageByRole(role);
    }

    private UserRole handleUser(User user) {
        HttpSession session = request.getSession();

        UserRole currentRole = user.getRole();
        boolean userIsNotBannedClient = currentRole.equals(UserRole.CLIENT) && !user.getBanned();
        boolean userIsAdmin = currentRole.equals(UserRole.ADMINISTRATOR);

        UserRole finalRole;
        if (userIsNotBannedClient || userIsAdmin) {
            session.setAttribute("user", user);
            session.setAttribute("locale", LANGUAGE.getLocale());
            finalRole = currentRole;
        } else {
            session.setAttribute("error", "User is banned!");
            finalRole = UserRole.ANONYMOUS;
        }

        return finalRole;
    }
}
