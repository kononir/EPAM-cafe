package com.epam.cafe.command.impl;

import com.epam.cafe.api.Command;
import com.epam.cafe.api.service.DishService;
import com.epam.cafe.api.util.DishHelper;
import com.epam.cafe.entitie.Dish;
import com.epam.cafe.service.DishServiceImpl;
import com.epam.cafe.service.exception.ServiceException;
import com.epam.cafe.util.DishHelperImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.util.*;

public class ShowBasketCommand extends AbstractCommand implements Command {
    public static final String BASKET_PAGE = "/view/page/client/basket.jsp";
    public static final String EMPTY_BASKET_PAGE = "/view/page/client/empty_basket.jsp";

    public HttpServletRequest request;

    public DishService service = new DishServiceImpl();
    public DishHelper helper = new DishHelperImpl();

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
        if (order != null) {
            List<Integer> dishesIDs = new ArrayList<>(order.keySet());
            List<Dish> dishes = service.getDishesByIds(dishesIDs);
            session.setAttribute("dishes", dishes);

            Map<Integer, BigDecimal> generalCosts = helper.calculateGeneralCosts(dishes, order);
            session.setAttribute("generalCosts", generalCosts);

            BigDecimal result = helper.calculateResult(generalCosts);
            session.setAttribute("resultCost", result);

            page = BASKET_PAGE;
        } else {
            page = EMPTY_BASKET_PAGE;
        }

        return page;
    }
}
