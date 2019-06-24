package com.epam.cafe.command.impl.update;

import com.epam.cafe.api.Command;
import com.epam.cafe.api.service.OrderService;
import com.epam.cafe.command.impl.AbstractCommand;
import com.epam.cafe.entitie.order.Order;
import com.epam.cafe.service.impl.OrderServiceImpl;
import com.epam.cafe.service.ServiceException;

import javax.servlet.http.HttpServletRequest;

public class RateOrderCommand extends AbstractCommand implements Command {
    private static final String PAGE = "/view/page/client/previous_orders.jsp";

    private HttpServletRequest request;

    public RateOrderCommand(HttpServletRequest request) {
        this.request = request;
    }

    @Override
    public String execute() throws ServiceException {
        Integer score = Integer.valueOf(request.getParameter("rating"));

        Order order = findOrder(request);
        order.setScore(score);

        OrderService service = new OrderServiceImpl();
        service.updateOrder(order);

        return PAGE;
    }
}
