package com.epam.cafe.api.util;

import com.epam.cafe.entitie.order.Order;
import com.epam.cafe.entitie.user.User;

import java.util.List;
import java.util.Map;

public interface UserHelper {
    User findUserByID(List<User> users, Integer userID);

    List<Integer> getDistinctUserIdsOfOrders(List<Order> orders);

    Map<Integer, User> convertUsersToIdUserMap(List<User> users);
}
