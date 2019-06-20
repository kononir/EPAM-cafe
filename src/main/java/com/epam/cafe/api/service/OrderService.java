package com.epam.cafe.api.service;

import com.epam.cafe.entitie.PaymentMethod;
import com.epam.cafe.entitie.order.Order;
import com.epam.cafe.entitie.user.User;
import com.epam.cafe.service.exception.ServiceException;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

public interface OrderService {
    void orderSelected(Map<Integer, Integer> chosenDishes, LocalDateTime dateTime,
                       User currentUser, BigDecimal resultCost,
                       PaymentMethod paymentMethod) throws ServiceException;

    List<Order> getPreviousOrders(int userID) throws ServiceException;

    void updateOrder(Order order) throws ServiceException;

    List<Order> getGlobalOrders(int userID) throws ServiceException;
}
