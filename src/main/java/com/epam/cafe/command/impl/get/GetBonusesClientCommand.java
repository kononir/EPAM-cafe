package com.epam.cafe.command.impl.get;

import com.epam.cafe.api.Command;
import com.epam.cafe.entitie.Bonus;
import com.epam.cafe.entitie.user.User;
import com.epam.cafe.service.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

public class GetBonusesClientCommand extends GetBonusesAbstractCommand implements Command {
    private static final String BONUSES_PAGE = "/view/page/client/bonuses_table_client.jsp";
    private static final String EMPTY_PAGE = "/view/page/client/empty_client.jsp";

    private HttpServletRequest request;

    public GetBonusesClientCommand(HttpServletRequest request) {
        super(request);
        this.request = request;
    }

    @Override
    public String execute() throws ServiceException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        int userID = user.getID();

        List<Bonus> bonuses = getBonuses(userID);

        String page;
        if (!bonuses.isEmpty()) {
            session.setAttribute("clientBonuses", bonuses);
            page = BONUSES_PAGE;
        } else {
            page = EMPTY_PAGE;
        }

        return page;
    }
}
