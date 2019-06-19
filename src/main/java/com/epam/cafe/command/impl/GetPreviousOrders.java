package com.epam.cafe.command.impl;

import com.epam.cafe.api.Command;
import com.epam.cafe.api.service.DishService;
import com.epam.cafe.api.service.OrderService;
import com.epam.cafe.entitie.Dish;
import com.epam.cafe.entitie.order.Order;
import com.epam.cafe.entitie.user.User;
import com.epam.cafe.service.DishServiceImpl;
import com.epam.cafe.service.OrderServiceImpl;
import com.epam.cafe.service.exception.ServiceException;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

public class GetPreviousOrders extends AbstractCommand implements Command {
    private static final String ORDERS_PAGE = "/view/page/client/previous_orders.jsp";
    private static final String EMPTY_PAGE = "/view/page/client/empty.jsp";

    private HttpSession session;

    public GetPreviousOrders(HttpSession session) {
        this.session = session;
    }

    @Override
    public String execute() throws ServiceException {
        OrderService service = new OrderServiceImpl();
        User user = (User) session.getAttribute("user");
        List<Order> orders = service.getPreviousOrders(user.getID());

        String page;
        if (!orders.isEmpty()) {
            session.setAttribute("orders", orders);

            DishService dishService = new DishServiceImpl();
            List<Integer> dishIDs = getDistinctDishesIdsOfOrders(orders);
            List<Dish> dishes = dishService.getDishesByIds(dishIDs);
            Map<Integer, Dish> idDishMap = convertDishesToIdDishMap(dishes);

            session.setAttribute("idDishMap", idDishMap);

            page = ORDERS_PAGE;
        } else {
            page = EMPTY_PAGE;
        }

        return page;
    }
}
