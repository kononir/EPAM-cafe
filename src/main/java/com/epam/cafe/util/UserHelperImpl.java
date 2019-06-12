package com.epam.cafe.util;

import com.epam.cafe.api.util.UserHelper;
import com.epam.cafe.entitie.user.User;

import java.util.List;
import java.util.stream.Collectors;

public class UserHelperImpl implements UserHelper {

    @Override
    public User findUserByID(List<User> users, Integer userID) {
        List<User> foundUsers = users.stream()
                .filter(client -> userID.equals(client.getID()))
                .collect(Collectors.toList());

        return foundUsers.get(0);
    }
}
