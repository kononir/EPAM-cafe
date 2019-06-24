package com.epam.cafe.command.impl.get;

import com.epam.cafe.api.Command;
import com.epam.cafe.api.service.OrderService;
import com.epam.cafe.command.impl.AbstractCommand;
import com.epam.cafe.entitie.Dish;
import com.epam.cafe.entitie.order.Order;
import com.epam.cafe.entitie.user.User;
import com.epam.cafe.service.impl.DishServiceImpl;
import com.epam.cafe.service.impl.OrderServiceImpl;
import com.epam.cafe.service.impl.UserServiceImpl;
import com.epam.cafe.service.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

public class GetGlobalOrdersCommand extends AbstractCommand implements Command {
    private static final String ORDERS_PAGE = "/view/page/client/global_orders.jsp";
    private static final String EMPTY_PAGE = "/view/page/client/empty_client.jsp";

    private HttpServletRequest request;

    public GetGlobalOrdersCommand(HttpServletRequest request) {
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
            orders = service.getGlobalOrders(userID);
            int ordersCount = orders.size();
            findPageCount(session, ordersCount, recordsCount);

            if (ordersCount > recordsCount) {
                orders = orders.subList(0, recordsCount);
            }
        } else {
            int skippingPagesNumber = pageNumber - 1;
            orders = service.getGlobalOrders(userID, skippingPagesNumber, recordsCount);
        }

        String page;
        if (!orders.isEmpty()) {
            session.setAttribute("orders", orders);

            Map<Integer, Dish> idDishMap = getDishesFromOrders(orders, new DishServiceImpl());
            session.setAttribute("idDishMap", idDishMap);

            Map<Integer, User> idUserMap = getUsersFromOrders(orders, new UserServiceImpl());
            session.setAttribute("idUserMap", idUserMap);

            page = ORDERS_PAGE;
        } else {
            page = EMPTY_PAGE;
        }

        return page;
    }
}
