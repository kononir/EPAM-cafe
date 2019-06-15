package com.epam.cafe.builder;

import com.epam.cafe.api.builder.EntityBuilder;
import com.epam.cafe.entitie.order.Order;

import java.sql.ResultSet;
import java.sql.SQLException;

public class OrderBuilder implements EntityBuilder<Order> {

    @Override
    public Order build(ResultSet resultSet) {
        return null;
    }
}
