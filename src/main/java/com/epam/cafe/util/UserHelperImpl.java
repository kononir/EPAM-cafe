package com.epam.cafe.util;

import com.epam.cafe.api.util.UserHelper;
import com.epam.cafe.entitie.order.Order;
import com.epam.cafe.entitie.user.User;

import java.util.*;
import java.util.stream.Collectors;

public class UserHelperImpl implements UserHelper {

    @Override
    public User findUserByID(List<User> users, Integer userID) {
        List<User> foundUsers = users.stream()
                .filter(client -> userID.equals(client.getID()))
                .collect(Collectors.toList());

        return foundUsers.get(0);
    }

    @Override
        public List<Integer> getDistinctUserIdsOfOrders(List<Order> orders) {
        Set<Integer> userIDs = new HashSet<>();
        for (Order order : orders) {
            userIDs.add(order.getUserID());
        }

        return new ArrayList<>(userIDs);
    }

    @Override
    public Map<Integer, User> convertUsersToIdUserMap(List<User> users) {
        Map<Integer, User> idUserMap = new HashMap<>();
        for (User user : users) {
            idUserMap.put(user.getID(), user);
        }

        return idUserMap;
    }
}
