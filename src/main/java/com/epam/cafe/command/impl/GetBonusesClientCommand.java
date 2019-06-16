package com.epam.cafe.command.impl;

import com.epam.cafe.api.Command;
import com.epam.cafe.api.service.BonusService;
import com.epam.cafe.entitie.bonus.Bonus;
import com.epam.cafe.entitie.user.User;
import com.epam.cafe.service.BonusServiceImpl;
import com.epam.cafe.service.exception.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

public class GetBonusesClientCommand extends AbstractCommand implements Command {
    private static final String PAGE = "/view/page/client/bonuses_table_client.jsp";

    private HttpServletRequest request;

    public GetBonusesClientCommand(HttpServletRequest request) {
        this.request = request;
    }

    @Override
    public String execute() throws ServiceException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        int userID = user.getID();

        BonusService service = new BonusServiceImpl();
        List<Bonus> bonuses = service.getClientBonuses(userID);

        session.setAttribute("clientBonuses", bonuses);

        return PAGE;
    }
}
