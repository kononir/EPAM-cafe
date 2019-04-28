package com.epam.cafe.command.impl;

import com.epam.cafe.api.Command;
import com.epam.cafe.command.exceptions.CommandExecutingException;
import com.epam.cafe.entitie.Bonus;
import com.epam.cafe.logic.UserService;
import com.epam.cafe.logic.exception.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

public class GetBonusesCommand implements Command {
    private static final String PAGE = "/view/bonuses_table_admin.jsp";

    private HttpServletRequest request;

    public GetBonusesCommand(HttpServletRequest request) {
        this.request = request;
    }

    @Override
    public String execute() throws CommandExecutingException {
        try {
            Integer clientID = Integer.valueOf(request.getParameter("clientID"));
            UserService service = new UserService();
            List<Bonus> bonuses = service.getClientBonuses(clientID);

            HttpSession session = request.getSession();
            session.setAttribute("clientBonuses", bonuses);
        } catch (ServiceException e) {
            throw new CommandExecutingException("Error when execute get bonuses command.", e);
        }
        return PAGE;
    }
}
