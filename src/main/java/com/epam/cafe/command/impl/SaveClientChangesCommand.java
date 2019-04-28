package com.epam.cafe.command.impl;

import com.epam.cafe.api.Command;
import com.epam.cafe.command.exceptions.CommandExecutingException;
import com.epam.cafe.entitie.user.User;
import com.epam.cafe.logic.UserService;
import com.epam.cafe.logic.exception.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class SaveClientChangesCommand implements Command {
    private static final String PAGE = "/view/user_table.jsp";

    private HttpServletRequest request;

    public SaveClientChangesCommand(HttpServletRequest request) {
        this.request = request;
    }

    @Override
    public String execute() throws CommandExecutingException {
        try {
            HttpSession session = request.getSession();

            List clients = (ArrayList) session.getAttribute("clients");
            Integer clientID = Integer.valueOf(request.getParameter("clientID"));
            User client = findClientWithID(clients, clientID);

            Boolean clientIsBanned = Boolean.valueOf(request.getParameter("clientIsBanned"));
            client.setBanned(clientIsBanned);

            Integer clientScore = Integer.valueOf(request.getParameter("clientScore"));
            client.setScore(clientScore);

            UserService service = new UserService();
            service.updateUser(client);
        } catch (ServiceException e) {
            throw new CommandExecutingException("Error when execute save client changes command.", e);
        }

        return PAGE;
    }

    private User findClientWithID(List clientsAsAttributes, Integer clientID) {
        List<User> clients = new ArrayList<>();
        for (Object attribute : clientsAsAttributes) {
            clients.add((User) attribute);
        }

        List<User> foundClients = clients.stream()
                .filter(client -> clientID.equals(client.getID()))
                .collect(Collectors.toList());

        return foundClients.get(0);
    }
}
