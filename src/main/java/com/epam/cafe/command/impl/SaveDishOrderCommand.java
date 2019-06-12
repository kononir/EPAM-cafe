package com.epam.cafe.command.impl;

import com.epam.cafe.api.Command;
import com.epam.cafe.service.exception.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

public class SaveDishOrderCommand extends AbstractCommand implements Command {
    private static final String PAGE = "/view/page/client/client_menu.jsp";

    private HttpServletRequest request;

    public SaveDishOrderCommand(HttpServletRequest request) {
        this.request = request;
    }

    @Override
    public String execute() {
        HttpSession session = request.getSession();

        @SuppressWarnings("unchecked")
        List<Integer> dishesIDs = (ArrayList<Integer>) session.getAttribute("order");
        if (dishesIDs == null) {
            dishesIDs = new ArrayList<>();
        }

        dishesIDs.add(Integer.valueOf(request.getParameter("selectedDishID")));
        session.setAttribute("order", dishesIDs);

        return PAGE;
    }
}
