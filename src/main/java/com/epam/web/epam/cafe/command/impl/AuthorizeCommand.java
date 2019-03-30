package com.epam.web.epam.cafe.command.impl;

import com.epam.web.epam.cafe.command.Command;
import com.epam.web.epam.cafe.logic.UserService;

import javax.servlet.http.HttpServletRequest;

public class AuthorizeCommand implements Command {
    private HttpServletRequest request;

    public AuthorizeCommand(HttpServletRequest request) {
        this.request = request;
    }

    @Override
    public String execute() {
        UserService userService = new UserService(request);
        return userService.authorize();
    }
}
