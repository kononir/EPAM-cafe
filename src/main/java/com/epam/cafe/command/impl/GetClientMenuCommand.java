package com.epam.cafe.command.impl;

import com.epam.cafe.api.Command;
import com.epam.cafe.api.service.DishService;
import com.epam.cafe.entitie.Dish;
import com.epam.cafe.service.DishServiceImpl;
import com.epam.cafe.service.exception.ServiceException;

import javax.servlet.http.HttpSession;
import java.util.List;

public class GetClientMenuCommand extends AbstractCommand implements Command {
    private static final String PAGE = "/view/page/client/client_menu.jsp";

    private HttpSession session;

    public GetClientMenuCommand(HttpSession session) {
        this.session = session;
    }

    @Override
    public String execute() throws ServiceException {
        DishService service = new DishServiceImpl();
        List<Dish> dishesInMenu = service.getDishesInMenu();
        session.setAttribute("dishesInMenu", dishesInMenu);

        return PAGE;
    }
}
