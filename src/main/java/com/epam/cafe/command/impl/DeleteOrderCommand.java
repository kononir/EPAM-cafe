package com.epam.cafe.command.impl;

import com.epam.cafe.api.Command;
import com.epam.cafe.api.service.OrderService;
import com.epam.cafe.entitie.order.Order;
import com.epam.cafe.service.OrderServiceImpl;
import com.epam.cafe.service.exception.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

public class DeleteOrderCommand extends AbstractCommand implements Command {
    private static final String ORDERS_PAGE = "/view/page/client/current_orders.jsp";
    private static final String EMPTY_PAGE = "/view/page/client/empty_client.jsp";

    private HttpServletRequest request;

    public DeleteOrderCommand(HttpServletRequest request) {
        this.request = request;
    }

    @Override
    public String execute() throws ServiceException {
        Order order = findOrder(request);

        OrderService service = new OrderServiceImpl();
        service.deleteOrder(order);

        removeOrderFromRequest(request, order);

        HttpSession session = request.getSession();
        @SuppressWarnings("unchecked")
        List<Order> orders = (ArrayList<Order>) session.getAttribute("orders");

        String page;
        if (!orders.isEmpty()) {
            page = ORDERS_PAGE;
        } else {
            page = EMPTY_PAGE;
        }

        return page;
    }
}
