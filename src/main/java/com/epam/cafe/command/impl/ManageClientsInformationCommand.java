package com.epam.cafe.command.impl;

import com.epam.cafe.api.Command;
import com.epam.cafe.entitie.user.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class ManageClientsInformationCommand extends AbstractCommand implements Command {
    private static final String PAGE = "/view/page/administrator/client_managing_form.jsp";

    private HttpServletRequest request;

    public ManageClientsInformationCommand(HttpServletRequest request) {
        this.request = request;
    }

    @Override
    public String execute() {
        HttpSession session = request.getSession();
        User client = findCurrentClientByRequest(request);
        session.setAttribute("client", client);

        return PAGE;
    }
}
