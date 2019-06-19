package com.epam.cafe.command.impl;

import com.epam.cafe.api.Command;
import com.epam.cafe.api.service.OrderService;
import com.epam.cafe.entitie.PaymentMethod;
import com.epam.cafe.entitie.user.User;
import com.epam.cafe.service.OrderServiceImpl;
import com.epam.cafe.service.exception.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.Map;

public class OrderCommand extends AbstractCommand implements Command {
    private static final String SUCCESS_PAGE = "/view/page/client/success_client.jsp";

    private HttpServletRequest request;

    public OrderCommand(HttpServletRequest request) {
        this.request = request;
    }

    @Override
    public String execute() throws ServiceException {
        String dateLine = request.getParameter("date");
        LocalDate date = LocalDate.parse(dateLine);
        String timeLine = request.getParameter("time");
        LocalTime time = LocalTime.parse(timeLine);
        LocalDateTime dateTime = LocalDateTime.of(date, time);

        String paymentMethodLine = request.getParameter("paymentMethod");
        PaymentMethod paymentMethod = PaymentMethod.valueOf(paymentMethodLine.toUpperCase());

        HttpSession session = request.getSession();
        User currentUser = (User) session.getAttribute("user");
        @SuppressWarnings("unchecked")
        Map<Integer, Integer> order = (HashMap<Integer, Integer>) session.getAttribute("order");
        BigDecimal resultCost = (BigDecimal) session.getAttribute("resultCost");

        OrderService service = new OrderServiceImpl();
        service.orderSelected(order, dateTime, currentUser, resultCost, paymentMethod);

        session.setAttribute("order", null);

        return SUCCESS_PAGE;
    }
}
