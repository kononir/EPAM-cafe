package com.epam.cafe.command.impl;

import com.epam.cafe.api.Command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class LogoutCommand implements Command {
    private HttpServletRequest request;

    private static final String AUTHORIZATION_PAGE = "/view/authorization.jsp";

    public LogoutCommand(HttpServletRequest request) {
        this.request = request;
    }

    @Override
    public String execute() {
        HttpSession session = request.getSession();

        session.invalidate();

        return AUTHORIZATION_PAGE;
    }
}
