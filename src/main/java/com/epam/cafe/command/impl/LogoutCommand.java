package com.epam.cafe.command.impl;

import com.epam.cafe.api.Command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class LogoutCommand extends AbstractCommand implements Command {
    private static final String PAGE = "/view/page/general/authorization.jsp";

    private HttpSession session;

    public LogoutCommand(HttpSession session) {
        this.session = session;
    }

    @Override
    public String execute() {
        session.invalidate();
        return PAGE;
    }
}
