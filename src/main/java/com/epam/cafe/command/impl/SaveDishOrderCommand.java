package com.epam.cafe.command.impl;

import com.epam.cafe.api.Command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

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
        Map<Integer, Integer> order = (HashMap<Integer, Integer>) session.getAttribute("order");
        if (order == null) {
            order = new HashMap<>();
        }

        Integer selectedDishID = Integer.valueOf(request.getParameter("selectedDishID"));
        Integer servingsNumber = Integer.valueOf(request.getParameter("servingsNumber"));
        if (order.containsKey(selectedDishID)) {
            Integer newServingsNumber = order.get(selectedDishID) + servingsNumber;
            order.put(selectedDishID, newServingsNumber);
        } else {
            order.put(selectedDishID, servingsNumber);
        }

        session.setAttribute("order", order);

        return PAGE;
    }


}
