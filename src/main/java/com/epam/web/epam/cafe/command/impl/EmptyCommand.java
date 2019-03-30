package com.epam.web.epam.cafe.command.impl;

import com.epam.web.epam.cafe.command.Command;

public class EmptyCommand implements Command {
    private static final String AUTHORIZATION_PAGE = "/WEB-INF/view/authorization.jsp";

    @Override
    public String execute() {
        return AUTHORIZATION_PAGE;
    }
}
