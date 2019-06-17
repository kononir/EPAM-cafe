package com.epam.cafe.command.impl;

import com.epam.cafe.api.Command;
import com.epam.cafe.api.service.DishService;
import com.epam.cafe.entitie.Dish;
import com.epam.cafe.service.DishServiceImpl;
import com.epam.cafe.service.exception.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ShowBasketCommand extends AbstractCommand implements Command {
    private static final String BASKET_PAGE = "/view/page/client/basket.jsp";
    private static final String EMPTY_BASKET_PAGE = "/view/page/client/empty_basket.jsp";

    private HttpServletRequest request;

    public ShowBasketCommand(HttpServletRequest request) {
        this.request = request;
    }

    @Override
    public String execute() throws ServiceException {
        HttpSession session = request.getSession();
        session.getAttribute("order");

        @SuppressWarnings("unchecked")
        Map<Integer, Integer> order = (HashMap<Integer, Integer>) session.getAttribute("order");

        String page;
        if (order != null && !order.isEmpty()) {
            List<Integer> dishesIDs = new ArrayList<>(order.keySet());
            DishService service = new DishServiceImpl();

            List<Dish> dishes = service.getDishesByIds(dishesIDs);
            session.setAttribute("dishes", dishes);

            calculateCostsOfDishesInBasket(session);

            page = BASKET_PAGE;
        } else {
            page = EMPTY_BASKET_PAGE;
        }

        return page;
    }
}
