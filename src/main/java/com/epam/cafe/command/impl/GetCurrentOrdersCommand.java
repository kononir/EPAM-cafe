package com.epam.cafe.command.impl;

import com.epam.cafe.api.Command;
import com.epam.cafe.api.service.OrderService;
import com.epam.cafe.entitie.order.Order;
import com.epam.cafe.entitie.user.User;
import com.epam.cafe.service.OrderServiceImpl;
import com.epam.cafe.service.exception.ServiceException;

import javax.servlet.http.HttpSession;
import java.util.List;

public class GetCurrentOrdersCommand extends AbstractCommand implements Command {
    private static final String ORDERS_PAGE = "/view/page/client/current_orders.jsp";
    private static final String EMPTY_PAGE = "/view/page/client/empty_client.jsp";

    private HttpSession session;

    public GetCurrentOrdersCommand(HttpSession session) {
        this.session = session;
    }

    @Override
    public String execute() throws ServiceException {
        OrderService service = new OrderServiceImpl();
        User user = (User) session.getAttribute("user");
        List<Order> orders = service.getCurrentOrders(user.getID());

        String page;
        if (!orders.isEmpty()) {
            setParamsForCurrentUserOrders(session, orders);

            page = ORDERS_PAGE;
        } else {
            page = EMPTY_PAGE;
        }

        return page;
    }
}
