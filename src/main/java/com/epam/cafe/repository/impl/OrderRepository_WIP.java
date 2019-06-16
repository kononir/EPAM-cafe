package com.epam.cafe.repository.impl;

import com.epam.cafe.api.repository.specification.SqlSpecification;
import com.epam.cafe.entitie.order.OrderBuilder_WIP;
import com.epam.cafe.entitie.order.Order;
import com.epam.cafe.entitie.order.OrderDish;
import com.epam.cafe.repository.exception.RepositoryException;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class OrderRepository_WIP extends AbstractRepository<Order> {
    private static final String TABLE_NAME = "`order`";

    private ChosenDishesRepository_WIP chosenDishesRepository;

    public OrderRepository_WIP(Connection connection, ChosenDishesRepository_WIP chosenDishesRepository) {
        super(connection);
        this.chosenDishesRepository = chosenDishesRepository;
    }

    @Override
    protected String getTableName() {
        return TABLE_NAME;
    }

    @Override
    protected Map<String, Object> getParams(Order order) {
        Map<String, Object> params = new LinkedHashMap<>();
        params.put(Order.ID_COLUMN, order.getID());
        params.put(Order.RECEIPT_TIME_COLUMN, order.getReceiptTime());
        params.put(Order.SCORE_COLUMN, order.getScore());
        params.put(Order.COMMENT_COLUMN, order.getComment());
        params.put(Order.USER_ID_COLUMN, order.getUserID());

        return params;
    }

    @Override
    public void save(Order order) throws RepositoryException {
        try {
            super.save(order);
            int orderID = getLastID();

            Map<Integer, Integer> chosenDishes = order.getChosenDishes();
            for (Map.Entry<Integer, Integer> entry : chosenDishes.entrySet()) {
                Integer dishID = entry.getKey();
                Integer servingsNumber = entry.getValue();
                chosenDishesRepository.save(new OrderDish(orderID, dishID, servingsNumber));
            }
        } catch (SQLException e) {
            throw new RepositoryException("Saving to order repository error.", e);
        }
    }

    @Override
    public List<Order> query(SqlSpecification specification) throws RepositoryException {


        return executeQuery(specification, new OrderBuilder_WIP());
    }
}
