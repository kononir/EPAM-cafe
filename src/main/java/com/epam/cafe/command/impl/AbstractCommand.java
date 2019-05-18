package com.epam.cafe.command.impl;

import com.epam.cafe.api.Command;
import com.epam.cafe.api.util.UserHelper;
import com.epam.cafe.entitie.user.User;
import com.epam.cafe.util.UserHelperImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

public abstract class AbstractCommand implements Command {
    private UserHelper helper = new UserHelperImpl();

    public User findCurrentClientByRequest(HttpServletRequest request) {
        HttpSession session = request.getSession();
        List clients = (ArrayList) session.getAttribute("clients");
        Integer clientID = Integer.valueOf(request.getParameter("clientID"));

        return helper.findUserByID(clients, clientID);
    }
}
