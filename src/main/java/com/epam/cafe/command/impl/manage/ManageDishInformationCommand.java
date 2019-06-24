package com.epam.cafe.command.impl.manage;

import com.epam.cafe.api.Command;
import com.epam.cafe.command.impl.AbstractCommand;
import com.epam.cafe.entitie.Dish;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class ManageDishInformationCommand extends AbstractCommand implements Command {
    private static final String PAGE = "/view/page/administrator/dish_managing_form.jsp";

    private HttpServletRequest request;

    public ManageDishInformationCommand(HttpServletRequest request) {
        this.request = request;
    }

    @Override
    public String execute() {
        Dish dish = findDish(request);
        HttpSession session = request.getSession();
        session.setAttribute("dish", dish);

        return PAGE;
    }
}
