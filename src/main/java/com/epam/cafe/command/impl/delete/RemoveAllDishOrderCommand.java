package com.epam.cafe.command.impl.delete;

import com.epam.cafe.api.Command;
import com.epam.cafe.command.impl.AbstractCommand;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class RemoveAllDishOrderCommand extends AbstractCommand implements Command {
    private static final String PAGE = "/view/page/client/empty_client.jsp";

    private HttpServletRequest request;

    public RemoveAllDishOrderCommand(HttpServletRequest request) {
        this.request = request;
    }

    @Override
    public String execute() {
        HttpSession session = request.getSession();
        session.setAttribute("order", null);

        return PAGE;
    }
}
