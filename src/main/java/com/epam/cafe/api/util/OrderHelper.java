package com.epam.cafe.api.util;

import com.epam.cafe.entitie.order.Order;

import java.util.List;

public interface OrderHelper {
    Order findOrderById(List<Order> orders, int id);
}
