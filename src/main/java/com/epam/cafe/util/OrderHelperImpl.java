package com.epam.cafe.util;

import com.epam.cafe.api.util.OrderHelper;
import com.epam.cafe.entitie.order.Order;

import java.util.List;
import java.util.Optional;

public class OrderHelperImpl implements OrderHelper {

    @Override
    public Order findOrderById(List<Order> orders, int id) {
        Optional<Order> foundOrder = orders.stream()
                .filter(order -> (order.getID() == id))
                .findFirst();

        return foundOrder.orElse(null);
    }
}
