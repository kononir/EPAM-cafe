package com.epam.cafe.command.impl;

import com.epam.cafe.api.Command;
import com.epam.cafe.api.service.BonusService;
import com.epam.cafe.entitie.Bonus;
import com.epam.cafe.entitie.user.User;
import com.epam.cafe.service.BonusServiceImpl;
import com.epam.cafe.service.exception.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

public class GetBonusesCommand extends AbstractCommand implements Command {
    private static final String PAGE = "/view/page/administrator/bonuses_table_admin.jsp";

    private HttpServletRequest request;

    public GetBonusesCommand(HttpServletRequest request) {
        this.request = request;
    }

    @Override
    public String execute() throws ServiceException {
        HttpSession session = request.getSession();

        User client = findCurrentClientByRequest(request);
        session.setAttribute("client", client);

        int clientID = Integer.parseInt(request.getParameter("clientID"));

        BonusService service = new BonusServiceImpl();
        List<Bonus> bonuses = service.getClientBonuses(clientID);

        session.setAttribute("clientBonuses", bonuses);

        return PAGE;
    }
}