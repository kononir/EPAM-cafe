package com.epam.cafe.command.impl;

import com.epam.cafe.api.Command;
import com.epam.cafe.api.service.OrderService;
import com.epam.cafe.entitie.order.Order;
import com.epam.cafe.entitie.user.User;
import com.epam.cafe.service.OrderServiceImpl;
import com.epam.cafe.service.exception.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

public class GetPreviousOrdersCommand extends AbstractCommand implements Command {
    private static final String ORDERS_PAGE = "/view/page/client/previous_orders.jsp";
    private static final String EMPTY_PAGE = "/view/page/client/empty_client.jsp";

    private HttpServletRequest request;

    public GetPreviousOrdersCommand(HttpServletRequest request) {
        this.request = request;
    }

    @Override
    public String execute() throws ServiceException {
        OrderService service = new OrderServiceImpl();
        HttpSession session = request.getSession();

        int userID = findUserID(session);

        int recordsCount = findRecordsCount(request);
        int pageNumber = findPageNumber(request);

        List<Order> orders;
        if (pageNumber == 1) {
            orders = service.getPreviousOrders(userID);
            int ordersCount = orders.size();
            findPageCount(session, ordersCount, recordsCount);

            if (ordersCount > recordsCount) {
                orders = orders.subList(0, recordsCount);
            }
        } else {
            int skippingPagesNumber = pageNumber - 1;
            orders = service.getPreviousOrders(userID, skippingPagesNumber, recordsCount);
        }

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
